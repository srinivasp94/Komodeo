package com.iprismtech.komodeo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.EventsAdapter;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.request.EventsReq;
import com.iprismtech.komodeo.responses.EventComment;
import com.iprismtech.komodeo.responses.EventsList;
import com.iprismtech.komodeo.responses.EventsRes;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MyEventsFragement extends BaseAbstractFragment implements RetrofitResponseListener {

    private RecyclerView rv_events;
    LinearLayoutManager manager;
    private ArrayList<EventsList> meventsLists = new ArrayList<>();
    private EventsAdapter eventsAdapter;
    private Object obj;

    @Override
    protected View getFragmentView() {
        View view = getLayoutInflater().inflate(R.layout.events_fragment, null);
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
        rv_events = view.findViewById(R.id.rv_events);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_events.setLayoutManager(manager);

        EventsReq eventsReq = new EventsReq();
        eventsReq.userId = SharedPrefsUtils.getInstance(getActivity()).getId();
        eventsReq.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);

        eventsReq.eventType = "tutor";


        eventsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {
            obj = Class.forName(EventsReq.class.getName()).cast(eventsReq);

        } catch (Exception e) {
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "my_events", true);

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(getActivity(), "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            EventsRes res = Common.getSpecificDataObject(objectResponse, EventsRes.class);
                            meventsLists = (ArrayList<EventsList>) res.response;
                            if (meventsLists != null && meventsLists.size() > 0) {
                                eventsAdapter = new EventsAdapter(getActivity(), meventsLists);
                                rv_events.setAdapter(eventsAdapter);

                                eventsAdapter.setOnItemClickListener(new EventsAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {

                                    }
                                });
                            }
                            break;
                    }
                } else {
                    Common.showToast(getActivity(), jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
