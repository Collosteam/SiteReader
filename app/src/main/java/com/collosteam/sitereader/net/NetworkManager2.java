package com.collosteam.sitereader.net;

import com.collosteam.sitereader.data.Responceitem;

import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;

/**
 * Created by Admin on 9/22/2014.
 */
public class NetworkManager2 {
    RestAdapter adapter;
    API mAPI;


    public NetworkManager2() {

        /*adapter = new RestAdapter.Builder().setEndpoint("http://translate.collosteam.com").build();*/
        // or this method
        adapter = new RestAdapter.Builder().setEndpoint("http://translate.collosteam.com").build();
        mAPI = adapter.create(API.class);
    }


    public boolean checkName(String s) {

        Responceitem checkLogin = mAPI.checkName("checkLogin", s);
        return checkLogin.getId() == 0;
    }

    interface API {
        @FormUrlEncoded
        @GET("/signup.php")
        public Responceitem checkName(@Field("action") String action, @Field("name") String name);
    }
}
