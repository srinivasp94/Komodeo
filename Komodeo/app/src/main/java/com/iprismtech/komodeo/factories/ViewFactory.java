package com.iprismtech.komodeo.factories;

import android.support.annotation.IntDef;


import com.iprismtech.komodeo.activity.AddclassFormAct;
import com.iprismtech.komodeo.factories.controllers.ApplicationController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.iprismtech.komodeo.factories.ViewFactory.ScreenIds.ADD_CLASS_FORM_SCREEN;


/**
 * Created by prasad on 05/07/2017.
 * ViewFactory.java The Class which returns the Class (Screen) to the
 * application frame. Developer should use this class to get the reference of
 * any screen in the application. He should not create the screen by him/her
 * self
 */


public class ViewFactory {

    @Retention(RetentionPolicy.CLASS)
    @IntDef({ScreenIds.SPLASH_SCREEN,
            ADD_CLASS_FORM_SCREEN})
    public @interface ScreenIds {
        int SPLASH_SCREEN = 1001;
        int ADD_CLASS_FORM_SCREEN = 1005;

    }


    /**
     * Reference of Application Controller
     */
    private ApplicationController mApplicationController = null;

    /**
     * Constructor
     */
    private ViewFactory() {
        mApplicationController = ApplicationController.getInstance();
    }

    /**
     * This function should only be used when whole application is made by
     * multiple activity.
     *
     * @param id
     * @return Activity class
     */
    public static Class getActivityClass(@ScreenIds int id) {

        switch (id) {
            case ADD_CLASS_FORM_SCREEN:
                return AddclassFormAct.class;
            default:
                throw new IllegalStateException("Invalid screen id");
        }

    }


    /**
     * This function should only be used when whole application is made by
     * multiple Fragment.
     *
     * @param id
     * @return Fragment class
     */
    public static Class getFragmentClass(@ScreenIds int id) {

        switch (id) {
            //todo logic for fragments are same
//            case SPLASH_SCREEN: {
//                return SplashActivity.class;
//            }
//
//            case LOGIN_SCREEN: {
//                return DriverLoginActivity.class;
//            }
//
//            case SIGNUP_SCREEN: {
//                return HomeActivity.class;
//            }

            default:
                throw new IllegalStateException("Invalid Event id");

        }

    }
}