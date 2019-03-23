package com.iprismtech.komodeo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.SubjectsRecyclerAdapter;
import com.iprismtech.komodeo.TutoringTabFilterAct;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.fragments.StudyEventFragment;

public class EventsTabAct extends BaseAbstractFragment {


    private TabLayout tabLayout;
    private StudyEventFragment eventFragment;

    @Override
    protected View getFragmentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_classes, null);
        return view;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }


    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        eventFragment = new StudyEventFragment();

        tabLayout = view.findViewById(R.id.tabLayout);
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
        tabLayout.addTab(tabLayout.newTab().setText("My Events "), true);
//        tabLayout.addTab(tabLayout.newTab().setText("Calender"));
        tabLayout.addTab(tabLayout.newTab().setText(" + "));
    }

    private void setCurrentTabFragment(int position) {
        switch (position) {
            case 0:
                replaceFragmets(eventFragment);
                break;
            case 1:
//                replaceFragmets(addClassesFragment);
                break;
        }
    }

    private void replaceFragmets(Fragment Fragment) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fm_classes, Fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

    }
}
