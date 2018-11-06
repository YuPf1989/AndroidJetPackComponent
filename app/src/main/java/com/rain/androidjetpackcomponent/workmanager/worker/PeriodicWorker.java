package com.rain.androidjetpackcomponent.workmanager.worker;

import android.content.Context;
import android.util.Log;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Author:rain
 * Date:2018/11/5 10:28
 * Description:
 */
public class PeriodicWorker extends Worker {
    private static final String TAG  = "PeriodicWorker";

    public PeriodicWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
//        SystemClock.sleep(2000);
        Log.e(TAG, "doWork: "+Thread.currentThread());
        return Result.SUCCESS;
    }
}
