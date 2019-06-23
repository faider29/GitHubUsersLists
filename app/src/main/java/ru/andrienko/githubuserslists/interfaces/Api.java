package ru.andrienko.githubuserslists.interfaces;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Maxim Andrienko
 * 6/16/19
 */
public interface Api {


    @GET("users")
    Call<JsonArray> getUsers (@Query("since") int since);

    @GET("users/{login}")
    Call<JsonObject> getProfil(@Path("login") String login);

}
