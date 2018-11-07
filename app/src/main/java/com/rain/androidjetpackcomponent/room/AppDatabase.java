package com.rain.androidjetpackcomponent.room;

import com.rain.androidjetpackcomponent.MyApp;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Author:rain
 * Date:2018/11/6 16:22
 * Description:
 */
@Database(entities = {User.class,Book.class}, version = 1,exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    private static class InstanceHolder{
        private static AppDatabase instance = Room.databaseBuilder(MyApp.getInstance(), AppDatabase.class, "user.db")
                .addMigrations(new Migration(1,1) { // 数据库升级用
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        // 执行一些数据库升级操作
                    }
                })
                .allowMainThreadQueries()// 允许在主线程工作
                .build();

    }

    public static AppDatabase instance() {
        return AppDatabase.InstanceHolder.instance;
    }

    public abstract UserDao userDao();
    public abstract BookDao bookDao();
}
