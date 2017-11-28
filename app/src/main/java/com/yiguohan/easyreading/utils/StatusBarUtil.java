package com.yiguohan.easyreading.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.widget.DrawerLayout;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.yiguohan.easyreading.widgets.StatusBarView;

/**
 * Created by yiguohan on 2017/9/13.
 * GitHub: https://github.com/yiguohan
 * E-mail: yiguohan@gmail.com
 * <p>
 * 状态栏工具类
 */

public class StatusBarUtil {

    /**
     * 设置透明状态栏
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void transParentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 对DrawerLayout进行不透明的状态栏颜色设置
     *
     * @param activity
     * @param drawerLayout
     * @param color
     */
    public static void setColorNoTranslucentForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int color) {
        setColorForDrawerLayout(activity, drawerLayout, color, 0);
    }

    /**
     * 为DrawerLayout设置颜色
     *
     * @param activity
     * @param drawerLayout
     * @param color
     * @param statusBarAlpha
     */
    private static void setColorForDrawerLayout(Activity activity, DrawerLayout drawerLayout, @ColorInt int color, int statusBarAlpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;//低于Android 4.4 Api 19 无法对状态栏进行设置
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //对DrawerLayout的主内容布局进行设置
        ViewGroup contentLayout = (ViewGroup) drawerLayout.getChildAt(0);
        if (contentLayout.getChildCount() > 0 && contentLayout.getChildAt(0) instanceof StatusBarView) {
            contentLayout.getChildAt(0).setBackgroundColor(calculateStatusBarColor(color, statusBarAlpha));
        } else {
            contentLayout.addView(createStatusBarView(activity, color), 0);
        }
        if (!(contentLayout instanceof LinearLayout) && contentLayout.getChildAt(1) != null) {
            contentLayout.setPadding(contentLayout.getPaddingLeft(), contentLayout.getPaddingTop() + getStatusBarHeight(activity), contentLayout.getPaddingRight(), contentLayout.getPaddingBottom());
        }

        //对DrawerLayout的侧滑栏进行设置
        ViewGroup drawer = (ViewGroup) drawerLayout.getChildAt(1);
        drawer.setFitsSystemWindows(false);
        contentLayout.setFitsSystemWindows(false);
        contentLayout.setClipToPadding(true);//Padding部分是否绘制ViewGroup的内容
        drawer.setFitsSystemWindows(true);
        addTranslucentView(activity, statusBarAlpha);
    }

    /**
     * 添加透明状态栏
     *
     * @param activity
     * @param statusBarAlpha
     */
    private static void addTranslucentView(Activity activity, int statusBarAlpha) {
        ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
        if (contentView.getChildCount() > 1) {
            contentView.getChildAt(1).setBackgroundColor(Color.argb(statusBarAlpha, 0, 0, 0));
        } else {
            contentView.addView(createTranslucentStatusBar(activity, statusBarAlpha));
        }
    }


    /**
     * 创建一个StatusBarView
     *
     * @param activity
     * @param statusBarAlpha
     * @return
     */
    private static StatusBarView createTranslucentStatusBar(Activity activity, int statusBarAlpha) {
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.argb(statusBarAlpha, 0, 0, 0));
        return statusBarView;
    }

    /**
     * 根据上下文获取状态栏高度
     *
     * @param context
     * @return
     */
    private static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    private static int calculateStatusBarColor(@ColorInt int color, int statusBarAlpha) {

        float a = 1 - statusBarAlpha / 255f;
        //获取RGB三种颜色值
        int red = color >> 16 & 0xff;
        int green = color >> 8 & 0xff;
        int blue = color & 0xff;

        red = (int) (red * a + 0.5);
        green = (int) (green * a + 0.5);
        blue = (int) (blue * a + 0.5);
        return 0xff << 24 | red << 16 | green << 8 | blue;
    }

    /**
     * 画一个和状态栏等宽等高的View
     *
     * @param activity
     * @param color
     * @return
     */
    private static StatusBarView createStatusBarView(Activity activity, @ColorInt int color) {
        StatusBarView statusBarView = new StatusBarView(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(color);
        return statusBarView;
    }

}
