package com.collosteam.sitereader;

/**
 * Created by Collos on 9/1/2014.
 */
public class Singleton {

    private static Singleton singleton;


    private Singleton(){}


    public static Singleton newInstance(){

        if(singleton == null){
            singleton = new Singleton();
        }

        return singleton;
    }

}
