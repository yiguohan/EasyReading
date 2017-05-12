package com.yiguohan.easyreading.APIs;

import com.yiguohan.easyreading.Beans.MyBook;
import com.yiguohan.easyreading.Beans.ReadingRecord;
import com.yiguohan.easyreading.Beans.User;

import java.util.List;

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
    boolean insertUser(User user);

    /**
     * 删除用户
     * @param id 用户id
     * @return
     */
    boolean deleteUser(int id);

    /**
     * 更新用户
     * @param user
     * @return
     */
    boolean updateUser(User user);

    /**
     * 查询用户
     * @param account
     * @param password
     * @return
     */
    Observable<User> getUserbyAccount(String account,String password);

    /*----------------------------------------Mybook---------------------------------------*/
    /**
     * 添加正在阅读的书
     * @param book
     * @return
     */
    boolean insertMyBook(MyBook book);

    /**
     * 删除正在阅读的书
     * @param id
     * @return
     */
    boolean deleteMyBook(int id);

    /**
     * 更新正在阅读的书的信息
     * @param book
     * @return
     */
    boolean updateMyBook(MyBook book);

    /**
     * 根据Id返回正在阅读的书的信息
     * @param id
     * @return
     */
    Observable<MyBook> getMyBookById(int id);

    /**
     * 根据用户Id返回用户正在阅读的书的列表
     * @param userId
     * @return
     */
    Observable<List<MyBook>> getMyBooksByUserId(int userId);

    /*----------------------------------------ReadingRecord---------------------------------------*/

    /**
     * 新增阅读记录
     * @param record
     * @return
     */
    boolean insertReadingRecord(ReadingRecord record);

    /**
     * 删除阅读记录（必要性？）
     * @param id
     * @return
     */
    boolean deleteReadingRecord(int id);

    /**
     * 更新阅读记录（必要性？）
     * @param record
     * @return
     */
    boolean updateReadingRecord(ReadingRecord record);

    /**
     * 根据阅读记录Id查询阅读记录（必要性？）
     * @param id
     * @return
     */
    Observable<ReadingRecord> getReadingRecordById(int id);

    /**
     * 查询最新的阅读记录
     * @return
     */
    Observable<ReadingRecord> getLatestRedingRecord();

    /**
     * 根据用户Id返回阅读记录列表
     * @param userId
     * @return
     */
    Observable<List<ReadingRecord>> getReadingRecordsByUserId(int userId);
}
