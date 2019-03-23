package com.iprismtech.komodeo.utils;

import android.util.Log;

public class LogUtils {

    public static boolean ENABLE_LOG = false;

    public static final void error(String tag, String message) {
        if (ENABLE_LOG) Log.e(tag, message);
    }

    public static final void info(String tag, String message) {
        if (ENABLE_LOG) Log.i(tag, message);
    }

    public static final void debug(String tag, String message) {
        if (ENABLE_LOG) Log.d(tag, message);
    }

}
