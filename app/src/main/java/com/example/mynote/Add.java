package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Add extends AppCompatActivity {
    private ImageButton mIbtn;
    private EditText mEttitle,mEttext;
    DbHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mIbtn=findViewById(R.id.iv_save);
        mEttitle=findViewById(R.id.et_title);
        mEttext=findViewById(R.id.et_text);
        mIbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=mEttitle.getText().toString().trim();
                String text=mEttext.getText().toString().trim();
                savetoDb(title,text);




            }
        });

    }

    private void savetoDb(String title,String text) {
        helper=new DbHelper(this);
        helper.insertNote(title,text);

        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        finish();

    }
}
