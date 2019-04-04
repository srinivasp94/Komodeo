package com.iprismtech.komodeo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.factories.controllers.ApplicationController;
import com.iprismtech.komodeo.request.AddClassReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

public class AddclassFormAct extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private ImageView iv_close;
    private EditText edtSubName, edtCourseName, edtCourse;
    private TextView txtSaveNewClass;
    private Object obj;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.add_class_form_layout);

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.add_class_form_layout, null);
        return view;
    }


    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_close.setOnClickListener(this);
        txtSaveNewClass.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        ApplicationController.getInstance().setContext(this);
        iv_close = findViewById(R.id.iv_close_add_class_form);
        edtSubName = findViewById(R.id.edtSubName);
        edtCourseName = findViewById(R.id.edtCourseName);
        edtCourse = findViewById(R.id.edtCourse);
        txtSaveNewClass = findViewById(R.id.txtSaveNewClass);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close_add_class_form:
                onBackPressed();
                break;
            case R.id.txtSaveNewClass:
                if (edtCourseName.getText().toString().length() == 0) {
                    Common.showToast(AddclassFormAct.this, "Enter Course Name");
                } else if (edtSubName.getText().toString().length() == 0) {
                    Common.showToast(AddclassFormAct.this, "Enter Subject Name");
                } else if (edtCourse.getText().toString().length() == 0) {
                    Common.showToast(AddclassFormAct.this, "Enter Course Code");
                } else {
                    AddClassReq req = new AddClassReq();
                    req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                    req.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
                    req.courseName = edtCourseName.getText().toString() + " " + edtCourse.getText().toString();
                    req.subjectName = edtSubName.getText().toString();
                    req.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
                    try {
                        obj = Class.forName(AddClassReq.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "add_class", true);
                }
                break;
        }
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(AddclassFormAct.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Common.showToast(AddclassFormAct.this, jsonObject.optString("message"));
                            startActivity(new Intent(AddclassFormAct.this, MainActivity.class));
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(AddclassFormAct.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

