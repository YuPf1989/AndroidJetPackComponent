package com.rain.demo.room;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.rain.demo.room.dao.WordDao;
import com.rain.demo.room.entity.Word;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Author:rain
 * Date:2018/11/8 10:22
 * Description:
 * 创建数据库操作类
 */
@Database(entities = {Word.class}, version = 1,exportSchema = false)
public abstract class WordRoomDb extends RoomDatabase {
    private static final String TAG = "WordRoomDb";
    private static Context context;

    public abstract WordDao wordDao();

    // 创建单例
    public static WordRoomDb instance(Application context) {
        WordRoomDb.context = context;
        return WordRoomDb.InstanceHolder.db;
    }

    private static class InstanceHolder {
        private static WordRoomDb db = Room.databaseBuilder(context, WordRoomDb.class, "word")
                .addCallback(callback)// 数据库创建完成的时候发生调用
                .build();
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.e(TAG, "onOpen: ");
            new PopulateDbAsync(InstanceHolder.db).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final WordDao wordDao;

        public PopulateDbAsync(WordRoomDb db) {
            wordDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... words) {
            wordDao.deleteAll();
            for (int i = 0; i < 20; i++) {
                wordDao.insert(new Word("item" + i));
            }
            return null;
        }
    }
}
