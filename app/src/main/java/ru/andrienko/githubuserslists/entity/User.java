package ru.andrienko.githubuserslists.entity;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim Andrienko
 * 6/16/19
 */
public class User implements Serializable {


    private String id;

    private String avatar_url;

    private String login;



    /**
     * Будет ли работать запрос если таких полей нет????7
     * @return
     */



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public static List<User> getUserFromJson(JsonArray response){
        List<User> userList = new ArrayList<>();
        Gson gson = new Gson();
        JsonArray array = response.getAsJsonArray();
        for (int i = 0; i < array.size(); i++){
            User user = gson.fromJson(array.get(i),User.class);
            userList.add(user);
        }
        return  userList;
    }

    @Override
    public String toString() {
        return "id: " + id + "avatar_url: " + avatar_url
                + "login: " + login;
    }
}
