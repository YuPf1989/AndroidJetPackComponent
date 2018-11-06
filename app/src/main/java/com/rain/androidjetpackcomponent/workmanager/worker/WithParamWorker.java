package com.rain.androidjetpackcomponent.workmanager.worker;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Author:rain
 * Date:2018/11/5 14:57
 * Description:
 */
public class WithParamWorker extends Worker {
    private static final String TAG  = WithParamWorker.class.getSimpleName();
    public WithParamWorker(Context context, WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @Override
    public Result doWork() {
        SystemClock.sleep(2000);
        int key_1 = getInputData().getInt("KEY_1", 0);
        int key_2 = getInputData().getInt("KEY_2", 0);
        int key_3 = getInputData().getInt("KEY_3", 0);
        int result = sum(key_1,key_2,key_3);
        Data output = new Data.Builder().put("result", result).build();
        setOutputData(output);
        Log.e(TAG, TAG+":doWork:result: "+result);
        Log.e(TAG, TAG+":doWork:key_1: "+key_1);
        return Result.SUCCESS;
    }

    private int sum(int... value) {
        int result = 0;
        for (int i = 0; i < value.length; i++) {
            result += value[i];
        }
        return result;
    }
}
