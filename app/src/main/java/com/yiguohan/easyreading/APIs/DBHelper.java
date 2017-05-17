package com.yiguohan.easyreading.APIs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * 数据库帮助类
 * Created by yiguohan on 2017/5/12.
 * Email:yiguohan@gmail.com
 */

public class DBHelper extends SQLiteOpenHelper {
    /**
     * 创建MyBook表
     */
    public static final String CREATE_MYBOOK = "create table MyBook(" +
            "id integer primary key autoincrement," +
            "userId integer," +
            "bookId integer," +
            "title text," +
            "process real," +
            "imageUrl text," +
            "currentPage text," +
            "totalPage text)";

    /**
     * 创建ReadingRecord表
     * PS SQLite没有时间格式，因此时间戳的格式是text
     */
    public static final String CREATE_READINGRECORD = "create table ReadingRecord(" +
            "id integer primary key autoincrement," +
            "userId integer," +
            "bookId integer," +
            "currentPage text," +
            "timeStamp text)";

    /**
     * 创建User表
     */
    public static final String CREATE_USER = "create table User(" +
            "id integer primary key autoincrement," +
            "account text," +
            "password text)";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_MYBOOK);
        db.execSQL(CREATE_READINGRECORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
