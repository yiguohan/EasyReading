package com.yiguohan.easyreading.Base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yiguohan on 2017/5/22.
 * Email:yiguohan@gmail.com
 */

public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity a :
                activities) {
            a.finish();
        }
    }
}
