package com.example.mynote;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {

    public static final String TABLE_NAME="NOTE";
    public static final String COLUMN_ID="id";
    public static final String TITLE="title";
    public static final String TEXT="text";

    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TITLE+" TEXT,"+
            TEXT+" TEXT)";


    private int id;
    private String title,text;

    public Note() {
    }

    protected Note(Parcel in) {
        id = in.readInt();
        title = in.readString();
        text = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(text);
    }
}
