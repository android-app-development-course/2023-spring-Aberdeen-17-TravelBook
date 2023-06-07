package com.example.login;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "password text)";

    public static final String TABLE_COMMENTS = "comments";
    public  static final String COLUMN_NEWS_TITLE = "img";
    public  static final String COLUMN_NEWS_user = "user";
    public  static final String COLUMN_ID = "id";
    // 创建评论表的 SQL 语句
    public static final String CREATE_COMMENTS_TABLE = "create table comments ("
            + "id integer primary key autoincrement,"
            + "img text,"
            + "user text)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_COMMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
