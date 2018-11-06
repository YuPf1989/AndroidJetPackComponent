package com.rain.androidjetpackcomponent.lifecycle;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rain.androidjetpackcomponent.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;

/**
 * Author:rain
 * Date:2018/11/6 10:32
 * Description:
 * LifeCycle ： 与Activity和Fragment的生命周期有关
 * LiveData ：异步可订阅数据，也是生命周期感知
 * ViewModel ：视图数据持有模型，也是生命周期感知
 * Room ：SQLite抽象层，用于简化SQLite数据存储
 * 参见：https://blog.csdn.net/g6uqwseseo/article/details/72621241
 * 由于目前LifeCycle处于alpha阶段，所以Fragment和AppCompatActivity并不会实现这些方法，
 * 在此之前，可以使用LifecycleActivity和LifecycleFragment。等LifeCycle趋于稳定后，Fragment和AppCompatActivity会默认实现这些。
 */
public class LifecycleActivity extends AppCompatActivity {

    // 手动标记生命周期
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        setContentView(R.layout.activity_lifecycle);

        getLifecycle().addObserver(MyObserver.instance());
        tvResult = findViewById(R.id.tv_result);
        findViewById(R.id.btn_creat_observer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyObserver.instance();
            }
        });
    }
}
