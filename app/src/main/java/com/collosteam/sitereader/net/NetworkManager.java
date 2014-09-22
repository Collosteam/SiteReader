package com.collosteam.sitereader.net;

import android.content.Context;
import android.util.Log;

import com.collosteam.sitereader.data.Responceitem;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Admin on 9/22/2014.
 */
public class NetworkManager {

    private static final String TAG = "{NetworkManager}";
    private final String BASE_URL = "http://translate.collosteam.com";
    private URLConnection connection;


    Context context;

    public NetworkManager(Context context) {
        this.context = context;

    }

    public boolean checkName(String name) {

        String checkUrl = null;
        try {
            checkUrl = BASE_URL + "/signup.php?action=checkLogin&login=" + URLEncoder.encode(name, "UTF-8");
            Log.d(TAG, "check url-"+checkUrl);


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URL url = null;
        try {
            url = new URL(checkUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String json = readInputStreamAsString(in);

            Gson gson = new Gson();
            Responceitem responceitem = gson.fromJson(json, Responceitem.class);
            urlConnection.disconnect();
            Log.d(TAG, "responce item-"+responceitem);
            return responceitem.getId()==0;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return false;
    }

    public static String readInputStreamAsString(InputStream in)
            throws IOException {

        BufferedInputStream bis = new BufferedInputStream(in);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result = bis.read();
        while (result != -1) {
            byte b = (byte) result;
            buf.write(b);
            result = bis.read();
        }
        return buf.toString();
    }

}


