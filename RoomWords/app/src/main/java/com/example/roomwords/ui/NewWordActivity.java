package com.example.roomwords.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roomwords.R;

public class NewWordActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_REPLY =
            "com.example.roomwords.REPLY";
    private EditText mTextAdd;
    private Button mButtonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mTextAdd = findViewById(R.id.text_add);
        mButtonSave = findViewById(R.id.button_save);
        mButtonSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(TextUtils.isEmpty(mTextAdd.getText())){
            setResult(RESULT_CANCELED, intent);
        }else {
            String word = mTextAdd.getText().toString();
            intent.putExtra(EXTRA_REPLY, word);
            setResult(RESULT_OK, intent);
        }

        finish();
    }
}
