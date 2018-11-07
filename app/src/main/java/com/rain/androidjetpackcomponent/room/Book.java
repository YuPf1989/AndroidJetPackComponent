package com.rain.androidjetpackcomponent.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Author:rain
 * Date:2018/11/6 16:49
 * Description:
 * 定义对象之间的关系
 * Book中的userId是User中的外键
 * 参见：https://blog.csdn.net/qq_37475168/article/details/79790848
 * 每个用户拥有哪些书籍
 */
@Entity(foreignKeys = {@ForeignKey(entity = User.class,// 关联的表
        parentColumns = "id",// 父列名
        childColumns = "user_id",// 子列名
        onUpdate = ForeignKey.CASCADE,// 父列列值发生变化时，子列的处理方式（我都用的级联，省事儿）
        onDelete = ForeignKey.CASCADE
)}, indices = @Index(value = "user_id"))
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int bookId;

    public String title;

    @ColumnInfo(name = "user_id")
    public int userId;

    public Book(String title, int userId) {
        this.title = title;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                '}';
    }
}
