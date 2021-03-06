package com.iprismtech.komodeo;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.activity.AddclassFormAct;
import com.iprismtech.komodeo.activity.ChatAct;
import com.iprismtech.komodeo.activity.FAQActivity;
import com.iprismtech.komodeo.activity.LoginAct;
import com.iprismtech.komodeo.activity.NotificationsActivity;
import com.iprismtech.komodeo.activity.PersonalChatActivity;
import com.iprismtech.komodeo.activity.ProfileActivity;
import com.iprismtech.komodeo.activity.SplashActivity;
import com.iprismtech.komodeo.activity.UploadCredentialsActivity;
import com.iprismtech.komodeo.fragments.ChatListFragment;
import com.iprismtech.komodeo.activity.SettingsAct;
import com.iprismtech.komodeo.activity.UserProfileActivity;
import com.iprismtech.komodeo.fragments.ClassesFragment;
import com.iprismtech.komodeo.activity.ContactusAct;
import com.iprismtech.komodeo.activity.EventsTabAct;
import com.iprismtech.komodeo.activity.FriendsAct;
import com.iprismtech.komodeo.activity.NotificationAct;
import com.iprismtech.komodeo.activity.CommunityDiscussionsActivity;
import com.iprismtech.komodeo.activity.TutoringAct;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.fragments.CurrentFragment;
import com.iprismtech.komodeo.utils.AlertUtils;
import com.iprismtech.komodeo.utils.Constants;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

public class MainActivity extends BaseAbstractActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ImageView iv_notification;
    private EditText searchview;

    private ImageView iv_logout, iv_share, profileimage;
    private boolean home_status = false;

    private boolean fragment_status = false;
    private boolean home_fragment_status = false;

    private LinearLayout ll_classes, ll_tutoring, ll_events, ll_chat;
    private TextView txt_classes, txt_tutoring, txt_events, txt_chat, txtprofile;
    private TextView txt_profile, txt_friends, txt_settings, txt_faqs, txt_terms, txt_contactus;
    private ImageView menu_icon;
    private DrawerLayout drawer_layout;

//    RelativeLayout rl_addclass;
//    LinearLayout ll_currentsubjects;

    private ImageView iv_add;
    private BottomNavigationView bottom_navigation;

    private ClassesFragment classesActivity;
    private TutoringAct tutoringAct;
    private EventsTabAct eventsTabAct;
    private ChatListFragment chatAct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

/*
        bottom_navigation = findViewById(R.id.bottom_navigation);

        bottom_navigation.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.action_class:
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.action_tutoring:
                        Intent intent1 = new Intent(MainActivity.this, TutoringAct.class);
                        startActivity(intent1);
                        break;


                    case R.id.action_events:
                        Intent intent2 = new Intent(MainActivity.this, EventsTabAct.class);
                        startActivity(intent2);
                        break;


                    case R.id.action_chat:

                        Intent intent3 = new Intent(MainActivity.this, ChatListAct.class);
                        startActivity(intent3);
                        break;


                }
            }
        });*/


    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_home, null);
        return view;
    }


    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_share.setOnClickListener(this);
        ll_classes.setOnClickListener(this);
        ll_tutoring.setOnClickListener(this);
        ll_events.setOnClickListener(this);
        ll_chat.setOnClickListener(this);
        txt_friends.setOnClickListener(this);
        txt_settings.setOnClickListener(this);
        txt_contactus.setOnClickListener(this);
        txt_profile.setOnClickListener(this);
        txt_terms.setOnClickListener(this);
        menu_icon.setOnClickListener(this);
        iv_add.setOnClickListener(this);
        searchview.setOnClickListener(this);
        txt_faqs.setOnClickListener(this);
        iv_logout.setOnClickListener(this);
        iv_notification.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        classesActivity = new ClassesFragment();
        tutoringAct = new TutoringAct();
        eventsTabAct = new EventsTabAct();
        chatAct = new ChatListFragment();


        profileimage = findViewById(R.id.profileimage);
        ll_classes = findViewById(R.id.ll_classes);
        txt_classes = findViewById(R.id.txt_classes);

        ll_tutoring = findViewById(R.id.ll_tutoring);
        txt_tutoring = findViewById(R.id.txt_tutoring);

        ll_events = findViewById(R.id.ll_events);
        txt_events = findViewById(R.id.txt_events);

        ll_chat = findViewById(R.id.ll_chat);
        txt_chat = findViewById(R.id.txt_chat);

        txtprofile = findViewById(R.id.txtprofile);
        SharedPrefsUtils prefsUtils = SharedPrefsUtils.getInstance(MainActivity.this);
        txtprofile.setText(SharedPrefsUtils.getString(SharedPrefsUtils.KEY_NAME));

        Picasso.with(this).load(Constants.BASE_IMAGE_URL + SharedPrefsUtils.getString(SharedPrefsUtils.KEY_PROFILE))
                .error(R.drawable.boy).into(profileimage);

        txt_friends = findViewById(R.id.txt_friends);
        txt_settings = findViewById(R.id.txt_settings);
        txt_faqs = findViewById(R.id.txt_faqs);
        txt_terms = findViewById(R.id.txt_terms);
        txt_contactus = findViewById(R.id.txt_contactus);

        searchview = (EditText) findViewById(R.id.searchview);

        iv_notification = findViewById(R.id.iv_notification);
        iv_add = findViewById(R.id.iv_add);
        txt_profile = findViewById(R.id.txt_profile);
        iv_share = findViewById(R.id.iv_share);

        iv_logout = findViewById(R.id.iv_logout);
        drawer_layout = findViewById(R.id.drawer_layout);

        menu_icon = findViewById(R.id.menu_icon);
        tabLayout = findViewById(R.id.tab_layout);

        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.framelayout_Main, new ClassesFragment(), "").commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_terms:

//                startActivity(new Intent(MainActivity.this,UploadCredentialsActivity.class));
                break;
            case R.id.ll_classes:
                txt_classes.setTextColor(Color.parseColor("#000000"));
                txt_tutoring.setTextColor(Color.parseColor("#7b7979"));
                txt_events.setTextColor(Color.parseColor("#7b7979"));
                txt_chat.setTextColor(Color.parseColor("#7b7979"));

                ll_classes.setBackgroundColor(Color.parseColor("#a3b5ff"));
                ll_tutoring.setBackgroundColor(Color.parseColor("#ffffff"));
                ll_events.setBackgroundColor(Color.parseColor("#ffffff"));
                ll_chat.setBackgroundColor(Color.parseColor("#ffffff"));

                replaceFragmets(classesActivity);
                break;
            case R.id.ll_tutoring:
                fragment_status = false;
                home_fragment_status = false;
                txt_classes.setTextColor(Color.parseColor("#7b7979"));
                txt_tutoring.setTextColor(Color.parseColor("#000000"));
                txt_events.setTextColor(Color.parseColor("#7b7979"));
                txt_chat.setTextColor(Color.parseColor("#7b7979"));

                ll_classes.setBackgroundColor(Color.parseColor("#ffffff"));
                ll_tutoring.setBackgroundColor(Color.parseColor("#a3b5ff"));
                ll_events.setBackgroundColor(Color.parseColor("#ffffff"));
                ll_chat.setBackgroundColor(Color.parseColor("#ffffff"));
                replaceFragmets(tutoringAct);
                break;
            case R.id.ll_events:
                fragment_status = false;
                home_fragment_status = false;
                txt_classes.setTextColor(Color.parseColor("#7b7979"));
                txt_tutoring.setTextColor(Color.parseColor("#7b7979"));
                txt_events.setTextColor(Color.parseColor("#000000"));
                txt_chat.setTextColor(Color.parseColor("#7b7979"));

                ll_classes.setBackgroundColor(Color.parseColor("#ffffff"));
                ll_tutoring.setBackgroundColor(Color.parseColor("#ffffff"));
                ll_events.setBackgroundColor(Color.parseColor("#a3b5ff"));
                ll_chat.setBackgroundColor(Color.parseColor("#ffffff"));
                replaceFragmets(eventsTabAct);
                break;
            case R.id.ll_chat:
                fragment_status = false;
                home_fragment_status = false;
                txt_classes.setTextColor(Color.parseColor("#7b7979"));
                txt_tutoring.setTextColor(Color.parseColor("#7b7979"));
                txt_events.setTextColor(Color.parseColor("#7b7979"));
                txt_chat.setTextColor(Color.parseColor("#000000"));

                ll_classes.setBackgroundColor(Color.parseColor("#ffffff"));
                ll_tutoring.setBackgroundColor(Color.parseColor("#ffffff"));
                ll_events.setBackgroundColor(Color.parseColor("#ffffff"));
                ll_chat.setBackgroundColor(Color.parseColor("#a3b5ff"));
//                replaceFragmets(chatAct);
                replaceFragmets(chatAct);
                //  startActivity(new Intent(MainActivity.this, chatAct.class));
                break;
            case R.id.txt_friends:
                Intent frnds = new Intent(MainActivity.this, FriendsAct.class);
                startActivity(frnds);
                finish();
                break;
            case R.id.txt_settings:
                Intent settings = new Intent(MainActivity.this, SettingsAct.class);
                startActivity(settings);
                finish();
                break;


            case R.id.txt_contactus:
                Intent contatusIntent = new Intent(MainActivity.this, ContactusAct.class);
                startActivity(contatusIntent);
                finish();
                break;


            case R.id.txt_profile:
                Intent profileintent = new Intent(MainActivity.this, UserProfileActivity.class);
                startActivity(profileintent);
                finish();
                break;


            case R.id.menu_icon:
                if (drawer_layout.isDrawerOpen(Gravity.START)) {
                    drawer_layout.closeDrawer(Gravity.START);
                } else {
                    drawer_layout.openDrawer(Gravity.START);


                }
                break;

            case R.id.searchview:
                Intent Searchintent = new Intent(MainActivity.this, CommunityDiscussionsActivity.class);
                startActivity(Searchintent);
                finish();
                break;


            case R.id.iv_notification:
                Intent Notifyintent = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(Notifyintent);
                finish();
                break;


            case R.id.iv_add:
                Intent addintent = new Intent(MainActivity.this, AddclassFormAct.class);
                startActivity(addintent);
                finish();
                break;
            case R.id.iv_share:
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "Komodeo");
//                intent.putExtra(Intent.EXTRA_TEXT, "Select one");
//                startActivity(Intent.createChooser(intent, "choose one"));

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "http://play.google.com/store/apps/details?id=" + getPackageName();
                intent.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(intent, "choose one"));

                break;
            case R.id.txt_faqs:
                startActivity(new Intent(this, FAQActivity.class));
                finish();
                break;
            case R.id.iv_logout:
                AlertUtils.showSimpleAlert(this, "Are sure you want to Logout .?",
                        "Logout", "Logout", "cancel", new AlertUtils.onClicklistners() {
                            @Override
                            public void onpositiveclick() {
                                SharedPrefsUtils.getInstance(MainActivity.this);
                                SharedPrefsUtils.logoutUser();
                                startActivity(new Intent(MainActivity.this, SplashActivity.class));
                                finish();
                            }

                            @Override
                            public void onNegativeClick() {

                            }
                        });

                break;
        }
    }


    private void replaceFragmets(Fragment Fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.framelayout_Main, Fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        if (fragment_status == false && home_fragment_status == false) {
            fragmentManager.beginTransaction().replace(R.id.framelayout_Main, new CurrentFragment(), "").commit();
            txt_classes.setTextColor(Color.parseColor("#000000"));
            txt_tutoring.setTextColor(Color.parseColor("#7b7979"));
            txt_events.setTextColor(Color.parseColor("#7b7979"));
            txt_chat.setTextColor(Color.parseColor("#7b7979"));

            ll_classes.setBackgroundColor(Color.parseColor("#a3b5ff"));
            ll_tutoring.setBackgroundColor(Color.parseColor("#ffffff"));
            ll_events.setBackgroundColor(Color.parseColor("#ffffff"));
            ll_chat.setBackgroundColor(Color.parseColor("#ffffff"));

            home_fragment_status = true;
        } else {
            AlertUtils.showSimpleAlert(MainActivity.this, "Are you sure want to exit?", "Confirm", "Yes", "No", new AlertUtils.onClicklistners() {
                @Override
                public void onpositiveclick() {
                    //startActivity(new Intent(MainActivity.this, LoginAct.class));
                    finish();


                }

                @Override
                public void onNegativeClick() {

                }
            });

        }
    }

}
