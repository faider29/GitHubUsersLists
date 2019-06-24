package ru.andrienko.githubuserslists.mvvm.model;

import android.util.Log;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.andrienko.githubuserslists.entity.Profile;
import ru.andrienko.githubuserslists.interfaces.ProfileModelListener;
import ru.andrienko.githubuserslists.network.NetworkRepository;


public class ProfileModel {

    private static String TAG = ProfileModel.class.getSimpleName();

    private List<ProfileModelListener> mListeners = new ArrayList<>();
    private Callback<JsonObject> mCallback;

    public ProfileModel(){
        initCallback();
    }

    public void start(String login){
        NetworkRepository.getInstance().getProfil(mCallback,login);
    }


    public void addListener(ProfileModelListener listener){
        mListeners.add(listener);
    }

    public void removeListener(ProfileModelListener listener){
        mListeners.remove(listener);
    }


    private void initCallback(){
        mCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "onResponse: " + call.request().toString() );
                Log.d(TAG, "onResponse: " + response.body());
                for (ProfileModelListener listener: mListeners){
                    listener.profileListLoad(Profile.getProfileFromJson(response.body()));
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onFailure: " + call.request().toString());
                Log.d(TAG, "onFailure: " + t.getMessage());
                for (ProfileModelListener listener: mListeners){
                    listener.error(t.getMessage());
                }

            }
        };
    }
}
