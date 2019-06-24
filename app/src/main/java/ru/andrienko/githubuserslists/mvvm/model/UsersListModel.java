package ru.andrienko.githubuserslists.mvvm.model;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.andrienko.githubuserslists.entity.User;
import ru.andrienko.githubuserslists.interfaces.UsersModelListener;
import ru.andrienko.githubuserslists.network.NetworkRepository;

/**
 * Created by Maxim Andrienko
 * 6/18/19
 */
public class UsersListModel {

    private static String TAG = UsersListModel.class.getSimpleName();
    private List<UsersModelListener> mListeners = new ArrayList<>();

    private Callback<JsonArray> mCallback;
    private int mOffset = 0;

    public UsersListModel(){
        initCallback();
    }

    public void start(){

        for (UsersModelListener listener: mListeners){
            listener.showLoad(true);
        }

        NetworkRepository.getInstance().getUsers(mCallback, mOffset);
    }

    public void addListener(UsersModelListener listener){
        mListeners.add(listener);
    }

    public void removeListener(UsersModelListener listener){
        mListeners.remove(listener);
    }

    private void initCallback(){

        mCallback = new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.d(TAG, "onResponse: " + call.request().toString() );

                for (UsersModelListener listener: mListeners){
                    listener.usersListLoad(User.getUserFromJson(response.body()));
                    listener.showLoad(false);
                }


            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                t.printStackTrace();
                for (UsersModelListener listener: mListeners){
                    listener.error(t.getMessage());
                    listener.showLoad(false);
                }
//                Toast.makeText(getContext(), "Check your connection", Toast.LENGTH_SHORT).show();
            }
        };

    }

    public void getNext() {
        mOffset += 46;
        for (UsersModelListener listener: mListeners){
            listener.showLoad(true);
        }
        NetworkRepository.getInstance().getUsers(mCallback, mOffset);

    }

}
