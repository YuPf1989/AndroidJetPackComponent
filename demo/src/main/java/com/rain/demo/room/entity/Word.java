package com.rain.demo.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Author:rain
 * Date:2018/11/8 10:13
 * Description:
 * 数据库word表
 */
@Entity
public class Word {
    @PrimaryKey
    @NonNull
    public String word;

    public Word(String word) {
        this.word = word;
    }
}
