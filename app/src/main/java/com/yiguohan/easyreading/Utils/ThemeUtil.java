package com.yiguohan.easyreading.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.yiguohan.easyreading.Base.EasyReadingApplication;

/**
 * Created by yiguohan.
 * 更改主题的工具类
 */

public class ThemeUtil {

    public static Context context = EasyReadingApplication.getContext();
    public static int defaultThemeColor = Color.TRANSPARENT;
    /**
     * 获取本机的主题颜色值
     *
     * @return
     */
    public static int getThemeColor() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE);
        if (sharedPreferences == null) {
            setThemeColor(defaultThemeColor);
        }
        return sharedPreferences.getInt("themecolor", defaultThemeColor);
    }

    /**
     * 记录本机的主体颜色值
     *
     * @param color
     */
    public static void setThemeColor(int color) {
        SharedPreferences.Editor editor = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE).edit();
        editor.putInt("themecolor", color);
        editor.commit();
    }
}
