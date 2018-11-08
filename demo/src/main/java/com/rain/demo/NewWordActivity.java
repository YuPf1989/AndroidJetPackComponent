package com.rain.demo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Author:rain
 * Date:2018/11/8 14:36
 * Description:
 */
public class NewWordActivity extends AppCompatActivity {
    public static final String STRING_RESULT = "STRING_RESULT";

    private EditText mInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newword);

        mInput = findViewById(R.id.et_input);
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                if (TextUtils.isEmpty(mInput.getText())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String s = mInput.getText().toString();
                    resultIntent.putExtra(STRING_RESULT, s);
                    setResult(RESULT_OK, resultIntent);
                }
                finish();
            }
        });
    }
}
