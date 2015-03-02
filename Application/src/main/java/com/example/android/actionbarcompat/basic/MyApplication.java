package com.example.android.actionbarcompat.basic;

import android.app.Application;
import android.content.Context;

/**
 * Created by steffenfb on 26/02/15.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance=this;

    }

    public static MyApplication getInstance(){

        return sInstance;
    }

    public static Context getContext(){

        return sInstance.getApplicationContext();
    }


}
