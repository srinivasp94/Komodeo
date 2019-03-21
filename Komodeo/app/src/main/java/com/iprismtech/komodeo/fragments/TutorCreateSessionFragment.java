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
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.activity.TutorEventAct;
import com.iprismtech.komodeo.adapters.ClassHorizontalAdapter;
import com.iprismtech.komodeo.adapters.TutorAdapter;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.request.EventsReq;
import com.iprismtech.komodeo.request.SimpleReq;
import com.iprismtech.komodeo.responses.ClassList;
import com.iprismtech.komodeo.responses.ClassResponse;
import com.iprismtech.komodeo.responses.EventsList;
import com.iprismtech.komodeo.responses.EventsRes;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TutorCreateSessionFragment extends BaseAbstractFragment implements RetrofitResponseListener, View.OnClickListener {

    private TutorAdapter tutorAdapter;
    private ClassHorizontalAdapter mHorizontalAdapter;
    private RecyclerView rcv_Classes, rv_tutors;
    private ArrayList<ClassList> classLists = new ArrayList<>();
    private ArrayList<EventsList> mTutorList;
    private Object obj;
    private EventsReq eventsReq;
    private LinearLayout ll_time, ll_rating, ll_price;

    @Override
    protected View getFragmentView() {
        View view = getLayoutInflater().inflate(R.layout.ratingtab_of_tutoringlayout, null);
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
        ll_time.setOnClickListener(this);
        ll_rating.setOnClickListener(this);
        ll_price.setOnClickListener(this);
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        rcv_Classes = view.findViewById(R.id.rcv_Classes);
        rv_tutors = view.findViewById(R.id.rv_tutors);

        ll_time = view.findViewById(R.id.ll_time);
        ll_rating = view.findViewById(R.id.ll_rating);
        ll_price = view.findViewById(R.id.ll_price);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rcv_Classes.setLayoutManager(manager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_tutors.setLayoutManager(layoutManager);

        SimpleReq req = new SimpleReq();
        req.userId = SharedPrefsUtils.getInstance(getActivity()).getId();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {
            obj = Class.forName(SimpleReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_classes", true);

//        new RetrofitRequester(this).callPostServices(obj, 2, "get_events", true);

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
                            ClassResponse response = Common.getSpecificDataObject(objectResponse, ClassResponse.class);
                            classLists = (ArrayList<ClassList>) response.response;
                            if (classLists != null && classLists.size() > 0) {
                                mHorizontalAdapter = new ClassHorizontalAdapter(getActivity(), classLists);
                                rcv_Classes.setAdapter(mHorizontalAdapter);
                                callWSforTutors(0);
                                mHorizontalAdapter.setOnItemClickListener(new ClassHorizontalAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        callWSforTutors(position);
                                    }
                                });
                            }
                            break;
                        case 2:
                            tutorAdapter = null;
                            mTutorList = new ArrayList<>();
                            EventsRes res = Common.getSpecificDataObject(objectResponse, EventsRes.class);
                            mTutorList = (ArrayList<EventsList>) res.response;
                            if (mTutorList != null && mTutorList.size() > 0) {
                                tutorAdapter = new TutorAdapter(getActivity(), mTutorList);
                                rv_tutors.setAdapter(tutorAdapter);
                                rv_tutors.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                    @Override
                                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                        super.onScrolled(recyclerView, dx, dy);
                                    }
                                });
                                tutorAdapter.setOnItemClickListener(new TutorAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        Intent intent = new Intent(getActivity(), TutorEventAct.class);
                                        intent.putExtra("Key_eventId", mTutorList.get(position).id);
                                        startActivity(intent);
                                    }
                                });
                            }

                            break;
                    }
                } else {
                    tutorAdapter = null;
                    tutorAdapter.notifyDataSetChanged();
                    Common.showToast(getActivity(), jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void callWSforTutors(int i) {
        eventsReq = new EventsReq();
        eventsReq.userId = SharedPrefsUtils.getInstance(getActivity()).getId();
        eventsReq.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
        eventsReq.classId = classLists.get(i).classId;
        eventsReq.eventType = "tutor";
        eventsReq.section = "time";
        eventsReq.count = "0";
        eventsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {
            obj = Class.forName(EventsReq.class.getName()).cast(eventsReq);

        } catch (Exception e) {
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "get_events", true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_time:
                break;
            case R.id.ll_rating:
                Collections.sort(mTutorList, new Comparator<EventsList>() {
                    @Override
                    public int compare(EventsList o1, EventsList o2) {
                        return o1.ratings.compareTo(o2.ratings);
                    }
                });
                tutorAdapter.notifyDataSetChanged();
                break;
            case R.id.ll_price:
                break;
        }
    }
}
