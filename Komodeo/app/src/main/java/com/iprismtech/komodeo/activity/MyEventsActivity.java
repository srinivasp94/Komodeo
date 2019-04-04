package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.fragments.AddClassesFragment;
import com.iprismtech.komodeo.fragments.CurrentFragment;
import com.iprismtech.komodeo.fragments.MyEventsFragement;
import com.iprismtech.komodeo.fragments.PendingApprovalFragment;

public class MyEventsActivity extends BaseAbstractActivity {

    private TabLayout tabLayout;
    private MyEventsFragement myEventsFragement;
    private PendingApprovalFragment pendingApprovalFragment;
    private RelativeLayout rl_rootEvents;
    private ImageView iv_invite_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_classes, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyEventsActivity.this, UserProfileActivity.class));
        finish();

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_invite_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        myEventsFragement = new MyEventsFragement();
        pendingApprovalFragment = new PendingApprovalFragment();

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.VISIBLE);

        iv_invite_back = findViewById(R.id.iv_invite_back);

        rl_rootEvents = findViewById(R.id.rl_rootEvents);
        rl_rootEvents.setVisibility(View.VISIBLE);

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.addTab(tabLayout.newTab().setText("My Events"), true);
        tabLayout.addTab(tabLayout.newTab().setText("Pending Approvals"));
    }

    private void setCurrentTabFragment(int position) {
        switch (position) {
            case 0:
                replaceFragmets(myEventsFragement);
                break;
            case 1:
                replaceFragmets(pendingApprovalFragment);
                break;
        }
    }

    private void replaceFragmets(Fragment Fragment) {
        try {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fm_classes, Fragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
