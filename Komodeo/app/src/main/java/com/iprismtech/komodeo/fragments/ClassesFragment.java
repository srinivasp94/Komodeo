package com.iprismtech.komodeo.fragments;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.fragments.AddClassesFragment;
import com.iprismtech.komodeo.fragments.CurrentFragment;

public class ClassesFragment extends BaseAbstractFragment {
    private TabLayout tabLayout;
    private CurrentFragment currentFragment;
    private AddClassesFragment addClassesFragment;


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
        currentFragment = new CurrentFragment();
        addClassesFragment = new AddClassesFragment();

        tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setVisibility(View.VISIBLE);
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
        tabLayout.addTab(tabLayout.newTab().setText("Current"), true);
        tabLayout.addTab(tabLayout.newTab().setText("+ Add Class"));
    }

    private void setCurrentTabFragment(int position) {
        switch (position) {
            case 0:
                replaceFragmets(currentFragment);
                break;
            case 1:
                replaceFragmets(addClassesFragment);
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
