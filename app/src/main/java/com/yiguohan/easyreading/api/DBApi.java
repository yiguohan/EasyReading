package com.yiguohan.easyreading.api;

import android.database.Cursor;

import com.yiguohan.easyreading.beans.MyBook;
import com.yiguohan.easyreading.beans.ReadingRecord;
import com.yiguohan.easyreading.beans.User;

import io.reactivex.Observable;


/**
 * 数据库访问接口
 * Created by yiguohan on 2017/5/12.
 * Email:yiguohan@gmail.com
 */

public interface DBApi {

    /*----------------------------------------User---------------------------------------*/
    /**
     * 插入用户
     * @param user
     * @return
     */
    Observable<Long> insertUser(User user);

    /**
     * 删除用户
     * @param id 用户id
     * @return 删除的行数
     */
    Observable<Integer> deleteUser(int id);

    /**
     * 更新用户
     * @param user
     * @return 更改的行数
     */
    Observable<Integer> updateUser(User user);

    /**
     * 查询用户
     * @param account
     * @param password
     * @return
     */
    Observable<Cursor> getUserbyAccount(String account, String password);

    /**
     * 根据账户名称查询数据库中是否已经存在该账户
     * @param account
     * @return
     */
    Observable<Cursor> checkUserExistbyAccount(String account);

    /**
     * 根据用户的ID查询用户
     * @param id
     * @return
     */
    Observable<Cursor> getUserById(int id);

    /*----------------------------------------Mybook---------------------------------------*/
    /**
     * 添加正在阅读的书
     * @param book
     * @return 返回当前的最大行数
     */
    Observable<Long> insertMyBook(MyBook book);

    /**
     * 删除正在阅读的书
     * @param id
     * @return
     */
    Observable<Integer> deleteMyBook(int id);

    /**
     * 更新正在阅读的书的信息
     * @param book
     * @return
     */
    Observable<Integer> updateMyBook(MyBook book);

    /**
     * 根据Id返回正在阅读的书的信息
     * @param id
     * @return
     */
    Observable<Cursor> getMyBookById(int id);

    /**
     * 根据用户Id返回用户正在阅读的书的列表
     * @param userId
     * @return
     */
    Observable<Cursor> getMyBooksByUserId(int userId);

    /**
     * 确认当前用户MyBook表中是否已经存在该书
     * @param myBook
     * @return
     */
    Observable<Cursor> checkMyBooksExists(MyBook myBook);

    /*----------------------------------------ReadingRecord---------------------------------------*/

    /**
     * 新增阅读记录
     * @param record
     * @return
     */
    Observable<Long> insertReadingRecord(ReadingRecord record);

    /**
     * 删除阅读记录（必要性？）
     * @param id
     * @return
     */
    Observable<Integer> deleteReadingRecord(int id);

    /**
     * 更新阅读记录（必要性？）
     * @param record
     * @return
     */
    Observable<Integer> updateReadingRecord(ReadingRecord record);

    /**
     * 根据阅读记录Id查询阅读记录（必要性？）
     * @param id
     * @return
     */
    Observable<Cursor> getReadingRecordById(int id);

    /**
     * 查询最新的阅读记录
     * @return
     */
    Observable<Cursor> getLatestRedingRecord();

    /**
     * 根据用户Id返回阅读记录列表
     * @param userId
     * @return
     */
    Observable<Cursor> getReadingRecordsByUserId(int userId);
}
