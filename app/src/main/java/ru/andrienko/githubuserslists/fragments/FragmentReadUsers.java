package ru.andrienko.githubuserslists.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.andrienko.githubuserslists.R;

/**
 * Created by Maxim Andrienko
 * 6/16/19
 */
public class FragmentReadUsers extends Fragment {

    private ImageView mAvatar;
    private TextView mlogin;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_read_user,container,false);

        mAvatar = view.findViewById(R.id.iv_read_user);
        mlogin = view.findViewById(R.id.tv_name_user);

        return view;
    }
}
