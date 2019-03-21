package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.request.LoginReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

public class LoginAct extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private TextView txt_signup, txt_signin, txt_forgotpass;
    private EditText edtuserName, edtPassword;
    private Object obj;
    SharedPrefsUtils utils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login_layout);

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.login_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txt_signin.setOnClickListener(this);
        txt_signup.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        utils = SharedPrefsUtils.getInstance(LoginAct.this);
        txt_signup = findViewById(R.id.txt_signup);
        txt_signin = findViewById(R.id.txt_signin);
        txt_forgotpass = findViewById(R.id.txt_forgotpass);
        edtPassword = findViewById(R.id.edtPassword);
        edtuserName = findViewById(R.id.edtuserName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_signup:
                Intent intent = new Intent(LoginAct.this, SignupAct.class);
                startActivity(intent);
                break;
            case R.id.txt_signin:
                //validations
                if (edtuserName.getText().toString().length() == 0) {
                    Common.showToast(LoginAct.this, "Enter user Name");
                } else if (edtPassword.getText().toString().length() == 0) {
                    Common.showToast(LoginAct.this, "Enter Password");
                } else {
                    LoginReq req = new LoginReq();
                    req.email_id = edtuserName.getText().toString();
                    req.password = edtPassword.getText().toString();
                    try {
                        obj = Class.forName(LoginReq.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "user_login", true);

                }
                break;
        }


    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(LoginAct.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            JSONObject response = jsonObject.optJSONObject("response");
                            String tokenbase = jsonObject.optString("token");
                            SharedPrefsUtils.setString(SharedPrefsUtils.KEY_TOKEN, tokenbase);


                            /* "id": "1",
                                "first_name": "Khadeer",
                                "last_name": "Mohammed",
                                "email_id": "khadeer.md@iprismtech.com",
                                "password": "e10adc3949ba59abbe56e057f20f883e",
                                "university_id": "1",
                                "registered_through": "normal",
                                "token": "asdfsdfg5df65fhgh6kldfdf6552df56fgjiofgf5gfg96f5gfgjfgofkgoffdsfsf748r",
                                "ios_token": "",
                                "status": "1",
                                "delete_status": "1",
                                "created_on": "2019-02-15 12:29:08",
                                "modified_on": null*/


                            utils.createUserSession(
                                    response.optString("id"),
                                    response.optString("first_name"),
                                    response.optString("last_name"),
                                    response.optString("email_id"),
                                    response.optString("university_id"),
                                    response.optString("registered_through")
                            );
                            Common.showToast(LoginAct.this, "" + tokenbase);
                            Common.commonLogs(LoginAct.this, tokenbase);

                            Log.d("@@TOKEN", tokenbase);

                            Intent intent1 = new Intent(LoginAct.this, MainActivity.class);
                            startActivity(intent1);
                            break;
                    }
                } else {
                    Common.showToast(LoginAct.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
