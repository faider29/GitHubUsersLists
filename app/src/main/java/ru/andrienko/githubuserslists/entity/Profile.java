package ru.andrienko.githubuserslists.entity;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.Serializable;

/**
 * Created by Maxim Andrienko
 * 6/17/19
 */
public class Profile implements Serializable {

    private static String TAG = Profile.class.getSimpleName();

    private String name;

    private String location;

    private String followers;

    private String following;

    private String avatar_url;

    private String bio;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public static Profile getProfileFromJson(JsonObject response){
        Gson gson = new Gson();
        JsonObject object = response.getAsJsonObject();

        Log.d(TAG, "getProfileFromJson: " + response.getAsJsonObject());

        Profile profile = gson.fromJson(object.toString(),Profile.class);

        return  profile;
    }


    @Override
    public String toString() {
        return "name: " + name
                + "location: " + location
                + "followers: " + followers
                + "following: " + following
                + "avatar_url: " + avatar_url
                + "bio: " + bio;
    }

}
