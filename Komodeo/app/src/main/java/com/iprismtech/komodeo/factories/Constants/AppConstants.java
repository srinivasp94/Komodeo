package com.iprismtech.komodeo.factories.Constants;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Created by Prasad on 7/5/2017.
 */
public interface AppConstants {

    String LOG_CAT = ">>logs : ";

    /**
     * Application Controller events ids
     * Maintain all app level event ids and def of that event ids
     */
    @Retention(RetentionPolicy.CLASS)
    @IntDef({


    })
    @interface EventIds {

        int LAUNCH_SPLASH_SCREEN = 101;

        int LAUNCH_ADD_CLASS_FORM_SCREEN= 105;

    }

    public static final long INTERVAL = 1000 * 1;
    public static final long FASTEST_INTERVAL = 500 * 1;


    /**
     * Maintain the PREFERENCES Keys and constant
     */
    interface PREFERENCES {
        String IS_LAUNCH_HOME_SCREEN = "isLaunchHomeScreen";
        String KEY_PREF_X_AUTH_TOKEN = "X-AUTH-TOKEN";
        String KEY_HOST_URL = "hostUrl";
    }

}
