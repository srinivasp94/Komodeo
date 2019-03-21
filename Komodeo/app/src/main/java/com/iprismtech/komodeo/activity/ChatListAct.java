package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;

public class ChatListAct extends AppCompatActivity {

    BottomNavigationView bottom_navigation;

    RelativeLayout cindy11, cindy2, cindy3, cindy4;
    ImageView iv_plus11;
    LinearLayout ll_classes3, ll_tutoring3, ll_eveents3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlist_layout);


        ll_classes3 = findViewById(R.id.ll_classes3);
        ll_tutoring3 = findViewById(R.id.ll_tutoring3);
        ll_eveents3 = findViewById(R.id.ll_events3);

        ll_classes3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListAct.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(intent);
            }
        });

        ll_tutoring3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListAct.this, TutoringAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);

                startActivity(intent);

            }
        });

        ll_eveents3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListAct.this, EventsTabAct.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });


        iv_plus11 = findViewById(R.id.iv_plus11);

        iv_plus11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListAct.this, CreateChatAct.class);
                startActivity(intent);
            }
        });

        cindy11 = findViewById(R.id.cindy11);

        cindy2 = findViewById(R.id.cindy2);
        cindy3 = findViewById(R.id.cindy3);
        cindy4 = findViewById(R.id.cindy4);


        cindy11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListAct.this, ChatAct.class);
                startActivity(intent);
            }
        });


        cindy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListAct.this, ChatAct.class);
                startActivity(intent);
            }
        });

        cindy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListAct.this, ChatAct.class);
                startActivity(intent);
            }
        });

        cindy4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatListAct.this, ChatAct.class);
                startActivity(intent);
            }
        });



       /* bottom_navigation = findViewById(R.id.bottom_navigation);


        bottom_navigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.action_class:
                        Intent intent = new Intent(ChatListAct.this, MainActivity.class);
                        startActivity(intent);
                        break;

                    case R.id.action_tutoring:
                        Intent intent1 = new Intent(ChatListAct.this, TutoringAct.class);
                        startActivity(intent1);
                        break;


                    case R.id.action_events:
                        Intent intent2 = new Intent(ChatListAct.this, EventsTabAct.class);
                        startActivity(intent2);
                        break;


                    case R.id.action_chat:
                        Intent intent3 = new Intent(ChatListAct.this, ChatListAct.class);
                        startActivity(intent3);
                        break;

                }
            }
        });*/

    }
}
