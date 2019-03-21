package com.iprismtech.komodeo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class AppPermissions {
    Context context;

    public static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 123;

    public static boolean callPermissionForFiles(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED
                    ) {

                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);

            }

        }
        return true;
    }

    public static boolean callPermissionForContacts(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED
                    ) {

                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);

            }

        }
        return true;
    }

    public static void callPermissionForContact(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS)
                            != PackageManager.PERMISSION_GRANTED
                    ) {

                ActivityCompat.requestPermissions((Activity) context,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);

            }

        }
    }


}
