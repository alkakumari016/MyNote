package com.example.mynote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Add extends AppCompatActivity {
    DbHelper helper;
    private ImageButton mIbtn;
    private EditText mEttitle, mEttext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        String intentType = getIntent().getStringExtra("EXTRA_TYPE");



        mIbtn = findViewById(R.id.iv_save);
        mEttitle = findViewById(R.id.et_title);
        mEttext = findViewById(R.id.et_text);


        if (intentType.equals("ADD")){

            mIbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title = mEttitle.getText().toString().trim();
                    String text = mEttext.getText().toString().trim();
                    savetoDb(title, text);


                }
            });
        }else if (intentType.equals("UPDATE")){

            final Note note = getIntent().getParcelableExtra("NOTE");

            mEttitle.setText(note.getTitle());
            mEttext.setText(note.getText());
            mIbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helper=new DbHelper(Add.this);
                    Note note1=new Note();
                    String title=mEttitle.getText().toString().trim();
                    String text=mEttext.getText().toString().trim();
                    note1.setTitle(title);
                    note1.setText(text);
                    helper.updateNote(note1);
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();

                }
            });

        }


    }

    private void savetoDb(String title, String text) {
        helper = new DbHelper(this);
        helper.insertNote(title, text);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();

    }
}
