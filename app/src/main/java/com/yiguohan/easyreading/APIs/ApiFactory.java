package com.yiguohan.easyreading.APIs;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by yiguohan on 2017/5/9.
 * Email:yiguohan@gmail.com
 */

public class ApiFactory {

    static DoubanApi doubanService = null;
    static DBApi dbService = null;
    static Object lock = new Object();
    static ContentValues contentValues;

    /**
     * 获取豆瓣服务的单例
     * @return
     */
    public static synchronized DoubanApi getDoubanService() {
        synchronized (lock) {
            if (doubanService == null) {
                doubanService = new ApiRetrofit().getDoubanService();
            }
        }

        return doubanService;
    }

    /**
     * 获取数据库接口单例
     * @param mContext 上下文
     * @return 数据库接口
     */
    public static synchronized DBApi getDbService(Context mContext) {

        synchronized (lock) {
            if (dbService == null) {
                dbService = new DBManager(mContext).getDBService();
            }
        }
        return dbService;

    }



}
