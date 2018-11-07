package com.rain.androidjetpackcomponent;

import android.app.Application;

/**
 * Author:rain
 * Date:2018/11/6 16:30
 * Description:
 */
public class MyApp extends Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }
}
