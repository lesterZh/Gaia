package com.gaia.member.gaiatt.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by zhangHaiTao on 2016/4/27.
 */
public class ActivityUtils {

    public static void gotoIntent(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

    public static void gotoIntentAndFinish(Activity activity, Class<?> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }


}
