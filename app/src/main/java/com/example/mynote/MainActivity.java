package com.example.mynote;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button mBtn;
    DbHelper helper;
    RecyclerView mRv;
    Button mVtnupdate;
    Button mBtndelete;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100 && resultCode==RESULT_OK) {
            assert data != null;
            helper=new DbHelper(this);
            mRv=findViewById(R.id.rv);
            mRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            List<Note> list;
            list=helper.getNotes();
            NoteAdapter adapter=new NoteAdapter(MainActivity.this,list);
            mRv.setAdapter(adapter);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtn=findViewById(R.id.btn_add);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Add.class);
                startActivityForResult(intent,100);
            }
        });
        mRv=findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        List<Note> list;

        helper=new DbHelper(this);
        list=helper.getNotes();
        NoteAdapter adapter=new NoteAdapter(MainActivity.this,list);
        mRv.setAdapter(adapter);

//        mBtndelete=findViewById(R.id.btn_delete);
//        mBtndelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                helper=new DbHelper(MainActivity.this);
//                Note note;
//                helper.deleteNote(note);
//            }
//        });



    }
}
