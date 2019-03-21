package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.iprismtech.komodeo.R;

public class ReoccuringSettingsAct extends AppCompatActivity {
    ImageView iv_closereoccuring;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reoccuring_settings_layout);

        iv_closereoccuring = findViewById(R.id.iv_closereoccuring);

        iv_closereoccuring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ReoccuringSettingsAct.this, TutoringAct.class);
                startActivity(intent1);
            }
        });

    }
}
