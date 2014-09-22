package com.collosteam.sitereader.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Collos on 9/22/2014.
 */
public class ResponseItem {

    @SerializedName("response")
    public int id;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseItem{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
