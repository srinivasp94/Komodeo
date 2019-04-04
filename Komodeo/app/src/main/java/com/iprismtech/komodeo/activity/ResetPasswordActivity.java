package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.request.ForgotPasswordReq;
import com.iprismtech.komodeo.request.UpdatePasswordReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

public class ResetPasswordActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    EditText etupass, etconfirmpass;
    TextView btn_save;
    String uid;
    private Object obj;
    UpdatePasswordReq updatePasswordReq;
    Bundle bundle;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                UpdatePasswordReq req = new UpdatePasswordReq();

                req.userId = uid;
                req.password = etupass.getText().toString();


                try {
                    obj = Class.forName(UpdatePasswordReq.class.getName()).cast(req);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                new RetrofitRequester(this).callPostServices(obj, 2, "update_password", true);

                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.reset_password_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse == "") {

            Common.showToast(ResetPasswordActivity.this, "Please Try Again");

        } else {

            try {
                Gson gson = new Gson();

                String jsonString = gson.toJson(objectResponse);

                JSONObject jsonObject = new JSONObject(jsonString);

                if (jsonObject.optBoolean("status")) {

                    switch (requestId) {


                        case 2:

                            updatePasswordReq = Common.getSpecificDataObject(objectResponse, UpdatePasswordReq.class);

                            Common.showToast(ResetPasswordActivity.this, jsonObject.optString("message"));

                            startActivity(new Intent(ResetPasswordActivity.this, LoginAct.class));

                            finish();


                    }

                } else {

                    Common.showToast(ResetPasswordActivity.this, jsonObject.optString("message"));

                }

            } catch (Exception e) {

                e.printStackTrace();

            }

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        bundle = getIntent().getExtras();
        uid = bundle.getString("uid");
        etupass = findViewById(R.id.etupass);
        etconfirmpass = findViewById(R.id.etconfirmpass);
        btn_save = findViewById(R.id.btn_save);
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        btn_save.setOnClickListener(this);
    }
}
