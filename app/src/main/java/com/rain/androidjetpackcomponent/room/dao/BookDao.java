package com.rain.androidjetpackcomponent.room.dao;

import com.rain.androidjetpackcomponent.room.entity.Book;
import com.rain.androidjetpackcomponent.room.entity.InnerJoinResult;

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

    @Query("SELECT user_id,first_name,last_name,title FROM user INNER JOIN book ON user.id=book.user_id  ")
    List<InnerJoinResult> getBookFromUser();
}
