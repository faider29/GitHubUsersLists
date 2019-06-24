package ru.andrienko.githubuserslists.mvvm.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.andrienko.githubuserslists.R;
import ru.andrienko.githubuserslists.adapter.UsersAdapter;
import ru.andrienko.githubuserslists.entity.User;
import ru.andrienko.githubuserslists.mvvm.viewModel.UsersListViewModel;

/**
 * Created by Maxim Andrienko
 * 6/16/19
 */
public class FragmentUsers  extends Fragment {

    private static String TAG = FragmentUsers.class.getSimpleName();


    private UsersListViewModel mViewModel;

    private RecyclerView mRecyclerView;
    private UsersAdapter mAdapter;
    private List<User> mUserList = new ArrayList<>();
    private NavController mNavController;
    public static final String USER_KEY = "user_key";


    private View mToolbar;
    private TextView mLabel;
    private ProgressBar mProgressBar;
    private GridLayoutManager layoutManager;

    private boolean isLoading = true;
    private int visibleIteamCount, firstVisibleItemPosition, totallItemCount, previousTotal = 0;
    private int PAGE_ITEM = 5;
//    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_users,container,false);
//        mView = view;

        mToolbar = view.findViewById(R.id.fr_custom_toolbar);
        mLabel = view.findViewById(R.id.fr_label);
        mProgressBar = view.findViewById(R.id.progressBar);

        mAdapter = new UsersAdapter(getContext(),mUserList);

        mRecyclerView = view.findViewById(R.id.rv_users);
        layoutManager = new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListeners((position, user) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable(USER_KEY,user);

            mNavController = Navigation.findNavController(getActivity(),R.id.nav_host_fragment);
            Log.d(TAG, "onCreateView: " + bundle.size());
            mNavController.navigate(R.id.action_fragmentUsers_to_fragmentReadUsers,bundle);
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleIteamCount = layoutManager.getChildCount();
                totallItemCount = layoutManager.getItemCount();
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                /**
                 * isLoad = true
                 */

                if (dy>0){

                    if (isLoading){

                        if (totallItemCount > previousTotal) {
                            isLoading = false;
                            previousTotal = totallItemCount;
//                            mProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }

                    if (!isLoading && (totallItemCount -visibleIteamCount) <= (firstVisibleItemPosition + PAGE_ITEM)){
//                        mProgressBar.setVisibility(ProgressBar.VISIBLE);
                        getNext();
                        isLoading = true;
                    }
                }
            }
        });

        observe();

        return view;
    }

    private void getNext() {
        mViewModel.getNext();
    }



    private void observe(){
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(UsersListViewModel.class);
        LiveData<List<User>> users = mViewModel.getUsers();
        users.observe(getActivity(), userList ->{
            mUserList.addAll(userList);
            mAdapter.notifyDataSetChanged();
        });

        LiveData<String> error = mViewModel.getError();
        error.observe(getActivity(),errorMessage ->{

        });

        LiveData<Boolean> isLoad = mViewModel.getShowLoad();
        isLoad.observe(getActivity(),showLoad ->{

            if (showLoad ){
                mProgressBar.setVisibility(View.VISIBLE);
            }else mProgressBar.setVisibility(View.INVISIBLE);

        });
    }

}
