package com.collosteam.sitereader.data;

import android.database.Cursor;

import com.collosteam.sitereader.db.DBColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Collos on 9/10/2014.
 */
public class UsersWraper implements DBColumns {


    public List<User> getData() {
        return users;
    }

    public List<User> users;

    public UsersWraper(Cursor cursor) {
        int nameID = cursor.getColumnIndex(COL_NAME);
        int emailID = cursor.getColumnIndex(COL_EMAIL);
        int passID = cursor.getColumnIndex(COL_PASS);

        users = new ArrayList<User>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(nameID);
                String pass = cursor.getString(passID);
                String email = cursor.getString(emailID);

                users.add(new SempleUser(name,pass,email));

                cursor.moveToNext();
            }
        }
    }










}
