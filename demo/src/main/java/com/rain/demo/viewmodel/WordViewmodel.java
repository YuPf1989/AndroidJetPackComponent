package com.rain.demo.viewmodel;

import android.app.Application;

import com.rain.demo.repository.WordRepository;
import com.rain.demo.room.entity.Word;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Author:rain
 * Date:2018/11/8 10:53
 * Description:
 * 连接UI和Repository的桥梁
 * 警告：不要将context传递到ViewModel实例中。不要在ViewModel中存储活动、片段或视图实例或它们的context
 */
public class WordViewmodel extends AndroidViewModel {

    private final WordRepository wordRepository;
    private final LiveData<List<Word>> allWord;

    public WordViewmodel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        allWord = wordRepository.getAllWord();
    }

    // 完全对UI隐藏细节
    public LiveData<List<Word>> getAllWord() {
        return allWord;
    }

    public void insert(Word word){
        wordRepository.insert(word);
    }
}
