package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.factories.controllers.ApplicationController;
import com.iprismtech.komodeo.pojo.UserProfilePojo;
import com.iprismtech.komodeo.pojo.UserProfileRequest;
import com.iprismtech.komodeo.request.EditBioReq;
import com.iprismtech.komodeo.request.UserProfileReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.Constants;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class UserProfileActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private Object obj;
    ImageView iv_profile, iv_edit_profile,iv_pro_back;
    TextView txt_name, txt_profile_subject, txt_biodesc;
    AlertDialog alert;
    ImageView iv_editbio;
    LinearLayout ll_Uploadcredentials;
    RetrofitResponseListener retrofitResponseListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_user_profile);

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.student_profile, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_editbio.setOnClickListener(this);
        iv_edit_profile.setOnClickListener(this);
        iv_pro_back.setOnClickListener(this);
        ll_Uploadcredentials.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(this);
        retrofitResponseListener = this;
        iv_profile = findViewById(R.id.iv_profile);
        iv_pro_back = findViewById(R.id.iv_pro_back);
        iv_edit_profile = findViewById(R.id.iv_edit_profile);
        iv_editbio = findViewById(R.id.iv_editbio);
        ll_Uploadcredentials = findViewById(R.id.ll_Uploadcredentials);
        txt_name = findViewById(R.id.txt_name_user_profile);
        txt_profile_subject = findViewById(R.id.txt_profile_subject);
        txt_biodesc = findViewById(R.id.txt_biodesc);

       /* txt_biodesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opeDialog();
            }
        })*/

        UserProfileReq userProfileReq = new UserProfileReq();

        userProfileReq.userId = SharedPrefsUtils.getInstance(UserProfileActivity.this).getId();

        userProfileReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);

        try {
            obj = Class.forName(UserProfileReq.class.getName()).cast(userProfileReq);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "user_profile", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse == "") {

            Common.showToast(UserProfileActivity.this, "Please Try Again");

        } else {

            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 2:
                            UserProfileRequest res = Common.getSpecificDataObject(objectResponse, UserProfileRequest.class);
                            UserProfilePojo userProfilePojo=res.response;
                            Common.showToast(UserProfileActivity.this, jsonObject.optString("message"));
                            txt_name.setText(res.response.firstName + "" + res.response.lastName);
                            txt_profile_subject.setText(res.response.major);
                            txt_biodesc.setText((CharSequence) res.response.bio);
                            Picasso.with(context).load(Constants.BASE_IMAGE_URL + res.response.profilePic).
                                    error(R.drawable.manager).into(iv_profile);
                            break;

                        case 3:

                            alert.dismiss();

                            EditBioReq editBioReq = Common.getSpecificDataObject(objectResponse, EditBioReq.class);
                            UserProfileReq userProfileReq = new UserProfileReq();


                            userProfileReq.userId = SharedPrefsUtils.getInstance(UserProfileActivity.this).getId();

                            userProfileReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);


                            try {
                                obj = Class.forName(UserProfileReq.class.getName()).cast(userProfileReq);
                            } catch (ClassNotFoundException e) {

                                e.printStackTrace();
                            }
                            new RetrofitRequester(this).callPostServices(obj, 2, "user_profile", true);

                            break;
                    }
                } else {

                    Common.showToast(UserProfileActivity.this, jsonObject.optString("message"));

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void opeProfileDialog() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_edit_bio, null);
        final EditText edt_editbio = view.findViewById(R.id.edt_editbio);
        b.setCancelable(true);
        b.setView(view);
        alert = b.create();
        alert.getWindow().setLayout(200, 300);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(alert.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        int dialogWindowWidth = (int) (displayWidth * 0.7f);
        int dialogWindowHeight = (int) (displayHeight * 0.7f);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        // Apply the newly created layout parameters to the alert dialog window
        alert.getWindow().setAttributes(layoutParams);
        alert.show();
    }


    public void opeDialog() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_edit_bio, null);


        final EditText edt_editbio = view.findViewById(R.id.edt_editbio);
        LinearLayout ll_save = view.findViewById(R.id.ll_save);
        LinearLayout ll_cancel = view.findViewById(R.id.ll_cancel);
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

        ll_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditBioReq editBioReq = new EditBioReq();

                editBioReq.userId = SharedPrefsUtils.getInstance(UserProfileActivity.this).getId();

                editBioReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);

                editBioReq.bio = edt_editbio.getText().toString();

                try {
                    obj = Class.forName(EditBioReq.class.getName()).cast(editBioReq);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(retrofitResponseListener).callPostServices(obj, 3, "update_bio", false);
            }
        });

        b.setCancelable(true);
        b.setView(view);
        alert = b.create();
        alert.getWindow().setLayout(200, 300);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(alert.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        int dialogWindowWidth = (int) (displayWidth * 0.7f);
        int dialogWindowHeight = (int) (displayHeight * 0.7f);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        // Apply the newly created layout parameters to the alert dialog window
        alert.getWindow().setAttributes(layoutParams);
        alert.show();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_Uploadcredentials:
                startActivity(new Intent(UserProfileActivity.this,UploadCredentialsActivity.class));

                break;
            case R.id.iv_editbio:
                opeDialog();
                break;

            case R.id.iv_edit_profile:
                Intent intent = new Intent(UserProfileActivity.this, SettingsAct.class);
                startActivity(intent);
                break;
            case R.id.iv_pro_back:
                onBackPressed();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
