/*
package com.iprismtech.komodeo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomeFrag extends Fragment {


    private TabLayout tabLayout;

    RelativeLayout rlnosearchresults;


    FrameLayout homemaincontainerlayout;

    ImageView iv_notification;


    EditText searchview;

    ImageView iv_logout, iv_share;

    LinearLayout ll_addclasstab, ll_currenttab;

    TextView txt_profile, txt_friends, txt_settings, txt_faqs, txt_terms, txt_contactus;

    ImageView menu_icon;

    DrawerLayout drawer_layout;

    RelativeLayout rl_addclass;

    LinearLayout ll_currentsubjects;

    ImageView iv_add;

    BottomNavigationView bottom_navigation;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_frag, null);

        ll_addclasstab = view.findViewById(R.id.ll_addclasstab);

        ll_currenttab = view.findViewById(R.id.ll_currenttab);


        ll_currentsubjects = view.findViewById(R.id.ll_currentsubjects);

       */
/* ll_currentsubjects.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {


            }
        });*//*


        ll_currenttab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ll_currenttab.setBackgroundColor(getResources().getColor(R.color.tab_selected_color, null));
                ll_addclasstab.setBackgroundColor(getResources().getColor(R.color.tab_unselected_color, null));


            }
        });

        ll_addclasstab.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                ll_currenttab.setBackgroundColor(getResources().getColor(R.color.tab_unselected_color, null));
                ll_addclasstab.setBackgroundColor(getResources().getColor(R.color.tab_selected_color, null));
                AddclassFormAct fragment2 = new AddclassFormAct();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout, fragment2);
                fragmentTransaction.commit();


            }
        });

        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
*/
