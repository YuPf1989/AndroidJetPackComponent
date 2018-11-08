package com.rain.demo.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.rain.demo.room.WordRoomDb;
import com.rain.demo.room.dao.WordDao;
import com.rain.demo.room.entity.Word;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Author:rain
 * Date:2018/11/8 10:34
 * Description:
 * 用于管理数据的来源，从网络、数据库
 */
public class WordRepository {

    private final WordDao wordDao;
    private final LiveData<List<Word>> allWord;

    public WordRepository(Application application) {
        WordRoomDb db = WordRoomDb.instance(application);
        wordDao = db.wordDao();
        allWord = wordDao.getAllWord();
    }

    /**
     * 为allWord添加一个包装器
     * @return allWord
     */
    public LiveData<List<Word>> getAllWord() {
        return allWord;
    }

    /**
     * 为insert添加一个包装器,数据库操作不能在UI线程
     * @param word 插入的word
     */
    public void insert(Word word) {
        new InsertAsyncTask(wordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word,Void,Void> {
        private WordDao mAsycWordDao;

        private InsertAsyncTask(WordDao wordDao) {
            mAsycWordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsycWordDao.insert(words[0]);
            return null;
        }
    }


}
