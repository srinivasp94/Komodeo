/*
package com.iprismtech.komodeo.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.pojo.ProfilePojo;
import com.iprismech.alertnikkiresidence.request.NotificationsReq;
import com.iprismech.alertnikkiresidence.request.ProfileReq;
import com.iprismech.alertnikkiresidence.response.ProfileRes;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitRequester;
import com.iprismech.alertnikkiresidence.retrofitnetwork.RetrofitResponseListener;
import com.iprismech.alertnikkiresidence.utilities.Common;
import com.iprismech.alertnikkiresidence.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class NotificationActivity extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {
    private Switch sw_invite_guest, sw_staff, sw_society, sw_school_bus, sw_kids_pass;
    private Object obj;
    private boolean aBoolean_invite_guest = false, aBoolean_staff = false, aBoolean_society = false, aBoolean_school_bus = false, aBoolean_kids_pass = false;
    private ProfilePojo profilePojo;
    private ImageView imgClose;
    private TextView txtitle;


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_notifications, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        sw_invite_guest = findViewById(R.id.sw_invite_guest);
        sw_staff = findViewById(R.id.sw_staff);
        sw_society = findViewById(R.id.sw_society);
        sw_school_bus = findViewById(R.id.sw_school_bus);
        sw_kids_pass = findViewById(R.id.sw_kids_pass);

        txtitle = findViewById(R.id.txtitle);
        imgClose = findViewById(R.id.imgClose);
        txtitle.setText("Notifications");


        ProfileReq req = new ProfileReq();
        req.userId = SharedPrefsUtils.getInstance(NotificationActivity.this).getId();
        req.userType = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_USER_TYPE);

        try {
            obj = Class.forName(ProfileReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_profile", true);


    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        sw_invite_guest.setOnClickListener(this);
        sw_staff.setOnClickListener(this);
        sw_society.setOnClickListener(this);
        sw_school_bus.setOnClickListener(this);
        sw_kids_pass.setOnClickListener(this);
        imgClose.setOnClickListener(this);

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(NotificationActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            profilePojo = gson.fromJson(jsonString, ProfilePojo.class);
                            String guest_noti_status = profilePojo.getResponse().getInvite_guest();
                            String school_noti_status = profilePojo.getResponse().getSchool_bus();
                            String society_gate_noti_status = profilePojo.getResponse().getSociety_gate();
                            String kids_gatepass_noti_status = profilePojo.getResponse().getKids_gatepass();
                            String my_staff_noti_status = profilePojo.getResponse().getMy_staff();

                            if (guest_noti_status.equalsIgnoreCase("yes")) {
                                sw_invite_guest.setChecked(true);
                            } else
                                sw_invite_guest.setChecked(false);

                            if (school_noti_status.equalsIgnoreCase("yes")) {
                                sw_school_bus.setChecked(true);
                            } else
                                sw_school_bus.setChecked(false);

                            if (society_gate_noti_status.equalsIgnoreCase("yes")) {
                                sw_society.setChecked(true);
                            } else
                                sw_society.setChecked(false);
                            if (kids_gatepass_noti_status.equalsIgnoreCase("yes")) {
                                sw_kids_pass.setChecked(true);
                            } else
                                sw_kids_pass.setChecked(false);
                            if (my_staff_noti_status.equalsIgnoreCase("yes")) {
                                sw_staff.setChecked(true);
                            } else
                                sw_staff.setChecked(false);

                            break;

                        case 2:
                            Toast.makeText(NotificationActivity.this, "Notifications updated for selected categories ", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } else {
                    Common.showToast(NotificationActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sw_invite_guest:

                callNotifySendWS();

                break;
            case R.id.sw_staff:
                callNotifySendWS();
                break;
            case R.id.sw_society:
                callNotifySendWS();
                break;
            case R.id.sw_school_bus:
                callNotifySendWS();
                break;
            case R.id.sw_kids_pass:
                callNotifySendWS();
                break;
            case R.id.imgClose:
                onBackPressed();
                break;
        }
    }

    public void callNotifySendWS() {

        aBoolean_invite_guest = sw_invite_guest.isChecked();
        aBoolean_staff = sw_staff.isChecked();
        aBoolean_society = sw_society.isChecked();
        aBoolean_school_bus = sw_school_bus.isChecked();
        aBoolean_kids_pass = sw_kids_pass.isChecked();

        NotificationsReq notificationsReq = new NotificationsReq();
        notificationsReq.user_id = SharedPrefsUtils.getInstance(NotificationActivity.this).getId();
        if (aBoolean_invite_guest)
            notificationsReq.invite_guest = "yes";
        else
            notificationsReq.invite_guest = "no";
        if (aBoolean_staff)
            notificationsReq.my_staff = "yes";
        else
            notificationsReq.my_staff = "no";
        if (aBoolean_society)
            notificationsReq.society_gate = "yes";
        else notificationsReq.society_gate = "no";
        if (aBoolean_school_bus)
            notificationsReq.school_bus = "yes";
        else notificationsReq.school_bus = "no";
        if (aBoolean_kids_pass)
            notificationsReq.kids_gatepass = "yes";
        else notificationsReq.kids_gatepass = "no";

        // req.user_id = "24";
        try {
            obj = Class.forName(NotificationsReq.class.getName()).cast(notificationsReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "update_user_notifications", true);
    }
}
*/
