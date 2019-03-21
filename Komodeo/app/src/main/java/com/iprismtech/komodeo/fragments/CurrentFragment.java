package com.iprismtech.komodeo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.activity.CommunityDiscussionsActivity;
import com.iprismtech.komodeo.adapters.CurrentGridAdapter;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.pojo.UserClassesPojo;
import com.iprismtech.komodeo.request.UserClassesReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

public class CurrentFragment extends BaseAbstractFragment implements RetrofitResponseListener {

    private GridView gv_current;
    private CurrentGridAdapter gridAdapter;
    private UserClassesPojo userClassesPojo;
    private Object obj;

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.current_fragment_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        gv_current = view.findViewById(R.id.gv_current);
        UserClassesReq userClassesReq = new UserClassesReq();
        userClassesReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        userClassesReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(UserClassesReq.class.getName()).cast(userClassesReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_classes", false);


    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getActivity(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);

                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            userClassesPojo = gson.fromJson(jsonString, UserClassesPojo.class);
                            gridAdapter = new CurrentGridAdapter(getActivity(), userClassesPojo);
                            gv_current.setAdapter(gridAdapter);
                            gv_current.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    Intent intent = new Intent(getActivity(), CommunityDiscussionsActivity.class);
                                    intent.putExtra("Key_course_name", userClassesPojo.getResponse().get(position).getTitle());
                                    intent.putExtra("Key_course_ID", userClassesPojo.getResponse().get(position).getClass_id());
                                    startActivity(intent);
                                }
                            });
                            break;
                    }
                } else {
                    Common.showToast(getActivity(), object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
