package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.UniversitySearchAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.UniversitiSearchPojo;
import com.iprismtech.komodeo.request.SignUpReq;
import com.iprismtech.komodeo.request.UniversitySearchReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;

import org.json.JSONObject;

public class SignupAct extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private EditText et_user_firstname, et_user_lastname, et_user_email, et_user_password, search_universities;
    private TextView tv_user_signup;
    private Object obj;
    private UniversitiSearchPojo universitiSearchPojo;
    private RecyclerView rview_search_universities;
    private LinearLayoutManager manager;
    private UniversitySearchAdapter adapter;
    private String university_id;


    @Override
    public void onClick(View v) {

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.signup_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        et_user_firstname = findViewById(R.id.et_user_firstname);
        et_user_lastname = findViewById(R.id.et_user_lastname);
        et_user_email = findViewById(R.id.et_user_email);
        et_user_password = findViewById(R.id.et_user_password);
        tv_user_signup = findViewById(R.id.tv_user_signup);
        search_universities = findViewById(R.id.search_universities);
        rview_search_universities = findViewById(R.id.rview_search_universities);

        search_universities.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1) {

                    callSearchUniverityWS(s.toString());
                } else if (s.length() == 0) {

                }
                // return false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tv_user_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (search_universities.getText().toString().isEmpty()) {
                    Toast.makeText(SignupAct.this, "Select University", Toast.LENGTH_SHORT).show();
                } else if (et_user_firstname.getText().toString().isEmpty()) {
                    Toast.makeText(SignupAct.this, "Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (et_user_lastname.getText().toString().isEmpty()) {
                    Toast.makeText(SignupAct.this, "Enter Last Name", Toast.LENGTH_SHORT).show();
                } else if (et_user_email.getText().toString().isEmpty()) {
                    Toast.makeText(SignupAct.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (et_user_password.getText().toString().isEmpty()) {
                    Toast.makeText(SignupAct.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (et_user_password.getText().toString().length() < 8) {
                    Toast.makeText(SignupAct.this, "Password must be 8 characters", Toast.LENGTH_SHORT).show();
                } else {
                    callRegisterWS();
                }
            }
        });

    }

    private void callSearchUniverityWS(String s) {
        UniversitySearchReq universitySearchReq = new UniversitySearchReq();
        universitySearchReq.keyword = s;
        try {
            obj = Class.forName(UniversitySearchReq.class.getName()).cast(universitySearchReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "search_universities", false);

    }

    private void callRegisterWS() {
        SignUpReq req = new SignUpReq();
        req.firstName = et_user_firstname.getText().toString();
        req.lastName = et_user_lastname.getText().toString();
        req.emailId = et_user_email.getText().toString();
        req.password = et_user_password.getText().toString();
        req.universityId = university_id;
        try {
            obj = Class.forName(SignUpReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "register_user", true);

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(SignupAct.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:

                            universitiSearchPojo = gson.fromJson(jsonString, UniversitiSearchPojo.class);
                            manager = new LinearLayoutManager(SignupAct.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_search_universities.setLayoutManager(manager);
                            adapter = new UniversitySearchAdapter(SignupAct.this, universitiSearchPojo);
                            rview_search_universities.setAdapter(adapter);
                            adapter.setOnItemClickListener(new UniversitySearchAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    university_id = universitiSearchPojo.getResponse().get(position).getId();
                                    search_universities.setText(universitiSearchPojo.getResponse().get(position).getTitle());
                                }
                            });
                            break;
                        case 2:
                            Common.showToast(SignupAct.this, jsonObject.optString("message"));
                            startActivity(new Intent(SignupAct.this, LoginAct.class));
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(SignupAct.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
