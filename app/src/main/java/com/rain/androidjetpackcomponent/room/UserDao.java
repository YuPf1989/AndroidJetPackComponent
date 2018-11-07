package com.rain.androidjetpackcomponent.room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Author:rain
 * Date:2018/11/6 16:14
 * Description:
 * 操作数据库的类
 */
@Dao
public interface UserDao {
    @Query("SELECT * FROM user ")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    LiveData<List<User>> loadAllbyIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);

    // 如果有冲突的话，进行替换
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    @Query("DELETE FROM user ")
    int delete();

    @Query("UPDATE user SET first_name = :firstName WHERE id = :id")
    void updateUserFirstname(String firstName, int id);

}
