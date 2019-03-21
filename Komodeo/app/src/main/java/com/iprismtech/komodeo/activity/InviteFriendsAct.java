package com.iprismtech.komodeo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.iprismtech.komodeo.R;

public class InviteFriendsAct extends AppCompatActivity {
    ImageView iv_invite_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invite_layout);

        iv_invite_back = findViewById(R.id.iv_invite_back);

        iv_invite_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
