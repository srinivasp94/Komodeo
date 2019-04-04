package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;

public class FAQActivity extends BaseAbstractActivity implements View.OnClickListener {
    LinearLayout llMiscel, llEvents, llStudy, llTutor, llGeneral;
    TextView txtMiscel, txtEvents, txtStudy, TxtTutor, txtGeneral;
    private ImageView iv_invite_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_faq);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_faq, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtMiscel.setOnClickListener(this);
        txtEvents.setOnClickListener(this);
        txtStudy.setOnClickListener(this);
        TxtTutor.setOnClickListener(this);
        txtGeneral.setOnClickListener(this);
        iv_invite_back.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        txtMiscel = findViewById(R.id.txtMiscel);
        txtEvents = findViewById(R.id.txtEvents);
        txtStudy = findViewById(R.id.txtStudy);
        TxtTutor = findViewById(R.id.TxtTutor);
        txtGeneral = findViewById(R.id.txtGeneral);

        iv_invite_back = findViewById(R.id.iv_invite_back);

        llMiscel = findViewById(R.id.llMiscel);
        llEvents = findViewById(R.id.llEvents);
        llStudy = findViewById(R.id.llStudy);
        llTutor = findViewById(R.id.llTutor);
        llGeneral = findViewById(R.id.llGeneral);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FAQActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_invite_back:
                onBackPressed();
                break;
            case R.id.txtMiscel:
                if (llMiscel.getVisibility() == View.GONE)
                    llMiscel.setVisibility(View.VISIBLE);
                else
                    llMiscel.setVisibility(View.GONE);
                break;
            case R.id.txtEvents:
                if (llEvents.getVisibility() == View.GONE)
                    llEvents.setVisibility(View.VISIBLE);
                else
                    llEvents.setVisibility(View.GONE);
                break;
            case R.id.txtStudy:
                if (llStudy.getVisibility() == View.GONE)
                    llStudy.setVisibility(View.VISIBLE);
                else
                    llStudy.setVisibility(View.GONE);
                break;
            case R.id.TxtTutor:
                if (llTutor.getVisibility() == View.GONE)
                    llTutor.setVisibility(View.VISIBLE);
                else
                    llTutor.setVisibility(View.GONE);
                break;
            case R.id.txtGeneral:
                if (llGeneral.getVisibility() == View.GONE)
                    llGeneral.setVisibility(View.VISIBLE);
                else
                    llGeneral.setVisibility(View.GONE);
                break;
        }
    }
}
