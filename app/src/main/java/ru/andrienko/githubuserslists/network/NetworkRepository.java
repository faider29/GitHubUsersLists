package ru.andrienko.githubuserslists.network;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.andrienko.githubuserslists.Consts;
import ru.andrienko.githubuserslists.interfaces.Api;

public class NetworkRepository {

    private static NetworkRepository sRepository;

    public static NetworkRepository getInstance(){
        if (sRepository == null)
            sRepository = new NetworkRepository();
        return  sRepository;
    }

    public void getUsers(Callback<JsonArray> callback, int offset){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit =  builder.build();

        final Api api = retrofit.create(Api.class);
        Call<JsonArray> j = api.getUsers(offset);
        j.enqueue(callback);
    }

    public void getProfil(Callback<JsonObject> callback, String login){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        final  Api api = retrofit.create(Api.class);

        Call<JsonObject> j = api.getProfil(login);
        j.enqueue(callback);
    }
}
