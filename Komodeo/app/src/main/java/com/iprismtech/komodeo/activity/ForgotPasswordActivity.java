package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.ForgotPasswordPojo;
import com.iprismtech.komodeo.pojo.UserProfilePojo;
import com.iprismtech.komodeo.request.ForgotPasswordReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

public class ForgotPasswordActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    EditText edt_email;
    ForgotPasswordReq forgotPasswordReq;
    ForgotPasswordPojo forgotPasswordPojo;
    private Object obj;
    TextView btn_resetpassword;
    private ImageView myaccountback;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_resetpassword:

                ForgotPasswordReq req = new ForgotPasswordReq();

                req.emailId = edt_email.getText().toString();

                try {
                    obj = Class.forName(ForgotPasswordReq.class.getName()).cast(req);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                new RetrofitRequester(this).callPostServices(obj, 2, "forgot_password", true);

                break;

            case R.id.myaccountback:

                onBackPressed();

                break;

        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.forgot_password_layout, null);
        return view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse == "") {

            Common.showToast(ForgotPasswordActivity.this, "Please Try Again");

        } else {

            try {
                Gson gson = new Gson();

                String jsonString = gson.toJson(objectResponse);

                JSONObject jsonObject = new JSONObject(jsonString);

                if (jsonObject.optBoolean("status")) {

                    switch (requestId) {


                        case 2:

                            forgotPasswordReq = Common.getSpecificDataObject(objectResponse, ForgotPasswordReq.class);

                            ForgotPasswordPojo forgotPasswordPojo = forgotPasswordReq.response;

                            String uid = forgotPasswordPojo.id;
                            Common.showToast(ForgotPasswordActivity.this, jsonObject.optString("message"));

                            Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
                            intent.putExtra("uid", uid);
                            startActivity(intent);

                            finish();

                    }

                } else {

                    Common.showToast(ForgotPasswordActivity.this, jsonObject.optString("message"));

                }

            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        btn_resetpassword.setOnClickListener(this);
        myaccountback.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {

        super.initializeViews();
        edt_email = findViewById(R.id.edt_email);
        myaccountback = findViewById(R.id.myaccountback);
        btn_resetpassword = findViewById(R.id.btn_resetpassword);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
