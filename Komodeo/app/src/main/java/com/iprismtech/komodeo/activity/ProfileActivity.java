package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.request.UserProfileReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

public class ProfileActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private ImageView iv_viewratings, iv_chat;
    private RelativeLayout rl_review_events;
    private ImageView iv_pro_back, iv_profile;
    private LinearLayout ll_editavailability, llcal1;
    private boolean istrue = true;
    private TextView txt_profile, txt_name, txt_profile_subject, txt_profile_friends, txt_study, tv_profile_tutor;
    private Object obj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        iv_pro_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        rl_review_events.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfileActivity.this, CreateQuarterEventAct.class);
//                startActivity(intent);
//            }
//        });
//        ll_editavailability.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (istrue) {
//                    llcal1.setVisibility(View.VISIBLE);
//                } else {
//                    llcal1.setVisibility(View.GONE);
//                }
//            }
//        });
//        iv_viewratings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfileActivity.this, ViewRatingsAct.class);
//                startActivity(intent);
//            }
//        });
//        iv_chat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ProfileActivity.this, ChatAct.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.student_profile, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_viewratings.setOnClickListener(this);
        iv_chat.setOnClickListener(this);
        ll_editavailability.setOnClickListener(this);
        rl_review_events.setOnClickListener(this);
        iv_pro_back.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        iv_pro_back = findViewById(R.id.iv_pro_back);
        iv_chat = findViewById(R.id.iv_chat);
        iv_viewratings = findViewById(R.id.iv_viewratings);
        rl_review_events = findViewById(R.id.rl_review_events);
        ll_editavailability = findViewById(R.id.ll_editavailability);
        llcal1 = findViewById(R.id.ll_cal1);
        txt_name = findViewById(R.id.txt_name);
        txt_profile_subject = findViewById(R.id.txt_profile_subject);
        iv_profile = findViewById(R.id.iv_profile);
        txt_profile_friends = findViewById(R.id.txt_profile_friends);
        txt_study = findViewById(R.id.txt_study);
        tv_profile_tutor = findViewById(R.id.tv_profile_tutor);


        UserProfileReq userProfileReq = new UserProfileReq();
        userProfileReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        userProfileReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        //flatListRequest.building_id="4";
        try {
            obj = Class.forName(UserProfileReq.class.getName()).cast(userProfileReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_profile", false);

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ProfileActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:

                            break;
                    }
                } else {
                    Common.showToast(ProfileActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
