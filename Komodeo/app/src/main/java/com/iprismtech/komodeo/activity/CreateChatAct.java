package com.iprismtech.komodeo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.iprismtech.komodeo.R;

public class CreateChatAct extends AppCompatActivity {
    ImageView menu_icon;
    DrawerLayout drawer_layout;
    ImageView iv_chatback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_chat_layout);

        drawer_layout = findViewById(R.id.drawer_layout);

        iv_chatback = findViewById(R.id.iv_chatback);

        iv_chatback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        menu_icon = findViewById(R.id.menu_icon);

        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawer_layout.isDrawerOpen(Gravity.START)) {
                    drawer_layout.closeDrawer(Gravity.START);
                } else {
                    drawer_layout.openDrawer(Gravity.START);


                }

            }

        });

    }

}
