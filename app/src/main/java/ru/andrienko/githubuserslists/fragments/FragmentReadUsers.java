package ru.andrienko.githubuserslists.fragments;

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


import com.squareup.picasso.Picasso;

import ru.andrienko.githubuserslists.R;
import ru.andrienko.githubuserslists.entity.User;

/**
 * Created by Maxim Andrienko
 * 6/16/19
 */
public class FragmentReadUsers extends Fragment {


    public static final String USER_KEY = "user_key";
    private User user;


    private ImageView mAvatar;
    private TextView mlogin;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_read_user,container,false);
        user = (User) getArguments().getSerializable(USER_KEY);

        mAvatar = view.findViewById(R.id.iv_read_user);
        Picasso.get()
                .load(user.getAvatar_url())
                .error(R.drawable.error)
                .into(getAvatar());
        mlogin = view.findViewById(R.id.tv_name_user);
        mlogin.setText(user.getLogin());

//        User user = (User) savedInstanceState.get("user_key");
//        Log.d("onCreateView", "onCreateView: " + user.getLogin());

        return view;
    }
    private ImageView getAvatar() {
        return mAvatar;
    }
}
