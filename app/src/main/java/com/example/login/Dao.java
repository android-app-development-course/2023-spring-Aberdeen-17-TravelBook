package com.example.login;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    private MyDatabaseHelper mySqliteHelper;
    public Dao(Context context) {
        mySqliteHelper = new MyDatabaseHelper(context, "UserDB.db", null, 1);
    }
    public Long insertComments(String newsTitle,String user) {
        Long a = null;
        SQLiteDatabase db = mySqliteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("img", newsTitle);
        values.put("user", user);
        a = db.insert("comments", null, values);
        return a;
    }
    public List<ImageBean> getImgs(String name) {
        SQLiteDatabase db = mySqliteHelper.getReadableDatabase();
        List<ImageBean> books = new ArrayList<>();

        String selection = " user = ?";
        String[] selectionArgs = {name};
        Cursor cursor = db.query(MyDatabaseHelper.TABLE_COMMENTS, null, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
                @SuppressLint("Range") int Id = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_ID));
                @SuppressLint("Range") String user = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NEWS_user));
                @SuppressLint("Range") String img = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NEWS_TITLE));
                ImageBean book = new ImageBean(Id,user,img);
                books.add(book);
        }

        cursor.close();

        return books;
    }
}
