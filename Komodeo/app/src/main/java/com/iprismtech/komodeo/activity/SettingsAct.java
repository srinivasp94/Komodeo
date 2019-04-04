package com.iprismtech.komodeo.activity;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.ProfileSettingsPojo;
import com.iprismtech.komodeo.pojo.UserProfilePojo;
import com.iprismtech.komodeo.pojo.UserProfileRequest;
import com.iprismtech.komodeo.request.SettingsReq;
import com.iprismtech.komodeo.request.UserProfileReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.Constants;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;
import com.iprismtech.komodeo.utils.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SettingsAct extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    ImageView iv_backarrow;

    int position = 0;

    int accountposition = 0;

    String paymentpreference;

    Boolean accountstatus = false, notifystatus = false, paypref = false;


    String commentsstat, likesstat, friendrequestsstat, eventstat, eventcancelstat;


    boolean pos1 = true, pos2 = true, pos3 = true, pos4 = true;

    private boolean aBoolean_comments = false, aBoolean_likes = false, aBoolean_friendrequests = false, aBoolean_events = false, aBoolean_event_cancel = false;

    ProfileSettingsPojo settingsPojo;

    Switch swch_comments, swch_likes, swch_mentions, swch_event_notifications, swch_friend_request, swch_message, swch_event_cancellation, swch_friend_suggestion;

    ImageView iv_profile_settings, iv_loc, iv_account_details, iv_notify, iv_paymentpreference;

    RetrofitResponseListener retrofitResponseListener;

    private int GALLERY = 1, CAMERA = 2;

    private String type;

    private Bitmap bitmap;

    private ProgressDialog progessDialog;

    private Uri picUri;

    private String base64pic;

    private TextView txt_default_location, txt_update, tv_address;

    RelativeLayout rlaccount, rlnotify, rlpaymentpref, rl_paymentpreference;

    private EditText txt_password, edt_firstname, edt_lastname, edt_mobile, edt_email, edt_university, edt_major;

    private SettingsReq settingsReq;


    LinearLayout ll_pament_venmo, ll_gpay, ll_Paypal, ll_cash,
            ll_account_details,
            ll_notifications;

    private Object obj;

    private String post_Status, selected_lat, selected_lng, selected_address;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        setContentView(R.layout.li_accountsettings);
    }

    @Override
    protected View getView() {

        View view = getLayoutInflater().inflate(R.layout.li_accountsettings, null);

        return view;

    }

    @Override
    protected void initializeViews() {

        super.initializeViews();

        retrofitResponseListener = this;

        iv_profile_settings = findViewById(R.id.iv_profile_settings);

        ll_pament_venmo = findViewById(R.id.ll_pament_venmo);

        /*iv_paymentpreference
iv_notify
iv_account_details*/

        iv_paymentpreference = findViewById(R.id.iv_paymentpreference);
        iv_notify = findViewById(R.id.iv_notify);
        iv_account_details = findViewById(R.id.iv_account_details);

        ll_gpay = findViewById(R.id.ll_gpay);

        /* ll_account_details,
            ll_notifications;*/


        ll_account_details = findViewById(R.id.ll_account_details);

        ll_notifications = findViewById(R.id.ll_notifications);

        ll_pament_venmo = findViewById(R.id.ll_pament_venmo);

        ll_Paypal = findViewById(R.id.ll_Paypal);

        ll_cash = findViewById(R.id.ll_cash);

        iv_loc = findViewById(R.id.iv_loc);

        /*rlaccount, rlnotify, rlpaymentpref, rl_paymentpreference*/

        iv_account_details = findViewById(R.id.iv_account_details);

        iv_notify = findViewById(R.id.iv_notify);

        iv_paymentpreference = findViewById(R.id.iv_paymentpreference);

        rl_paymentpreference = findViewById(R.id.rl_paymentpreference);

        tv_address = findViewById(R.id.tv_address);

        txt_password = findViewById(R.id.txt_password);

        txt_default_location = findViewById(R.id.txt_default_location);

        txt_update = findViewById(R.id.txt_update);

        iv_backarrow = findViewById(R.id.iv_backarrow);

        swch_comments = findViewById(R.id.swch_comments);

        swch_likes = findViewById(R.id.swch_likes);

        swch_event_notifications = findViewById(R.id.swch_event_notifications);

        swch_friend_request = findViewById(R.id.swch_friend_request);

        swch_event_cancellation = findViewById(R.id.swch_event_cancellation);

        edt_firstname = findViewById(R.id.edt_firstname);

        edt_lastname = findViewById(R.id.edt_lastname);

        edt_mobile = findViewById(R.id.edt_mobile);

        edt_email = findViewById(R.id.edt_emailid);

        edt_university = findViewById(R.id.edt_university);

        edt_major = findViewById(R.id.edt_major);

        UserProfileReq userProfileReq = new UserProfileReq();

        userProfileReq.userId = SharedPrefsUtils.getInstance(SettingsAct.this).getId();

        userProfileReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);

        try {

            obj = Class.forName(UserProfileReq.class.getName()).cast(userProfileReq);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "user_profile", true);
    }

    @Override
    public void onBackPressed() {

        finish();

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_backarrow.setOnClickListener(this);
        iv_profile_settings.setOnClickListener(this);
        txt_update.setOnClickListener(this);
        /* rlaccount = findViewById(R.id.rlaccount);
        rlnotify = findViewById(R.id.rlnotify);
        rlpaymentpref = findViewById(R.id.rlpaymentpref);*/
        iv_account_details.setOnClickListener(this);
        iv_notify.setOnClickListener(this);
        iv_paymentpreference.setOnClickListener(this);
        ll_pament_venmo.setOnClickListener(this);
        ll_gpay.setOnClickListener(this);
        ll_Paypal.setOnClickListener(this);
        ll_cash.setOnClickListener(this);
        iv_loc.setOnClickListener(this);


        swch_comments.setOnClickListener(this);
        swch_likes.setOnClickListener(this);
        swch_event_notifications.setOnClickListener(this);
        swch_friend_request.setOnClickListener(this);
        swch_event_cancellation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_backarrow:
                onBackPressed();
                break;
/*        /* rlaccount = findViewById(R.id.rlaccount);
        rlnotify = findViewById(R.id.rlnotify);
        rlpaymentpref = findViewById(R.id.rlpaymentpref);*/
            case R.id.iv_account_details:


                if (accountstatus) {
                    ll_account_details.setVisibility(View.GONE);
                    accountstatus = false;
                    iv_account_details.setImageResource(R.drawable.down);
                } else {
                    ll_account_details.setVisibility(View.VISIBLE);
                    accountstatus = true;
                    iv_account_details.setImageResource(R.drawable.up);


                }
                break;

            case R.id.iv_notify:

                if (notifystatus) {
                    ll_notifications.setVisibility(View.GONE);
                    notifystatus = false;
                    iv_notify.setImageResource(R.drawable.down);
                } else {
                    ll_notifications.setVisibility(View.VISIBLE);
                    notifystatus = true;
                    iv_notify.setImageResource(R.drawable.up);

                }
                break;


            case R.id.iv_paymentpreference:

                if (paypref) {
                    rl_paymentpreference.setVisibility(View.GONE);
                    paypref = false;
                    iv_paymentpreference.setImageResource(R.drawable.down);

                } else {
                    rl_paymentpreference.setVisibility(View.VISIBLE);
                    paypref = true;
                    iv_paymentpreference.setImageResource(R.drawable.up);

                }

                break;
            case R.id.ll_pament_venmo:
                if (position == 0) {
                    if (pos1) {
                        paymentpreference = "Venmo";
                        ll_pament_venmo.setBackgroundResource(R.drawable.settings_noti_frequency_bg);
                        ll_cash.setBackgroundResource(R.color.transparent);
                        ll_gpay.setBackgroundResource(R.color.transparent);
                        ll_Paypal.setBackgroundResource(R.color.transparent);
                        break;
                    }
                }


            case R.id.ll_Paypal:
                if (position == 0) {
                    if (pos2) {
                        paymentpreference = "Paypal";
                        ll_Paypal.setBackgroundResource(R.drawable.settings_noti_frequency_bg);
                        ll_cash.setBackgroundResource(R.color.transparent);
                        ll_gpay.setBackgroundResource(R.color.transparent);
                        ll_pament_venmo.setBackgroundResource(R.color.transparent);
                        break;
                    }
                }


            case R.id.ll_gpay:
                if (position == 0) {
                    if (pos3) {
                        paymentpreference = "Google Pay";
                        ll_gpay.setBackgroundResource(R.drawable.settings_noti_frequency_bg);
                        ll_cash.setBackgroundResource(R.color.transparent);
                        ll_Paypal.setBackgroundResource(R.color.transparent);
                        ll_pament_venmo.setBackgroundResource(R.color.transparent);
                        break;
                    }
                }


            case R.id.ll_cash:
                if (position == 0) {
                    if (pos4) {
                        paymentpreference = "Cash";
                        ll_cash.setBackgroundResource(R.drawable.settings_noti_frequency_bg);
                        ll_Paypal.setBackgroundResource(R.color.transparent);
                        ll_gpay.setBackgroundResource(R.color.transparent);
                        ll_pament_venmo.setBackgroundResource(R.color.transparent);

                        break;
                    }
                }

                break;



           /* case R.id.txt_default_location:
                Intent intent = new Intent(SettingsAct.this, SearchLocationActivity.class);
                startActivity(intent);
                break;*/

            case R.id.iv_profile_settings:
                showPictureDialog("document");
                break;
            case R.id.iv_loc:
                Intent intent1 = new Intent(SettingsAct.this, LocationGetActivity.class);
                startActivityForResult(intent1, 101);

                // startActivityForResult(new Intent(CreateTutorRequestAct.this,LocationGetActivity.class));
                break;


            case R.id.txt_update:

                callNotifySendWS();
                break;
                /*if (edt_firstname.getText().toString() == "") {
                    Toast.makeText(SettingsAct.this, "Please enter First Name", Toast.LENGTH_SHORT).show();

                } else if (edt_lastname.getText().toString() == "") {

                    Toast.makeText(this, "Please enter Last Name", Toast.LENGTH_SHORT).show();

                } else if (edt_mobile.getText().toString() == "") {
                    Toast.makeText(this, "Please enter Mobile Number", Toast.LENGTH_SHORT).show();


                } else if (edt_mobile.length() < 10) {
                    Toast.makeText(this, "Please enter Valid Mobile Number", Toast.LENGTH_SHORT).show();


                } else if (edt_major.getText().toString() == "") {

                    Toast.makeText(this, "Please enter Major of Study", Toast.LENGTH_SHORT).show();


                } else {


                    new RetrofitRequester(this).callPostServices(obj, 3, "update_profile", true);
                    break;
                }*/


        }
    }

    @Override
    public void setPresenter() {

    }


    private void showPictureDialog(final String base64) {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary(base64);
                                break;
                            case 1:
                                takePhotoFromCamera(base64);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }


    public void choosePhotoFromGallary(String base64) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        type = base64;
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera(String base64) {
        try {
            type = base64;

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(intent, CAMERA);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse == "") {

            Common.showToast(SettingsAct.this, "Please Try Again");

        } else {

            try {
                Gson gson = new Gson();

                String jsonString = gson.toJson(objectResponse);

                JSONObject jsonObject = new JSONObject(jsonString);

                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {

                        case 2:

                            UserProfileRequest res = Common.getSpecificDataObject(objectResponse, UserProfileRequest.class);

                            UserProfilePojo userProfilePojo = res.response;


                            Common.showToast(SettingsAct.this, jsonObject.optString("message"));

                            edt_firstname.setText(res.response.firstName);

                            edt_lastname.setText(res.response.lastName);

                            edt_email.setText(res.response.emailId);

                            edt_mobile.setText(res.response.mobile);

                            edt_major.setText(res.response.major);

                            txt_password.setText(res.response.password);

                            edt_university.setText(res.response.university);
                            Picasso.with(context).load(Constants.BASE_IMAGE_URL + res.response.profilePic).
                                    error(R.drawable.manager).into(iv_profile_settings);


                            String res_switch_comments = userProfilePojo.comments;
                            String res_switch_likes = userProfilePojo.likes;
                            String res_switch_friendrequest = userProfilePojo.friendRequests;
                            String res_switch_event_noti = userProfilePojo.events;
                            String res_switch_eventcancellation = userProfilePojo.eventCancel;


                            if (res_switch_comments.equalsIgnoreCase("yes")) {
                                swch_comments.setChecked(true);

                            } else {
                                swch_comments.setChecked(false);
                            }

                            if (res_switch_likes.equalsIgnoreCase("yes")) {
                                swch_likes.setChecked(true);

                            } else {
                                swch_likes.setChecked(false);
                            }
                            if (res_switch_friendrequest.equalsIgnoreCase("yes")) {
                                swch_friend_request.setChecked(true);

                            } else {
                                swch_friend_request.setChecked(false);
                            }
                            if (res_switch_event_noti.equalsIgnoreCase("yes")) {
                                swch_event_notifications.setChecked(true);

                            } else {
                                swch_event_notifications.setChecked(false);
                            }
                            if (res_switch_eventcancellation.equalsIgnoreCase("yes")) {
                                swch_event_cancellation.setChecked(true);

                            } else {
                                swch_event_cancellation.setChecked(false);
                            }


                            if (res.response.paymentPreference.equalsIgnoreCase("Paypal")) {
                                ll_Paypal.setBackgroundResource(R.drawable.settings_noti_frequency_bg);

                            } else if (res.response.paymentPreference.equalsIgnoreCase("Venmo")) {
                                ll_pament_venmo.setBackgroundResource(R.drawable.settings_noti_frequency_bg);

                            } else if (res.response.paymentPreference.equalsIgnoreCase("Google Pay")) {
                                ll_gpay.setBackgroundResource(R.drawable.settings_noti_frequency_bg);

                            } else if (res.response.paymentPreference.equalsIgnoreCase("Cash")) {
                                ll_cash.setBackgroundResource(R.drawable.settings_noti_frequency_bg);
                            } else {

                            }


//                            edt_university.setText(res.response.un);

                            break;

                        case 3:

                            settingsReq = Common.getSpecificDataObject(objectResponse, SettingsReq.class);

                            Common.showToast(SettingsAct.this, jsonObject.optString("message"));


                            UserProfileReq userProfileReq = new UserProfileReq();

                            userProfileReq.userId = SharedPrefsUtils.getInstance(SettingsAct.this).getId();

                            userProfileReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);

                            settingsPojo = gson.fromJson(jsonString, ProfileSettingsPojo.class);

                           /* "payment_preference": "Paypal",
                                "comments": "yes",
                                "likes": "yes",
                                "friend_requests": "yes",
                                "events": "yes",
                                "event_cancel": "yes",*/
                            String comments_status = settingsPojo.comments;
                            String likes_status = settingsPojo.likes;
//                            String mentions_status = settingsPojo.;
                            String frienrequest_status = settingsPojo.friendRequests;
                            String events_status = settingsPojo.events;

                            String event_cancel = settingsPojo.eventCancel;

                            try {
                                obj = Class.forName(UserProfileReq.class.getName()).cast(userProfileReq);


                            } catch (ClassNotFoundException e) {

                                e.printStackTrace();
                            }
                            new RetrofitRequester(this).callPostServices(obj, 2, "user_profile", true);

                            break;
                    }
                } else {

                    Common.showToast(SettingsAct.this, jsonObject.optString("message"));

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        } else if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();

                try {
                    picUri = data.getData();
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
                    if (type.equalsIgnoreCase("profile")) {
                        new Async_BitmapWorkerTaskForPic().execute();
                    } else if (type.equalsIgnoreCase("document")) {
                        new Async_BitmapWorkerTaskForPic().execute();

                    }
                    //   CropImage();
                }
                // respond to users whose devices do not support the crop action
                catch (ActivityNotFoundException anfe) {
                    // display an error message
                    String errorMessage = "Whoops - your device doesn't support the crop action!";
                    Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                    toast.show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //     new Async_BitmapWorkerTask().execute();
                // String path = saveImage(bitmap);


            }

        } else if (requestCode == CAMERA) {
        /*  bitmap = (Bitmap) data.getExtras().get("data");

         // imgprofile.setImageBitmap(bitmap);
          new Async_BitmapWorkerTask().execute();*/
            bitmap = (Bitmap) data.getExtras().get("data");

            // imgprofile.setImageBitmap(bitmap);
            if (type.equalsIgnoreCase("profile")) {
                new Async_BitmapWorkerTaskForPic().execute();
            } else if (type.equalsIgnoreCase("document")) {
                new Async_BitmapWorkerTaskForPic().execute();
            }
            // CropImage();
            //saveImage(thumbnail);

        } else if (requestCode == 1000) {

            bitmap = (Bitmap) data.getExtras().get("data");

            // imgprofile.setImageBitmap(bitmap);
            if (type.equalsIgnoreCase("profile")) {
                new Async_BitmapWorkerTaskForPic().execute();
            } else if (type.equalsIgnoreCase("document")) {
                new Async_BitmapWorkerTaskForPic().execute();
            }

        }
        if (requestCode == 101) {
            post_Status = data.getStringExtra("location_Status");
            if (post_Status.equalsIgnoreCase("ok")) {
//                    TextView count_commnets = adaptet_item_view.getRootView().findViewById(R.id.tv_comments_count);
//                    int str_count_comments = Integer.parseInt(count_commnets.getText().toString());
//                    str_count_comments = str_count_comments + 1;
//                    count_commnets.setText(str_count_comments + "");
                selected_lat = data.getStringExtra("user_lat");
                selected_lng = data.getStringExtra("user_lang");
                selected_address = data.getStringExtra("user_address");
                tv_address.setText(selected_address);
            }

        }
    }


    class Async_BitmapWorkerTaskForPic extends AsyncTask<Integer, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progessDialog = new ProgressDialog(SettingsAct.this);
            progessDialog.setMessage("Image Loading");
            progessDialog.show();
        }

        // Compress and Decode image in background.
        @Override
        protected String doInBackground(Integer... params) {

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byte_arr = stream.toByteArray();
            base64pic = Base64.encodeToString(byte_arr, Base64.DEFAULT);
            return base64pic.trim();
        }

        // This method is run on the UI thread
        @Override
        protected void onPostExecute(String string) {
            try {
                progessDialog.dismiss();
                iv_profile_settings.setVisibility(View.VISIBLE);


                Variables.profilr = base64pic;
                iv_profile_settings.setImageBitmap(bitmap);
                System.out.println(string);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void callNotifySendWS() {

        SettingsReq settingsReq = new SettingsReq();

        aBoolean_comments = swch_comments.isChecked();
        aBoolean_likes = swch_likes.isChecked();
        aBoolean_friendrequests = swch_friend_request.isChecked();
        aBoolean_events = swch_event_notifications.isChecked();
        aBoolean_event_cancel = swch_event_cancellation.isChecked();

        if (aBoolean_comments)
            settingsReq.comments = "yes";

        else
            settingsReq.comments = "no";


        if (aBoolean_likes)
            settingsReq.likes = "yes";
        else
            settingsReq.likes = "no";
        if (aBoolean_friendrequests)
            settingsReq.friendRequests = "yes";
        else
            settingsReq.friendRequests = "no";
        if (aBoolean_events)
            settingsReq.events = "yes";
        else
            settingsReq.events = "no";
        if (aBoolean_event_cancel)
            settingsReq.eventCancel = "yes";
        else
            settingsReq.eventCancel = "no";

        settingsReq.userId = SharedPrefsUtils.getInstance(SettingsAct.this).getId();
        settingsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        settingsReq.firstName = edt_firstname.getText().toString();
        settingsReq.lastName = edt_lastname.getText().toString();
        settingsReq.mobile = edt_mobile.getText().toString();
        settingsReq.major = edt_major.getText().toString();
        settingsReq.paymentPreference = paymentpreference;
        settingsReq.defaultLat = selected_lat;
        settingsReq.defaultLng = selected_lng;
        settingsReq.defaultAddress = selected_address;
        settingsReq.comments = settingsReq.comments;
        settingsReq.likes = settingsReq.likes;
        settingsReq.friendRequests = settingsReq.friendRequests;
        settingsReq.events = settingsReq.events;
        settingsReq.eventCancel = settingsReq.eventCancel;
        settingsReq.profilePic = base64pic;

        try {
            obj = Class.forName(SettingsReq.class.getName()).cast(settingsReq);

        } catch (Exception e) {

            e.printStackTrace();

        }
        new RetrofitRequester(this).callPostServices(obj, 3, "update_profile", true);

    }


}
