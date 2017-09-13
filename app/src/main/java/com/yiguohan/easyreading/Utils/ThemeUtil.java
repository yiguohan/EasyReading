package com.yiguohan.easyreading.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.yiguohan.easyreading.Base.EasyReadingApplication;
import com.yiguohan.easyreading.R;

/**
 * Created by yiguohan.
 * 更改主题的工具类
 */

public class ThemeUtil {

    public static Context context = EasyReadingApplication.getContext();
    public static int defaultThemeColor = R.style.AppTheme;

    /**
     * 获取本机的主题颜色值
     *
     * @return
     */
    public static int getThemeColor() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE);
        if (sharedPreferences == null) {
            setThemeColor(defaultThemeColor);
            setNightModeState(false);
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

    /**
     * 是否为夜间模式
     *
     * @return
     */
    public static boolean getNightModeState() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE);
        if (sharedPreferences == null) {
            setNightModeState(false);
        }
        return sharedPreferences.getBoolean("nightModeState", false);
    }

    /**
     * 记录夜间模式状态
     *
     * @param isNightMode
     */
    public static void setNightModeState(boolean isNightMode) {
        SharedPreferences.Editor editor = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE).edit();
        editor.putBoolean("nightModeState", isNightMode);
        editor.commit();
    }

    public static int getThemePosition(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE);
        if (sharedPreferences == null) {
            setThemePosition(0);
            setNightModeState(false);
        }
        return sharedPreferences.getInt("themePosition", 0);
    }

    public static void setThemePosition(int position){
        SharedPreferences.Editor editor = context.getSharedPreferences("ThemeColor", Context.MODE_PRIVATE).edit();
        editor.putInt("themePosition", position);
        editor.commit();
    }
}
