package com.rain.androidjetpackcomponent.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Author:rain
 * Date:2018/11/6 16:10
 * Description:
 * 创建数据库的表
 */
// 标记当前索引，且索引唯一
@Entity(indices = {@Index(value = {"first_name", "last_name"},unique = true)})
public class User {
    // 主键
    @PrimaryKey
    public int id;

    // name = "first_name"命名当前列，默认为当前属性名
    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    // 表示不加入表列
    @Ignore
    public int age;

    public User(int id,String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
