package ru.andrienko.githubuserslists.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.andrienko.githubuserslists.Consts;
import ru.andrienko.githubuserslists.R;
import ru.andrienko.githubuserslists.entity.User;

/**
 * Created by Maxim Andrienko
 * 6/16/19
 */
public class UsersAdapter  extends RecyclerView.Adapter<UsersAdapter.UsersHolder> {

    private static String TAG = UsersAdapter.class.getSimpleName();

    private Context mContext;
    private List<User> mUserList;
    private List<OnItemClickListener> mOnItemClickListeners;

    public UsersAdapter(Context context,List<User> usersList){
        mContext = context;
        mUserList = usersList;
        mOnItemClickListeners = new ArrayList<>();

    }


    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_users,viewGroup,false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder usersHolder, int i) {

        usersHolder.setLogin(mUserList.get(i).getLogin());

        usersHolder.setClickListener(view ->{
            for (OnItemClickListener l : mOnItemClickListeners){
                l.onItemClick(i,mUserList.get(i));
            }
        });

//        Log.d(TAG, "onBindViewHolder: " + mUserList.get(i).getAvatar_url());

        Picasso.get()
                .load(mUserList.get(i).getAvatar_url())
                .error(R.drawable.error)
                .into(usersHolder.getAvatarImage());

    }

    @Override
    public int getItemCount() {
        return mUserList == null ? 0 : mUserList.size();
    }

    public void setOnItemClickListeners(OnItemClickListener listeners){
        mOnItemClickListeners.add(listeners);
    }

    public class UsersHolder extends RecyclerView.ViewHolder {

        private ImageView mAvatar;
        private TextView mLogin;
        private View mView;

        public UsersHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mAvatar = itemView.findViewById(R.id.iv_item_users);
            mLogin = itemView.findViewById(R.id.tv_item_users);


        }

        public void setLogin(String login) {
            mLogin.setText(login);
        }

        public void setAvatar(Drawable avatar) {
            mAvatar.setImageDrawable(avatar);
        }

        public ImageView getAvatarImage(){
            return mAvatar;
        }

        public void setClickListener(View.OnClickListener listener){
            mView.setOnClickListener(listener);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position, User user);
    }
}
