package ru.andrienko.githubuserslists.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.andrienko.githubuserslists.R;
import ru.andrienko.githubuserslists.adapter.UsersAdapter;
import ru.andrienko.githubuserslists.entity.User;
import ru.andrienko.githubuserslists.network.NetworkRepository;

/**
 * Created by Maxim Andrienko
 * 6/16/19
 */
public class FragmentUsers  extends Fragment {

    private static String TAG = FragmentUsers.class.getSimpleName();

    private Callback<JsonArray> mCallback;

    private RecyclerView mRecyclerView;
    private UsersAdapter mAdapter;
    private List<User> mUserList = new ArrayList<>();
    private NavController mNavController;

    private NetworkRepository mNetworkRepository = new NetworkRepository();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_users,container,false);

        mAdapter = new UsersAdapter(getContext(),mUserList);

        mRecyclerView = view.findViewById(R.id.rv_users);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        initCallback();

        mNetworkRepository.getUsers(mCallback);
        return view;
    }

    private void initCallback(){

        mCallback = new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                Log.d(TAG, "onResponse: " + call.request().toString() );
                mUserList.addAll(User.getUserFromJson(response.body()));
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {

                t.printStackTrace();
                Toast.makeText(getContext(), "Error!!!", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public void onIteamClick(){

    }



}
