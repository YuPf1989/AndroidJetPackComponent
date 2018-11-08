package com.rain.demo.room.dao;

import com.rain.demo.room.entity.Word;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Author:rain
 * Date:2018/11/8 10:16
 * Description:
 * 操作word表的dao
 * LiveData具有生命周期感知
 */
@Dao
public interface WordDao {
    @Insert
    void insert(Word word);
    @Query("DELETE FROM word")
    void deleteAll();


    @Query("SELECT * FROM word ORDER BY word ASC")
    LiveData<List<Word>> getAllWord();// LiveData是一个与生命周期相关的数据包装类
}
