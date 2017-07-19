package com.gaia.member.androidlib.utils;

import android.util.Log;

/**
 * Created by baiyuanwei on 16/4/10.
 *
 * 功能: 可以自由控制日志是否可以输出
 */
public class LogUtil {

    private static final int VERBOSE = 1;

    private static final int DEBUG = 2;

    private static final int INFO = 3;

    private static final int WARN = 4;

    private static final int ERROR = 5;

    //禁止输出日志
    private static final int NOTHING = 6;

    //控制日志的输出与否
    private static final int LEVEL = VERBOSE;

    private static final String TAG = "GAIA";

    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            Log.e(tag, msg);
        }
    }

    public static void v(String msg) {
        if (LEVEL <= VERBOSE) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (LEVEL <= DEBUG) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String msg) {
        if (LEVEL <= INFO) {
            Log.i(TAG, msg);
        }
    }

    public static void w(String msg) {
        if (LEVEL <= WARN) {
            Log.w(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (LEVEL <= ERROR) {
            Log.e(TAG, msg);
        }
    }
}
