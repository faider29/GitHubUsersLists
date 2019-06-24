package ru.andrienko.githubuserslists.interfaces;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Api {

    @GET("users")
    Call<JsonArray> getUsers (@Query("since") int since);

    @GET("users/{login}")
    Call<JsonObject> getProfil(@Path("login") String login);

}
