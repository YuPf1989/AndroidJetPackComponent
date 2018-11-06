package com.rain.androidjetpackcomponent.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.rain.androidjetpackcomponent.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author:rain
 * Date:2018/11/6 10:32
 * Description:
 * LifeCycle ： 与Activity和Fragment的生命周期有关
 * LiveData ：异步可订阅数据，也是生命周期感知
 * ViewModel ：视图数据持有模型，也是生命周期感知
 * Room ：SQLite抽象层，用于简化SQLite数据存储
 * 参见：https://www.jb51.net/article/144759.htm
 * https://blog.csdn.net/guiying712/article/details/81176039
 * AppCompatActivity和Fragment都实现了LifecycleOwner接口（Support Library 26.1.0之后的版本），所以可以直接拿来使用。
 * 如果自定义activity 继承于activity监听activity生命周期步骤：
 * 实现LifecycleOwner接口
 * 重写getLifecycle()方法
 * 手动标记生命周期的状态
 */
public class LifecycleActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);

        getLifecycle().addObserver(MyObserver.instance());
        tvResult = findViewById(R.id.tv_result);
        findViewById(R.id.btn_creat_observer).setOnClickListener(this);
        findViewById(R.id.btn_observe_activity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_creat_observer:

                break;

            case R.id.btn_observe_activity:
                startActivity(new Intent(this, MyActivity.class));
                break;

            default:
        }
    }
}
