package com.iprismtech.komodeo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;

public class ResetPasswordActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    @Override
    public void onClick(View v) {

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

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
