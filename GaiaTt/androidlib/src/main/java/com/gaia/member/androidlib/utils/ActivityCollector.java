package com.gaia.member.androidlib.utils;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by baiyuanwei on 16/3/15.
 */
public class ActivityCollector {

    private static ArrayList<Activity> activityColletor = new ArrayList<Activity>();

    /**
     * 添加activity
     *
     * @param activity
     */
    public static void addActivity(Activity activity) {
        activityColletor.add(activity);
    }

    /**
     * 移除某个activity
     *
     * @param activity
     */
    public static void removeActivity(Activity activity) {
        activityColletor.remove(activity);
    }

    /**
     * 销毁所有的activity
     */
    public static void removeAllActivity() {
        if (activityColletor != null) {
            for (Activity activity : activityColletor) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    }

}
