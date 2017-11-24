package com.yiguohan.easyreading.Base;

import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by yiguohan on 2017/5/23.
 * Email:yiguohan@gmail.com
 */

public class EasyReadingApplication extends Application {

    /**
     * 全局上下文
     */
    private static Context context;

    /**
     * 当前登录用户Id 其他信息去SharedPreference里面取
     */
    private static String currentUserId;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        /**
         * 极光推送API初始化
         */
        JPushInterface.setDebugMode(true);//极光推送API设置为Debug模式
        JPushInterface.init(this);//极光推送API初始化
    }

    public static Context getContext(){
        return context;
    }

    public static String getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(String currentUserId) {
        EasyReadingApplication.currentUserId = currentUserId;
    }
}
