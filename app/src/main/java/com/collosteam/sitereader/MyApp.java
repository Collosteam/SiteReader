package com.collosteam.sitereader;

import android.app.Application;

import com.collosteam.sitereader.data.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Collos on 9/1/2014.
 */
public class MyApp extends Application {

    public static Map<Integer,User> userMap = new HashMap<Integer, User>();



    @Override
    public void onCreate() {
        super.onCreate();



    }
}
