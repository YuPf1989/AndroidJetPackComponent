package com.rain.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rain.demo.room.entity.Word;
import com.rain.demo.viewmodel.WordViewmodel;

import java.util.List;

/**
 * 参见：https://codelabs.developers.google.com/codelabs/android-room-with-a-view/#0
 */
public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    private RecyclerView recycler;
    private WordViewmodel wordViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, NewWordActivity.class), REQUEST_CODE);
            }
        });

        recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        final WordListAdapter adapter = new WordListAdapter(this);
        recycler.setAdapter(adapter);

        wordViewmodel = ViewModelProviders.of(this).get(WordViewmodel.class);
        wordViewmodel.getAllWord().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                adapter.setWords(words);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String s = data.getStringExtra(NewWordActivity.STRING_RESULT);
            wordViewmodel.insert(new Word(s));
        } else {
            Toast.makeText(this, "no new word", Toast.LENGTH_SHORT).show();
        }
    }
}
