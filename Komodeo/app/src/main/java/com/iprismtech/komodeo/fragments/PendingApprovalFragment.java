package com.iprismtech.komodeo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.EventsAdapter;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.request.AcceptEventRequsetReq;
import com.iprismtech.komodeo.request.EventsReq;
import com.iprismtech.komodeo.responses.EventsList;
import com.iprismtech.komodeo.responses.EventsRes;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.AlertUtils;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class PendingApprovalFragment extends BaseAbstractFragment implements RetrofitResponseListener {

    private RecyclerView rv_events;
    LinearLayoutManager manager;
    private ArrayList<EventsList> meventsLists = new ArrayList<>();
    private EventsAdapter eventsAdapter;
    private Object obj;
    private int lastpos;
    private RadioGroup rg_eventType;
    private String result_event_type = "";
    private String str_result_event;

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
        rg_eventType = view.findViewById(R.id.rg_eventType);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_events.setLayoutManager(manager);


        rg_eventType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    // tv.setText("Checked:" + checkedRadioButton.getText());
                    result_event_type = checkedRadioButton.getText().toString();
                    if (result_event_type.equalsIgnoreCase("Tutor Event")) {
                        str_result_event = "tutor";

                    } else {
                        str_result_event = "study";
                    }
                    calleventsWS(str_result_event);


                }

            }
        });

        EventsReq eventsReq = new EventsReq();
        eventsReq.userId = SharedPrefsUtils.getInstance(getActivity()).getId();
        eventsReq.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);

        eventsReq.eventType = "study";


        eventsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {
            obj = Class.forName(EventsReq.class.getName()).cast(eventsReq);

        } catch (Exception e) {
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "my_pending_approvals", true);

    }

    private void calleventsWS(String str_result_event) {
        rv_events.setVisibility(View.GONE);
        EventsReq eventsReq = new EventsReq();
        eventsReq.userId = SharedPrefsUtils.getInstance(getActivity()).getId();
        eventsReq.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);

        eventsReq.eventType = str_result_event;


        eventsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {
            obj = Class.forName(EventsReq.class.getName()).cast(eventsReq);

        } catch (Exception e) {
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "my_pending_approvals", true);

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
                                rv_events.setVisibility(View.VISIBLE);
                                eventsAdapter = new EventsAdapter(getActivity(), meventsLists);
                                rv_events.setAdapter(eventsAdapter);

                                eventsAdapter.setOnItemClickListener(new EventsAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, final int position) {
                                        AlertUtils.showSimpleAlert(getActivity(), "Accept Event Request.", "Alert"
                                                , "Accept", "Cancel", new AlertUtils.onClicklistners() {
                                                    @Override
                                                    public void onpositiveclick() {
                                                        approveEventsWS(position);
                                                    }

                                                    @Override
                                                    public void onNegativeClick() {

                                                    }
                                                });

                                    }
                                });
                            }
                            break;
                        case 2:
                            meventsLists.remove(lastpos);
                            eventsAdapter.notifyDataSetChanged();
                            Common.showToast(getActivity(), jsonObject.optString("message"));

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

    private void approveEventsWS(int position) {
        lastpos = position;
        AcceptEventRequsetReq req = new AcceptEventRequsetReq();
        SharedPrefsUtils prefsUtils = SharedPrefsUtils.getInstance(getActivity());
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        req.eventMemberId = meventsLists.get(position).event_member_id;
        req.eventId = meventsLists.get(position).id;
        try {
            obj = Class.forName(AcceptEventRequsetReq.class.getName()).cast(req);

        } catch (Exception e) {
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "accept_event_request", true);


    }
}
