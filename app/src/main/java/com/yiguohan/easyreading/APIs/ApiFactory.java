package com.yiguohan.easyreading.APIs;

/**
 * Created by yiguohan on 2017/5/9.
 * Email:yiguohan@gmail.com
 */

public class ApiFactory {

    static DoubanApi doubanService = null;
    static Object lock = new Object();

    public static synchronized DoubanApi getDoubanService() {
        synchronized (lock) {
            if (doubanService == null) {
                doubanService = new ApiRetrofit().getDoubanService();
            }
        }

        return doubanService;
    }

}
