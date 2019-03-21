package com.iprismtech.komodeo.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;


/**
 * Created by pegasys on 12/7/2017.
 */

public class Common {
    //    public static void customToast(Context context, String toastMessage) {
//        customToast(context, toastMessage, Toast.LENGTH_LONG);
//    }
    public static void dismissProgressDialog(Dialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

    public static Dialog showProgressDialog(Context context) {

//        final AlertDialog progressDialog = new ProgressDialog(context);
        final Dialog progressDialog = new Dialog(context);
        final AlertDialog.Builder progressDialog1 = new AlertDialog.Builder(context);

        View progressView = LayoutInflater.from(context).inflate(R.layout.dialog_view, null);
        progressDialog.setContentView(progressView);

//        progressDialog.setMessage("Please Wait.....");

        progressDialog.setCancelable(false);

        if (!((Activity) context).isFinishing()) {

            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    progressDialog.show();

                }
            });
        }


        return progressDialog;
    }

    public static boolean haveInternet(Context ctx) {
        try {
            NetworkInfo info = ((ConnectivityManager) ctx
                    .getSystemService(Context.CONNECTIVITY_SERVICE))
                    .getActiveNetworkInfo();

            if (info == null || !info.isConnected()) {
                return false;
            }
        } catch (Exception e) {
            Log.d("err", e.toString());
        }
        return true;
    }

    public static <T> T getSpecificDataObject(Object object, Class<T> classOfT) {

        String jsonString = new Gson().toJson(object);

        return new Gson().fromJson(jsonString, classOfT);

    }

    public static void showContentView(Activity activity, boolean showStatus) {

        int visibleStatus = showStatus ? View.VISIBLE : View.GONE;

        activity.findViewById(android.R.id.content).setVisibility(visibleStatus);


    }

    public static void showToast(Context context, String toastMessage) {
        Toast.makeText(context, "" + toastMessage, Toast.LENGTH_SHORT).show();
    }


    public static void commonLogs(Context context, String LogMessage) {
        Log.e("LogMessage", LogMessage);
    }
}
