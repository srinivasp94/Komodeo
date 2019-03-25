package com.iprismtech.komodeo.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;

public class AlertUtils {
    AlertDialog dialog = null;
    private Context mContext;


    public static void showSimpleAlert(Context mContext, String message) {
        showSimpleAlert(mContext, message, null, null, null, null);
    }


    public static void showSimpleAlert(Context mContext, String message, String title) {
        showSimpleAlert(mContext, message, title, null, null, null);
    }

    public static void showSimpleAlert(Context mContext, String message, String title, String positiveButton) {
        showSimpleAlert(mContext, message, title, positiveButton, null, null);
    }


    public static void showSimpleAlert(Context mContext, String message, String title, String positiveButton, String negativeButton) {
        showSimpleAlert(mContext, message, title, positiveButton, negativeButton, null);
    }

    public static void showSimpleAlert(Context mContext, String message, String title, String positiveButton, String negativeButton, final onClicklistners onClicklistners) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message))
            builder.setMessage(message);
        if (!TextUtils.isEmpty(positiveButton)) {
            builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    onClicklistners.onpositiveclick();
                }
            });
        }
        if (!TextUtils.isEmpty(negativeButton)) {
            builder.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    onClicklistners.onNegativeClick();
                }
            });
        }
        builder.setCancelable(false);


        builder.show();

    }

    public static void showCustomAlert(Context mContext, View view, String message, String title, onClicklistners onClicklistners) {


    }

    public interface onClicklistners {

        void onpositiveclick();

        void onNegativeClick();
    }

}
