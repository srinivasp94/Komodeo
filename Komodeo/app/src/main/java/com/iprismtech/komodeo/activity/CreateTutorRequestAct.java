package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.MapDialog;
import com.iprismtech.komodeo.R;

public class CreateTutorRequestAct extends AppCompatActivity {

    TextView txt_datentime, txt_reoccuring_settings, txt_location;
    LinearLayout ll_addfriends;
    ImageView iv_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_tutor_event_request_layout);

        txt_datentime = findViewById(R.id.txt_datentime);


        iv_back = findViewById(R.id.iv_back);


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txt_datentime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateTutorRequestAct.this, EventsTabAct.class);
                startActivity(intent);
            }
        });

        ll_addfriends = findViewById(R.id.ll_addfriends);


        txt_reoccuring_settings = findViewById(R.id.txt_reoccuring_settings);


        txt_reoccuring_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateTutorRequestAct.this, ReoccuringSettingsAct.class);
                startActivity(intent);
            }
        });

        txt_location = findViewById(R.id.txt_location);


        txt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapDialog ratingDialog = new MapDialog();
                FragmentManager fragmentManager = getSupportFragmentManager();
                ratingDialog.show(fragmentManager, "Dialog fragment");
            }
        });


        ll_addfriends = findViewById(R.id.ll_addfriends);


        ll_addfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateTutorRequestAct.this, InviteFriendsAct.class);
                startActivity(intent);
            }
        });


    }
}
