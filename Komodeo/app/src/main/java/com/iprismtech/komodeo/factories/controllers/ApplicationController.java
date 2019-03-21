package com.iprismtech.komodeo.factories.controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.iprismtech.komodeo.MyApplication;
import com.iprismtech.komodeo.factories.Constants.AppConstants;
import com.iprismtech.komodeo.factories.ViewFactory;


/**
 * Created by prasad on 05-07-17.
 * ApplicationController.java
 * <p/>
 * The ApplicationController Class, which helps to manage different Controllers,
 * Models, Views. This Class will be initialize as the platform requirement.
 */

public class ApplicationController {

    /**
     * private instance of ApplicationController for singleton Design Pattern
     */
    private static ApplicationController instance;

    /**
     * private instance of UIController for managing different AbstractViews
     */
    public UiController uiController;

    /**
     * private instance of ViewFactory for fast accessing.
     */
    public ViewFactory viewFactory;

    public Activity mActivity;
    public Context mContext;

    private MyApplication application;

    /**
     * Constructor of ApplicationController
     */
    private ApplicationController() {
        uiController = UiController.getInstance();
    }

    /**
     * Get Single instance of ApplicationController
     *
     * @return ApplicationController single instance
     */
    public static ApplicationController getInstance() {
        if (instance == null) {
            // creating new instance of application controller
            instance = new ApplicationController();
        }
        return instance;
    }
//
//    /**
//     * This Function will get called from DriverLoginActivity or Any Activity which is
//     * going to display first screen after launching this application
//     */
//    public void initialize() {
//
//        // initialize the ModelFacade
//        modelFacade.initialize();
//
//        // set the reference for UI Controller
//        uiController = UIController.getInstance();
//
//        // initialize the UIController
//        uiController.initialize();
//
//        // set the viewFactory reference for further use in code.
//        viewFactory = ViewFactory.getInstance();
//
//    }

    /**
     * returns the current mActivity
     *
     * @return
     */
    public Activity getActivity() {
        return mActivity;
    }

    /**
     * sets the reference of current mActivity
     *
     * @param mActivity
     */
    public void setActivity(@NonNull Activity mActivity) {
        this.mActivity = mActivity;
    }


    /**
     * @return the application mContext
     */
    public MyApplication getApplication() {
        return application;
    }

    /**
     * sets the reference of application
     *
     * @param application
     */
    public void setApplication(MyApplication application) {
        this.application = application;
    }

    /**
     * @return the mContext of current mActivity
     */
    public Context getContext() {
        return mContext;
    }


    /**
     * sets the reference of mContext
     *
     * @param
     */
    public void setContext(@NonNull Context mContext) {
        this.mContext = mContext;
    }


    /**
     * Handle Event Based on Event ID
     *
     * @param eventId Event raised by View
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void handleEvent(@AppConstants.EventIds int eventId) {
        handleEvent(eventId, null);
    }


    /**
     * Handle Event Based on Event ID and Object
     *
     * @param eventId      Event Id based on user actions
     * @param eventObjects It stores the data for the given Event. so it can forward to
     *                     other events
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint({"WrongConstant", "SwitchIntDef"})
    public void handleEvent(@AppConstants.EventIds int eventId, Object eventObjects) {
        Log.d(AppConstants.LOG_CAT, "handleEvent : " + eventId);

        switch (eventId) {
            case AppConstants.EventIds.LAUNCH_SPLASH_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.SPLASH_SCREEN);
                break;

            case AppConstants.EventIds.LAUNCH_ADD_CLASS_FORM_SCREEN:
                UiController.getInstance().launchActivity(ViewFactory.ScreenIds.ADD_CLASS_FORM_SCREEN);
                break;


            default:
                throw new IllegalStateException("Invalid Event id");
        }
    }
}
