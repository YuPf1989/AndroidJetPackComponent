package com.rain.androidjetpackcomponent.workmanager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.rain.androidjetpackcomponent.R;
import com.rain.androidjetpackcomponent.workmanager.worker.PeriodicWorker;
import com.rain.androidjetpackcomponent.workmanager.worker.TestWorker;
import com.rain.androidjetpackcomponent.workmanager.worker.WithParamWorker;
import com.rain.androidjetpackcomponent.workmanager.worker.WorkerA;
import com.rain.androidjetpackcomponent.workmanager.worker.WorkerB;
import com.rain.androidjetpackcomponent.workmanager.worker.WorkerC;
import com.rain.androidjetpackcomponent.workmanager.worker.WorkerD;
import com.rain.androidjetpackcomponent.workmanager.worker.WorkerE;

import java.util.concurrent.TimeUnit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;

/**
 * Author:rain
 * Date:2018/11/2 16:19
 * Description:
 * WorkManager使用:
 * WorkManager适用于那些即使应用程序退出，系统也能够保证这个任务正常运行的场景，
 * 比如将应用程序数据上传到服务器。它不适用于应用进程内的后台工作，如果应用进程消失，就可以安全地终止，对于这种情况，推荐你使用线程池
 */
public class WorkmanagerActivity extends AppCompatActivity {

    private TextView observer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workmanager);

        observer = findViewById(R.id.tv_observer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.worker_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_one_work:
                OneTimeWorkRequest workRequest = doWork();
                // todo 少导入一个包，不知道什么包
                WorkManager.getInstance().getStatusByIdLiveData(workRequest.getId());
                break;

            case R.id.action_one_work_with_constrains:
                doWorkWithConstrains();
                break;

            case R.id.action_cancel_work:
                OneTimeWorkRequest workRequest2 = doWork();
                WorkManager.getInstance().cancelWorkById(workRequest2.getId());
                break;

            case R.id.action_periodic_work:
                doPeriodWork();
                break;

            case R.id.action_chain_work:
                chainWork();
                break;

            case R.id.action_param_work:
                withParamWork();
                break;

            case R.id.action_cancel_allwork:
                WorkManager.getInstance().cancelAllWork();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * worker 传值
     */
    private void withParamWork() {
        Data data = new Data.Builder()
                .put("KEY_1", 2)
                .put("KEY_2", 3)
                .put("KEY_3", 4)
                .build();
        OneTimeWorkRequest paramWork = new OneTimeWorkRequest.Builder(WithParamWorker.class)
                .setInputData(data)
                .build();
        WorkManager.getInstance().enqueue(paramWork);
        WorkManager.getInstance().getStatusByIdLiveData(paramWork.getId());
    }

    private void chainWork() {
        OneTimeWorkRequest workA = new OneTimeWorkRequest.Builder(WorkerA.class).build();
        OneTimeWorkRequest workB = new OneTimeWorkRequest.Builder(WorkerB.class).build();
        OneTimeWorkRequest workC = new OneTimeWorkRequest.Builder(WorkerC.class).build();
        OneTimeWorkRequest workD = new OneTimeWorkRequest.Builder(WorkerD.class).build();
        OneTimeWorkRequest workE = new OneTimeWorkRequest.Builder(WorkerE.class).build();
        WorkContinuation chain1 = WorkManager.getInstance().beginWith(workA).then(workB);
        WorkContinuation chain2 = WorkManager.getInstance().beginWith(workC).then(workD);
        WorkContinuation.combine(chain1, chain2).then(workE).enqueue();
    }

    /**
     * 执行周期性任务
     * todo 测试存在问题
     */
    private void doPeriodWork() {
        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(PeriodicWorker.class, 2, TimeUnit.SECONDS).build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);

    }

    private OneTimeWorkRequest doWork() {
        // 创建workerRequest
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker.class).build();
        // 将workerRequest添加到队列
        WorkManager.getInstance().enqueue(workRequest);
        return workRequest;
    }

    private OneTimeWorkRequest doWorkWithConstrains() {
        // 在设备网络连接、充电、空闲的时候
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                .setRequiresDeviceIdle(true)
                .build();
        // 创建workerRequest
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker.class)
                .setConstraints(constraints)
                .build();
        // 将workerRequest添加到队列
        WorkManager.getInstance().enqueue(workRequest);
        return workRequest;
    }
}
