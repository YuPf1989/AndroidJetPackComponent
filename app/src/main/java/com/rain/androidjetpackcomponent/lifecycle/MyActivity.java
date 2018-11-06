package com.rain.androidjetpackcomponent.lifecycle;

import android.app.Activity;
import android.os.Bundle;

import com.rain.androidjetpackcomponent.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/**
 * Author:rain
 * Date:2018/11/6 14:50
 * Description:
 * 自定义activity实现lifecycle
 * 因为lifecycle只实现于FragmentActivity和Fragment
 */
public class MyActivity extends Activity implements LifecycleOwner {
    private LifecycleRegistry mLifecycleRegistry;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mLifecycleRegistry = new LifecycleRegistry(this);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);

        mLifecycleRegistry.addObserver(MyCustomObserver.instance());
    }

    @Override
    public void onStart() {
        super.onStart();
        //标记状态
        mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //标记状态
        mLifecycleRegistry.markState(Lifecycle.State.RESUMED);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //标记状态
        mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }
}
