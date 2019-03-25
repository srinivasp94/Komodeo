package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

public class SplashActivity extends BaseAbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_splash, null);
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefsUtils.getInstance(SplashActivity.this).isUserLoggedIn() == true) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginAct.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 3000);
    }
}
