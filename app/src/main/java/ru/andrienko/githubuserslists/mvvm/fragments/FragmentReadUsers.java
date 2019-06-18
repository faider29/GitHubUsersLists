package ru.andrienko.githubuserslists.mvvm.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.andrienko.githubuserslists.R;
import ru.andrienko.githubuserslists.entity.Profile;
import ru.andrienko.githubuserslists.entity.User;
import ru.andrienko.githubuserslists.network.NetworkRepository;

/**
 * Created by Maxim Andrienko
 * 6/16/19
 */
public class FragmentReadUsers extends Fragment {

    private static String TAG = FragmentReadUsers.class.getSimpleName();


    public static final String USER_KEY = "user_key";

    private User user;
    private Profile profile;


    private ImageView mAvatar;
    private TextView mlogin;
    private TextView mName;
    private TextView mFollowers;
    private TextView mFollowing;
    private TextView mBio;
    private TextView mLocation;

    private Callback<JsonObject> mCallback;


    private NetworkRepository mNetworkRepository = new NetworkRepository();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_read_user,container,false);
        user = (User) getArguments().getSerializable(USER_KEY);

        initCallback();

        mNetworkRepository.getProfil(mCallback,user.getLogin());

        return view;
    }

    private ImageView getAvatar() {
        return mAvatar;
    }

    private void initCallback(){
        mCallback = new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d(TAG, "onResponse: " + call.request().toString() );
                Log.d(TAG, "onResponse: " + response.body());

               profile =  Profile.getProfileFromJson(response.body());
                initView();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "onFailure: " + call.request().toString());
                Log.d(TAG, "onFailure: " + t.getMessage());

                Toast.makeText(getContext(),  "Check your connection", Toast.LENGTH_SHORT).show();

            }
        };
    }
    private void initView(){
        mAvatar = getActivity().findViewById(R.id.iv_read_user);
        Picasso.get()
                .load(user.getAvatar_url())
                .error(R.drawable.error)
                .into(getAvatar());

        mlogin = getActivity().findViewById(R.id.tv_read_login);
        mlogin.setText(user.getLogin());


        mFollowers = getActivity().findViewById(R.id.tv_read_followers);
        mFollowers.setText("Followers: " + profile.getFollowers());


        mFollowing = getActivity().findViewById(R.id.tv_read_following);
        mFollowing.setText("Following: " + profile.getFollowing());

        mName = getActivity().findViewById(R.id.fr_read_name);
        mName.setText(profile.getName());

        mBio = getActivity().findViewById(R.id.fr_read_bio);
        mBio.setText(profile.getBio());

        mLocation = getActivity().findViewById(R.id.fr_read_location);
        mLocation.setText(profile.getLocation());
    }
}
