package com.rain.androidjetpackcomponent.workmanager.worker;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Author:rain
 * Date:2018/11/5 14:57
 * Description:
 */
public class WorkerD extends Worker {
    private static final String TAG  = WorkerD.class.getSimpleName();
    public WorkerD(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        SystemClock.sleep(2000);
        Log.e(TAG, TAG+":doWork: ");
        return Result.SUCCESS;
    }
}
