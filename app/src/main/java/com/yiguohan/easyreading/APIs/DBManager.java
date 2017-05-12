package com.yiguohan.easyreading.APIs;

import com.yiguohan.easyreading.Beans.MyBook;
import com.yiguohan.easyreading.Beans.ReadingRecord;
import com.yiguohan.easyreading.Beans.User;

import java.util.List;

import io.reactivex.Observable;

/**
 *
 * Created by yiguohan on 2017/5/12.
 * Email:yiguohan@gmail.com
 */

public class DBManager  {

    private DBApi dbService;

    /**
     * 构造函数中需要：
     * 1.获取DBApi的实例
     * 2.判断当前是否存在数据表
     *      2.1 不存在则创建数据表
     *      2.2 存在
     */
    public DBManager() {

    }

    public DBApi getDBService(){
        return dbService;
    }
}
