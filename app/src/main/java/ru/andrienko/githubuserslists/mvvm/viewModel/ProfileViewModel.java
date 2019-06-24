package ru.andrienko.githubuserslists.mvvm.viewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import ru.andrienko.githubuserslists.entity.Profile;
import ru.andrienko.githubuserslists.interfaces.ProfileModelListener;
import ru.andrienko.githubuserslists.mvvm.model.ProfileModel;

public class ProfileViewModel  extends ViewModel implements ProfileModelListener {

    private ProfileModel mModel;

    private MutableLiveData<Profile> mProfile = new MutableLiveData<>();
    private MutableLiveData<String> mError = new MutableLiveData<>();
    private MutableLiveData<Boolean> mIsLoad = new MutableLiveData<>();

    public ProfileViewModel(){
        mModel = new ProfileModel();
        mModel.addListener(this);
        mIsLoad.postValue(true);
    }

    public void start(String login) {
        mModel.start(login);
    }

    @Override
    public void profileListLoad(Profile profile) {
        mIsLoad.postValue(false);
        mProfile.postValue(profile);
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

    public MutableLiveData<Profile> getProfile(){
        return mProfile;
    }
    public MutableLiveData<String> getError(){
        return mError;
    }
    public MutableLiveData<Boolean> getIsLoad(){
        return mIsLoad;
    }
}
