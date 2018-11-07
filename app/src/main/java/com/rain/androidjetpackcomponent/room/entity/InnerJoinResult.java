package com.rain.androidjetpackcomponent.room.entity;

import androidx.room.ColumnInfo;

/**
 * Author:rain
 * Date:2018/11/7 14:49
 * Description:
 * 内联查询的结果bean
 */
public class InnerJoinResult {
    @ColumnInfo(name = "user_id")
    public int userId;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    public String title;

    @Override
    public String toString() {
        return "InnerJoinResult{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
