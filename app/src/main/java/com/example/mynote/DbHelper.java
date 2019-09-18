package com.example.mynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="NOTE.db";
    Context context;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Note.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertNote(String title,String text) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Note.TITLE,title);
        cv.put(Note.TEXT,text);

        long id=db.insert(Note.TABLE_NAME,null,cv);
        db.close();

        Toast.makeText(context,"Success",Toast.LENGTH_SHORT);
        return id;
    }

//    public int getNotesCount() {
//        String getQuery="SELECT * FROM "+Note.TABLE_NAME;
//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor cursor=db.rawQuery(getQuery,null);
//        int count=cursor.getCount();
//        cursor.close();
//        db.close();
//        return count;
   // }


    public void deleteNote(Note note) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Note.TABLE_NAME,Note.TEXT+"=?",new String[]{String.valueOf(note.getText())});
        db.close();
    }

    public int updateNote(Note note) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Note.TITLE,note.getTitle());
        cv.put(Note.TEXT,note.getText());

        return db.update(Note.TABLE_NAME,cv,Note.COLUMN_ID+" =?",new String[]{String.valueOf(note.getId())});
    }




    public List<Note> getNotes() {

        List<Note> notes=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();

        String getQuery="SELECT * FROM "+Note.TABLE_NAME;

        Cursor cursor=db.rawQuery(getQuery,null);

        if(cursor.moveToFirst()) {
            do {
                Note note=new Note();
                note.setTitle(cursor.getString(cursor.getColumnIndex(Note.TITLE)));
                note.setText(cursor.getString(cursor.getColumnIndex(Note.TEXT)));
                notes.add(note);
            }while (cursor.moveToNext());
        }

        return notes;

    }
}
