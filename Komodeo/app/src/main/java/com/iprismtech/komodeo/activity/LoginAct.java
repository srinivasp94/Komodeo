package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.gson.Gson;
import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.request.LoginReq;
import com.iprismtech.komodeo.request.SocialLoginReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.LogUtils;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.Arrays;

public class LoginAct extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private TextView txt_signup, txt_signin, txt_forgotpass;
    private EditText edtuserName, edtPassword;
    private ImageView iv_faceBook, iv_google;
    private Object obj;
    SharedPrefsUtils utils;
    private CallbackManager callbackManager;

//    private GoogleApiClient mGoogleApiClient;

    private static final int RC_SIGN_IN = 007;

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
        iv_faceBook.setOnClickListener(this);
        txt_forgotpass.setOnClickListener(this);
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
        iv_google = findViewById(R.id.iv_google);
        iv_faceBook = findViewById(R.id.iv_faceBook);


/*        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestScopes(new Scope(Scopes.PLUS_ME))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(LoginAct.this)
                .enableAutoManage(LoginAct.this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d("onConnectionFailed", "onConnectionFailed:" + connectionResult);
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();*/

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                if (Profile.getCurrentProfile() != null) {
                    LogUtils.error("Name", Profile.getCurrentProfile().getFirstName());
                    callSocilaLoginWS();
                }
            }

            @Override
            public void onCancel() {
                LogUtils.error("onCancel", "onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                LogUtils.error("FacebookException", "FacebookException");
            }
        });

    }

    private void callSocilaLoginWS() {
        SocialLoginReq req = new SocialLoginReq();
        req.firstName = Profile.getCurrentProfile().getFirstName();
        req.lastName = Profile.getCurrentProfile().getLastName();
        req.emailId = Profile.getCurrentProfile().getName();
        req.universityId = "1";

        req.registeredThrough = "facebook";

        try {
            obj = Class.forName(SocialLoginReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 3, "social_login", true);
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
            case R.id.iv_faceBook:
                LoginManager.getInstance().logInWithReadPermissions(LoginAct.this, Arrays.asList("public_profile"));
                break;
            case R.id.iv_google:
//                signIn();
                break;
            case R.id.txt_forgotpass:
                startActivity(new Intent(LoginAct.this, ForgotPasswordActivity.class));
        }


    }

/*    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
          /*  GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);*/
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);

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
