package com.iprismtech.komodeo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.iprismtech.komodeo.R;

public class SettingsAct extends AppCompatActivity {
    ImageView iv_backarrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.li_accountsettings);
        iv_backarrow = findViewById(R.id.iv_backarrow);
        iv_backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
