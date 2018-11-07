package com.rain.androidjetpackcomponent.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Author:rain
 * Date:2018/11/7 11:12
 * Description:
 */
@Dao
public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Book... books);

    @Query("DELETE FROM book ")
    void delete();

    @Query("SELECT * FROM book ")
    List<Book> getAll();
}
