package com.yiguohan.easyreading.APIs;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;
import com.yiguohan.easyreading.Beans.MyBook;
import com.yiguohan.easyreading.Beans.ReadingRecord;
import com.yiguohan.easyreading.Beans.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableJust;
import rx.schedulers.Schedulers;

/**
 * Created by yiguohan on 2017/5/12.
 * Email:yiguohan@gmail.com
 */

public class DBManager{

    private DBApi dbService;

    private static ContentValues contentValues;

    public DBManager(Context mContext) {
        initDBService(mContext);
    }

    public DBApi getDBService() {
        return dbService;
    }

    /**
     * 初始化数据库服务
     * 戒指2017年5月16日，Sqlbrite作者在GitHub上未发布支持RxJava2的Adapter,所以此处暂不适用Sqlbrite
     * @param mContext
     * @return
     */
    private DBApi initDBService(Context mContext){
        final BriteDatabase db = new SqlBrite.Builder().build().wrapDatabaseHelper(new DBHelper(mContext, "EazyReading.db", null, 1), Schedulers.io());
        dbService = new DBApi() {
            @Override
            public Observable<Long> insertUser(User user) {
                contentValues = new ContentValues();
                contentValues.put("Id",user.getId());
                contentValues.put("Account",user.getAccount());
                contentValues.put("Password",user.getPassword());
                return new ObservableJust<Long>(Long.valueOf(db.insert("User",contentValues)));
            }

            @Override
            public Observable<Boolean> deleteUser(int id) {
                return null;
            }

            @Override
            public Observable<Boolean> updateUser(User user) {
                return null;
            }

            @Override
            public Observable<User> getUserbyAccount(String account, String password) {
                return null;
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
