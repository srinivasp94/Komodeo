package com.iprismtech.komodeo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtils {
    private Context context;
    private static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    public static final String PREF_NAME = "myprefs";
    public static final String IS_USER_LOGIN = "IsUserLoggedIn";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PROFILE = "profile_pic";
    public static final String KEY_UNIVERSITY_ID = "university_id";
    public static final String KEY_STATUS = "status";
    public static final String KEY_TOKEN = "token";
    public static final String KEY_FIREBASE_TOKEN = "firebase_token";
    public static final String KEY_REGISTERED_THROUGH = "registered_through";


    private static SharedPrefsUtils prefsUtils;

    public static SharedPrefsUtils getInstance(Context context) {
        if (prefsUtils == null) {
            prefsUtils = new SharedPrefsUtils(context);
        }
        return prefsUtils;

    }

    public SharedPrefsUtils(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    //constructor

    public SharedPrefsUtils(Context context, SharedPreferences preferences) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }


    //Saving user details
    public void createUserSession(String id, String name, String lastname, String email, String universityID, String registered_through) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_LAST_NAME, lastname);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_UNIVERSITY_ID, universityID);
        editor.putString(KEY_REGISTERED_THROUGH, registered_through);
        editor.commit();
    }


    public static void setString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void updateString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key) {
        return preferences.getString(key, "");
    }


    public String getId() {
        return preferences.getString(KEY_ID, "");
    }


    public String getEmial() {
        return preferences.getString(KEY_EMAIL, "");
    }

    public String getPhoneNumber() {
        return preferences.getString(KEY_PHONE, "");
    }


    public boolean isUserLoggedIn() {
        return preferences.getBoolean(IS_USER_LOGIN, false);
    }


    public static void logoutUser() {
        editor.clear();
        editor.commit();
    }

}
