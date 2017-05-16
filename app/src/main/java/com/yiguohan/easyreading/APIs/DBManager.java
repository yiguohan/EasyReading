package com.yiguohan.easyreading.APIs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yiguohan.easyreading.Beans.MyBook;
import com.yiguohan.easyreading.Beans.ReadingRecord;
import com.yiguohan.easyreading.Beans.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableJust;

/**
 * Created by yiguohan on 2017/5/12.
 * Email:yiguohan@gmail.com
 */

public class DBManager {

    private DBApi dbService;

    private static ContentValues contentValues;

    private DBHelper dbHelper;

    private SQLiteDatabase db;

    public DBManager(Context mContext) {
        initDBService(mContext);
    }

    public DBApi getDBService() {
        return dbService;
    }

    /**
     * 初始化数据库服务
     * 截至2017年5月16日，Sqlbrite作者在GitHub上未发布支持RxJava2的Adapter,所以此处暂不适用Sqlbrite
     * 为方便重构，此处不适用第三方Api进行数据库操作
     *
     * @param mContext
     * @return
     */
    private DBApi initDBService(Context mContext) {
//        final BriteDatabase db = new SqlBrite.Builder().build().wrapDatabaseHelper(new DBHelper(mContext, "EazyReading.db", null, 1), Schedulers.io());
        dbHelper = new DBHelper(mContext, "EasyReading", null, 1);
        db = dbHelper.getWritableDatabase();
        dbService = new DBApi() {
            @Override
            public Observable<Long> insertUser(User user) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("account", user.getAccount());
                contentValues.put("password", user.getPassword());
                return new ObservableJust<Long>(db.insert("User", null, contentValues));
            }

            @Override
            public Observable<Integer> deleteUser(int id) {
                return new ObservableJust<Integer>(db.delete("User", "id = ?", new String[]{String.valueOf(id)}));
            }

            @Override
            public Observable<Integer> updateUser(User user) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("account", user.getAccount());
                contentValues.put("password", user.getPassword());
                return new ObservableJust<Integer>(db.update("User", contentValues, "id = ?", new String[]{String.valueOf(user.getId())}));
            }

            @Override
            public Observable<Cursor> getUserbyAccount(String account, String password) {
                return new ObservableJust<Cursor>(db.rawQuery("select * from User where account = ? and password = ?", new String[]{account, password}));
            }

            @Override
            public Observable<Long> insertMyBook(MyBook book) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id",book.getId());
                contentValues.put("bookId",book.getBookId());
                contentValues.put("userId",book.getUserId());
                contentValues.put("title",book.getTitle());
                contentValues.put("imageUrl",book.getImageUrl());
                contentValues.put("currentPage",book.getCurrentPage());
                contentValues.put("totalPage",book.getTotalPage());
                contentValues.put("process",book.getProcess());

                return new ObservableJust<Long>(db.insert("MyBook",null,contentValues));
            }

            @Override
            public Observable<Integer> deleteMyBook(int id) {
                return new ObservableJust<Integer>(db.delete("MyBook", "id = ?", new String[]{String.valueOf(id)}));
            }

            @Override
            public Observable<Integer> updateMyBook(MyBook book) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("bookId",book.getBookId());
                contentValues.put("userId",book.getUserId());
                contentValues.put("title",book.getTitle());
                contentValues.put("imageUrl",book.getImageUrl());
                contentValues.put("currentPage",book.getCurrentPage());
                contentValues.put("totalPage",book.getTotalPage());
                contentValues.put("process",book.getProcess());
                return new ObservableJust<>(db.update("MyBook",contentValues,"id = ?",new String[]{String.valueOf(book.getId())}));
            }

            @Override
            public Observable<Cursor> getMyBookById(int id) {
                return new ObservableJust<Cursor>(db.rawQuery("select * from MyBook where id = ?",new String[]{String.valueOf(id)}));
            }

            @Override
            public Observable<Cursor> getMyBooksByUserId(int userId) {
                return new ObservableJust<Cursor>(db.rawQuery("select * from MyBook where userId = ?",new String[]{String.valueOf(userId)}));
            }

            @Override
            public Observable<Long> insertReadingRecord(ReadingRecord record) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id",record.getId());
                contentValues.put("userId",record.getUserId());
                contentValues.put("bookId",record.getBookId());
                contentValues.put("currentPage",record.getCurrentPage());
                contentValues.put("timeStamp",record.getTimeStamp().toString());
                return new ObservableJust<Long>(db.insert("ReadingRecord",null,contentValues));
            }

            @Override
            public Observable<Integer> deleteReadingRecord(int id) {
                return new ObservableJust<Integer>(db.delete("MyBook","id = ?",new String[]{String.valueOf(id)}));
            }

            @Override
            public Observable<Integer> updateReadingRecord(ReadingRecord record) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("userId",record.getUserId());
                contentValues.put("bookId",record.getBookId());
                contentValues.put("currentPage",record.getCurrentPage());
                contentValues.put("timeStamp",record.getTimeStamp().toString());
                return new ObservableJust<Integer>(db.update("MyBook",contentValues,"id = ?",new String[]{String.valueOf(record.getId())}));
            }

            @Override
            public Observable<Cursor> getReadingRecordById(int id) {
                return new ObservableJust<Cursor>(db.rawQuery("select * from ReadingRecord where id = ?",new String[]{String.valueOf(id)}));
            }

            @Override
            public Observable<Cursor> getLatestRedingRecord() {
                return new ObservableJust<Cursor>(db.rawQuery("select top 1 * from ReadingRecord orderby id desc",null));
            }

            @Override
            public Observable<Cursor> getReadingRecordsByUserId(int userId) {
                return new ObservableJust<Cursor>(db.rawQuery("select * from ReadingRecord where userId = ?",new String[]{String.valueOf(userId)}));
            }
        };
        return dbService;
    }

}
