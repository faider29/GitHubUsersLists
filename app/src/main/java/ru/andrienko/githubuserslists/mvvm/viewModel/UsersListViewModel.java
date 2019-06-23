package ru.andrienko.githubuserslists.mvvm.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ru.andrienko.githubuserslists.entity.User;
import ru.andrienko.githubuserslists.interfaces.UsersModelListener;
import ru.andrienko.githubuserslists.mvvm.model.UsersListModel;

/**
 * Created by Maxim Andrienko
 * 6/18/19
 */
public class UsersListViewModel extends ViewModel implements UsersModelListener {


    private UsersListModel mModel;

    private MutableLiveData<List<User>> mUsers = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoad = new MutableLiveData<>();


    public UsersListViewModel(){
        mModel = new UsersListModel();
        mModel.addListener(this);
        mIsLoad.postValue(true);
        mModel.start();

    }

    public void getNext(){
        mModel.getNext();
    }

    @Override
    public void usersListLoad(List<User> users) {
        mIsLoad.postValue(false);
        mUsers.postValue(users);
    }

    @Override
    public void error(String errorMessage) {
        mIsLoad.postValue(false);
        mError.postValue("Ошибка: " +errorMessage);
    }

    @Override
    protected void onCleared() {
        mModel.removeListener(this);
        mModel = null;
        super.onCleared();
    }

    public MutableLiveData<List<User>> getUsers(){
        return mUsers;
    }

    public MutableLiveData<String> getError(){
        return mError;
    }
    public MutableLiveData<Boolean> getIsLoad(){
        return mIsLoad;
    }
}
