package com.iprismtech.komodeo.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;

import java.util.List;
import java.util.Locale;


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
    public static String getAddressString(Context context, double LATITUDE, double LONGITUDE) {

        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE,LONGITUDE, 1);


            if (addresses != null) {
                Address address = addresses.get(0);

                //  Log.i("address.toString() : " + address.toString());

                StringBuilder sb = new StringBuilder("");
                String Feature = address.getFeatureName();
                String ThroughFrare = address.getSubThoroughfare();
                String Sub_Admin = address.getSubAdminArea();
                String Locality = address.getLocality();
                String Admin = address.getAdminArea();
                String SubLocality = address.getSubLocality();
                String Country = address.getCountryName();


                /*if (Feature != null) {
                    sb.append(Feature);
                }
                if (ThroughFrare != null) {
                    sb.append(","+ThroughFrare);
                }
                if (Sub_Admin != null) {
                    sb.append(","+Sub_Admin);
                }*/
                if (SubLocality != null) {
                    //   Log.i("Locality : " + Locality);
                    sb.append(SubLocality);
                }
                if (Locality != null) {
                    //   Log.i("Locality : " + Locality);
                    sb.append(", "+Locality);
                }

//                if (Admin != null) {
//                  //  Log.i("Admin : " + Admin);
//
//                    sb.append(", " + Admin);
//                }
                if (Country != null) {

                    //   Log.i("Country : " + Country);

                    // sb.append(", " + Country);
                }
                strAdd = sb.toString();


                android.util.Log.w("My Current", "" + sb.toString());
            } else {
                android.util.Log.w("My Current", "No Address returned!");
            }


        } catch (Exception e) {
            e.printStackTrace();
            android.util.Log.w("My Current", "Canont get Address!");
        }
        return strAdd;
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
