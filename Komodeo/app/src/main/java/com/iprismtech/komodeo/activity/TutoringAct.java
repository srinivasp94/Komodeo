package com.iprismtech.komodeo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.fragments.TutorCreateSessionFragment;

public class TutoringAct extends BaseAbstractFragment {

    private TabLayout tabLayout;
    private TutorCreateSessionFragment sessionFragment;

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

        sessionFragment = new TutorCreateSessionFragment();
        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
//                setCurrentTabFragment(0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.addTab(tabLayout.newTab().setText("+ Create Session"), true);
//        tabLayout.addTab(tabLayout.newTab().setText("View Session"));
    }

    private void setCurrentTabFragment(int position) {
        switch (position) {
            case 0:
                replaceFragmets(sessionFragment);
                break;
            case 1:
//                replaceFragmets(addClassesFragment);
                break;
        }
    }

    private void replaceFragmets(Fragment mFragment) {
        try {
            FragmentManager manager = getChildFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fm_classes, mFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
