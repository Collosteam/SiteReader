package com.collosteam.sitereader.data;

/**
 * Created by Collos on 9/1/2014.
 */
public class SempleUser extends AbstractUser {

    public SempleUser(String name, String pass, String email) {
        mName = name;
        mPass = pass;
        mEmail = email;
    }


    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getPass() {
        return mPass;
    }

    @Override
    public String getEmail() {
        return mEmail;
    }
}
