package ru.andrienko.githubuserslists.mvvm.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;


import java.util.Objects;

import retrofit2.Callback;
import ru.andrienko.githubuserslists.R;
import ru.andrienko.githubuserslists.entity.Profile;
import ru.andrienko.githubuserslists.entity.User;
import ru.andrienko.githubuserslists.mvvm.viewModel.ProfileViewModel;

/**
 * Created by Maxim Andrienko
 * 6/16/19
 */
public class FragmentReadProfile extends Fragment {

    private static String TAG = FragmentReadProfile.class.getSimpleName();


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

    private View mToolbar;
    private ImageView mBtnBack;
    private TextView mLabel;

    private ProfileViewModel mViewModel;
    private Callback<JsonObject> mCallback;

    private View mView;

//    private NetworkRepository mNetworkRepository = new NetworkRepository();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fr_read_user,container,false);
        user = (User) getArguments().getSerializable(USER_KEY);

//        initCallback();

        initView();
        observe();

        return mView;
    }

    private ImageView getAvatar() {
        return mAvatar;
    }

    private void observe(){
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(ProfileViewModel.class);
        LiveData<Profile> profile = mViewModel.getProfile();
        profile.observe(getActivity(),profiles -> {
            this.profile = profiles;
            updateViews();
        });
        mViewModel.start(user.getLogin());
    }

    private void initView(){

        mToolbar = mView.findViewById(R.id.fr_read_custom_toolbar);
        mBtnBack = mView.findViewById(R.id.fr_read_btn_back);
        mBtnBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        mLabel = mView.findViewById(R.id.fr_read_label);


        mAvatar = mView.findViewById(R.id.iv_read_user);


        mlogin = mView.findViewById(R.id.tv_read_login);


        mFollowers = mView.findViewById(R.id.tv_read_followers);


        mFollowing = mView.findViewById(R.id.tv_read_following);

        mName = mView.findViewById(R.id.fr_read_name);

        mBio = mView.findViewById(R.id.fr_read_bio);

        mLocation = mView.findViewById(R.id.fr_read_location);
    }

    private void updateViews(){

        Picasso.get()
                .load(user.getAvatar_url())
                .error(R.drawable.error)
                .into(getAvatar());

        mlogin.setText(user.getLogin());

        mFollowers.setText("Followers: " + profile.getFollowers());

        mFollowing.setText("Following: " + profile.getFollowing());

        mName.setText(profile.getName());

        mBio.setText(profile.getBio());

        mLocation.setText(profile.getLocation());

    }
}
