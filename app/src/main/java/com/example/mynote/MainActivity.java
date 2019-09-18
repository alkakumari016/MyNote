package com.example.mynote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnItemClickListener {

    Button mBtn,mBtnupdate,mBtndelete;
    DbHelper helper;
    RecyclerView mRv;

    TextView mTvNoItems;

    private static int REQ_CODE_ADD = 100,REQ_CODE_UPDATE=200;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_ADD && resultCode == RESULT_OK) {
            assert data != null;

            setupAdapter();

        }
        if(requestCode == REQ_CODE_UPDATE && resultCode == RESULT_OK) {
            assert data!=null;
            setupAdapter();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvNoItems = findViewById(R.id.tv_no_item_found);
        mBtn = findViewById(R.id.btn_add);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                intent.putExtra("EXTRA_TYPE","ADD");
                startActivityForResult(intent, REQ_CODE_ADD);
            }
        });



        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        setupAdapter();


    }

    private void setupAdapter() {
        List<Note> list;
        helper = new DbHelper(this);
        list = helper.getNotes();

        if (list.size() > 0){
            mTvNoItems.setVisibility(View.GONE);
            mRv.setVisibility(View.VISIBLE);
            NoteAdapter adapter = new NoteAdapter(MainActivity.this, list);
            adapter.setListener(this);
            mRv.setAdapter(adapter);

        }else {
            mTvNoItems.setVisibility(View.VISIBLE);
            mRv.setVisibility(View.GONE);
        }


    }

    @Override
    public void onUpdate(Note note) {

        Intent updateIntent = new Intent(MainActivity.this,Add.class);
        updateIntent.putExtra("EXTRA_TYPE","UPDATE");
        updateIntent.putExtra("NOTE",note);
        startActivityForResult(updateIntent,REQ_CODE_UPDATE);

    }


    @Override
    public void onDelete(Note note) {
        helper=new DbHelper(this);
        helper.deleteNote(note);
        setupAdapter();
    }
}
