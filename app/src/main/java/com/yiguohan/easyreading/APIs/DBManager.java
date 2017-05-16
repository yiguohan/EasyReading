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
                return new ObservableJust<Long>(Long.valueOf(db.insert("User", null, contentValues)));
            }

            @Override
            public Observable<Integer> deleteUser(int id) {
                db.delete("User", "id = ?", new String[]{String.valueOf(id)});
                return new ObservableJust<Integer>(Integer.valueOf(db.delete("User", "id = ?", new String[]{String.valueOf(id)})));
            }

            @Override
            public Observable<Integer> updateUser(User user) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("account", "yiguohan");
                contentValues.put("password", "12345");
                return new ObservableJust<>(db.update("User", contentValues, "id = ?", new String[]{String.valueOf(user.getId())}));
            }

            @Override
            public Observable<Cursor> getUserbyAccount(String account, String password) {
                return new ObservableJust<Cursor>(db.rawQuery("select * from User where account = ? and password = ?", new String[]{account, password}));
            }

            @Override
            public Observable<Boolean> insertMyBook(MyBook book) {
                return null;
            }

            @Override
            public Observable<Boolean> deleteMyBook(int id) {
                return null;
            }

            @Override
            public Observable<Boolean> updateMyBook(MyBook book) {
                return null;
            }

            @Override
            public Observable<MyBook> getMyBookById(int id) {
                return null;
            }

            @Override
            public Observable<List<MyBook>> getMyBooksByUserId(int userId) {
                return null;
            }

            @Override
            public Observable<Boolean> insertReadingRecord(ReadingRecord record) {
                return null;
            }

            @Override
            public Observable<Boolean> deleteReadingRecord(int id) {
                return null;
            }

            @Override
            public Observable<Boolean> updateReadingRecord(ReadingRecord record) {
                return null;
            }

            @Override
            public Observable<ReadingRecord> getReadingRecordById(int id) {
                return null;
            }

            @Override
            public Observable<ReadingRecord> getLatestRedingRecord() {
                return null;
            }

            @Override
            public Observable<List<ReadingRecord>> getReadingRecordsByUserId(int userId) {
                return null;
            }
        };
        return dbService;
    }

}
