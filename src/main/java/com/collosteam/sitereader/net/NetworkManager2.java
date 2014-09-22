package com.collosteam.sitereader.net;

import com.collosteam.sitereader.data.ResponseItem;

import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Collos on 9/22/2014.
 */
public class NetworkManager2 {

    RestAdapter adapter;
    API mAPI;

    public NetworkManager2() {
        adapter = new RestAdapter.Builder().setEndpoint("http://translate.collosteam.com").setLogLevel(RestAdapter.LogLevel.FULL).build();
        mAPI = adapter.create(API.class);
    }


    public boolean checkName(String s) {
        ResponseItem checkLogin = mAPI.checkName("checkLogin", s);
        if (checkLogin == null)
            return false;
        return checkLogin.getId() == 0;
    }

    interface API {

        @FormUrlEncoded
        @POST("/signup.php")
        public ResponseItem checkName(@Field("action") String action, @Field("login") String name);

    }

}
