package com.rain.androidjetpackcomponent.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * Author:rain
 * Date:2018/11/6 11:30
 * Description:
 */
public class MyObserver implements LifecycleObserver {
    private static final String TAG  = "MyObserver";

    private MyObserver() {
        Log.e(TAG, "instance: ");
    }

    private static class InstanceHolder {
        private static MyObserver instance = new MyObserver();
    }

    public static MyObserver instance() {
        return InstanceHolder.instance;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreat() {
        Log.e(TAG, "onCreat: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        Log.e(TAG, "onStart: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Log.e(TAG, "onResume: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Log.e(TAG, "onPause: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {
        Log.e(TAG, "onStop: ");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
    }
}
