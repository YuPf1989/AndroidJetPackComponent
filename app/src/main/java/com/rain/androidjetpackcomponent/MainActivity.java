package com.rain.androidjetpackcomponent;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.rain.androidjetpackcomponent.lifecycle.LifecycleActivity;
import com.rain.androidjetpackcomponent.room.RoomActivity;
import com.rain.androidjetpackcomponent.workmanager.WorkmanagerActivity;

/**
 * 安卓架构组件
 * 参见：
 * https://blog.csdn.net/guiying712/article/details/78474177
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_workmanager).setOnClickListener(this);
        findViewById(R.id.btn_lifecycle).setOnClickListener(this);
        findViewById(R.id.btn_room).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_workmanager:
                startActivity(new Intent(this,WorkmanagerActivity.class));
                break;

            case R.id.btn_lifecycle:
                startActivity(new Intent(this,LifecycleActivity.class));
                break;

            case R.id.btn_room:
                startActivity(new Intent(this,RoomActivity.class));
                break;


            default:
        }
    }
}
