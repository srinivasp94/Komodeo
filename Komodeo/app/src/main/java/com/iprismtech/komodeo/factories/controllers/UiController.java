package com.iprismtech.komodeo.factories.controllers;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.factories.ViewFactory;


/**
 * UIController.java
 * <p>
 * The UIController Class, which helps for add and remove the View from the Device Screen
 */

public class UiController {

    /**
     * private sInstance of UIControllers for singleton Design Pattern
     */
    private static UiController sInstance;


    /**
     * Empty Constructor
     */
    private UiController() {

    }

    /**
     * Get Single sInstance of UiController
     *
     * @return UiController single sInstance
     */
    public static UiController getInstance() {
        if (sInstance == null) {
            // creating new sInstance of application controller
            sInstance = new UiController();
        }
        return sInstance;
    }


    /**
     * Launch the respective activity
     *
     * @param screenId
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void launchActivity(@ViewFactory.ScreenIds int screenId) {
        try {
            Intent intent = new Intent(ApplicationController.getInstance().getContext(), ViewFactory.getActivityClass(screenId));
            //Added the animation to open activity as smooth.
            Bundle screenLaunchAnimation =
                    ActivityOptions.makeCustomAnimation(ApplicationController.getInstance().getContext(), R.anim.animation_click, R.anim.animation_show).toBundle();
            ApplicationController.getInstance().getContext().startActivity(intent, screenLaunchAnimation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Launch the respective activity
     *
     * @param screenId
     * @param bundle
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void launchActivity(@ViewFactory.ScreenIds int screenId, @NonNull Bundle bundle) {
        try {
            Intent intent = new Intent(ApplicationController.getInstance().getContext(), ViewFactory.getActivityClass(screenId));
            intent.putExtras(bundle);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ApplicationController.getInstance().getContext().startActivity(intent, bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Launch the respective activity
     *
     * @param screenId
     * @param launchMode
     */
    public void launchActivity(@ViewFactory.ScreenIds int screenId, int launchMode) {
        Intent intent = new Intent(ApplicationController.getInstance().getContext(), ViewFactory.getActivityClass(screenId));
        //TODO impl the launch mode logic
        intent.setFlags(launchMode);
        ApplicationController.getInstance().getContext().startActivity(intent);
    }


    /**
     * Launch the respective activity for result
     *
     * @param screenId
     */
    public void launchActivityForResult(@ViewFactory.ScreenIds int screenId, int reqCode) {
        Intent intent = new Intent(ApplicationController.getInstance().getContext(), ViewFactory.getActivityClass(screenId));
//      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        Activity activity = ApplicationController.getInstance().getActivity();
        if (activity != null) {
            activity.startActivityForResult(intent, reqCode);
        }
    }

    /**
     * Launch the respective fragments
     *
     * @param screenId
     */
    public void launchFragments(@ViewFactory.ScreenIds int screenId) {
        //TODO impl later
    }

}
