package com.yiguohan.easyreading.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * JPush Receiver
 * Created by yiguohan on 2017/11/27.
 * github: https://www.github.com/yiguohan
 * email: yiguohan@gmail.com
 */

public class JPushReceiver extends BroadcastReceiver {
    private static final String TAG = "JPushReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Log.d(TAG, "onReceive - " + intent.getAction());

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "JPushReceiver 接收Registration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "收到了自定义消息。");
            Log.d(TAG, "消息标题是：" + bundle.getString(JPushInterface.EXTRA_TITLE));
            Log.d(TAG, "消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            Log.d(TAG, "附加字段：" + bundle.getString(JPushInterface.EXTRA_EXTRA));
            Log.d(TAG, "消息ID：" + bundle.getString(JPushInterface.EXTRA_MSG_ID));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "收到了通知");
            Log.d(TAG, "通知的标题是：" + bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE));
            Log.d(TAG, "通知内容是：" + bundle.getString(JPushInterface.EXTRA_ALERT));
            Log.d(TAG, "附加字段是：" + bundle.getString(JPushInterface.EXTRA_EXTRA));
            Log.d(TAG, "通知的Notification ID是：" + bundle.getString(JPushInterface.EXTRA_NOTIFICATION_ID));
            Log.d(TAG, "富媒体通知推送下载的HTML的文件路径是：" + bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_PATH));
            Log.d(TAG, "富媒体通知推送下载的图片资源的文件名是：" + bundle.getString(JPushInterface.EXTRA_RICHPUSH_HTML_RES));
            Log.d(TAG, "唯一标识通知消息的 ID是：" + bundle.getString(JPushInterface.EXTRA_MSG_ID));
            Log.d(TAG, "大文本通知样式中大文本的内容是：" + bundle.getString(JPushInterface.EXTRA_BIG_TEXT));
            Log.d(TAG, "大图片通知样式中大图片的路径/地址是：" + bundle.getString(JPushInterface.EXTRA_BIG_PIC_PATH));
            Log.d(TAG, "收件箱通知样式中收件箱的内容是：" + bundle.getString(JPushInterface.EXTRA_INBOX));
            Log.d(TAG, "通知的优先级是：" + bundle.getString(JPushInterface.EXTRA_NOTI_PRIORITY));
            Log.d(TAG, "通知分类是：" + bundle.getString(JPushInterface.EXTRA_NOTI_CATEGORY));
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "用户点击了通知");

        } else if (JPushInterface.ACTION_NOTIFICATION_CLICK_ACTION.equals(intent.getAction())) {
            Log.d(TAG, "用户点击了通知栏中自定义的按钮");

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            Log.d(TAG, "JPush 服务的连接状态发生变化");

        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());
        }
    }
}
