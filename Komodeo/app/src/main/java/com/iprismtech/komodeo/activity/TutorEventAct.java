package com.iprismtech.komodeo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.EventCommentAdapter;
import com.iprismtech.komodeo.adapters.EventuserHorizonAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.request.EventSingelReq;
import com.iprismtech.komodeo.responses.EventComment;
import com.iprismtech.komodeo.responses.EventMember;
import com.iprismtech.komodeo.responses.TutorSingelRes;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class TutorEventAct extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {

    private ImageView iv_te_back;
    private TextView txt_Date, txt_tutoringwith, txtinTime, txtInvitedBy,
            txt_estimation, txtPeopleLeft, txtPeopleGoing, txt_address, txt_desc;
    private EditText edtMessage;
    LinearLayout ll_book, ll_share, ll_SendMsg;
    private EventuserHorizonAdapter mUserHorizonAdapter;
    private EventCommentAdapter commentAdapter;
    private ArrayList<EventMember> membersList = new ArrayList<>();
    private RecyclerView rv_eventUsers;
    private RecyclerView rv_commnet_tutor;
    private ArrayList<EventMember> memberArrayList = new ArrayList<>();
    private ArrayList<EventComment> commentsArrayList = new ArrayList<>();
    private String eventId;
    private Object obj;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.tutor_event_layout);

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.tutor_event_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_book.setOnClickListener(this);
        ll_share.setOnClickListener(this);
        ll_SendMsg.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        eventId = getIntent().getExtras().getString("Key_eventId", "");
        iv_te_back = findViewById(R.id.iv_te_back);
        rv_eventUsers = findViewById(R.id.rv_eventUsers);
        rv_commnet_tutor = findViewById(R.id.rv_commnet_tutor);
        txt_Date = findViewById(R.id.txt_Date);
        txt_tutoringwith = findViewById(R.id.txt_tutoringwith);
        txtinTime = findViewById(R.id.txtinTime);
        txtInvitedBy = findViewById(R.id.txtInvitedBy);
        txt_estimation = findViewById(R.id.txt_estimation);
        txtPeopleLeft = findViewById(R.id.txtPeopleLeft);
        txtPeopleGoing = findViewById(R.id.txtPeopleGoing);
        txt_address = findViewById(R.id.txt_address);
        txt_desc = findViewById(R.id.txt_desc);

        edtMessage = findViewById(R.id.edtMessage);

        ll_book = findViewById(R.id.ll_book);
        ll_share = findViewById(R.id.ll_share);
        ll_SendMsg = findViewById(R.id.ll_SendMsg);


        LinearLayoutManager manager = new LinearLayoutManager(TutorEventAct.this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_eventUsers.setLayoutManager(manager);

        LinearLayoutManager managerComment = new LinearLayoutManager(TutorEventAct.this);
        managerComment.setOrientation(LinearLayoutManager.VERTICAL);
        rv_commnet_tutor.setLayoutManager(managerComment);

        EventSingelReq req = new EventSingelReq();
        req.eventId = eventId;
        req.userId = SharedPrefsUtils.getInstance(TutorEventAct.this).getId();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {
            obj = Class.forName(EventSingelReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "event_details", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
//        TutorSingelRes
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(TutorEventAct.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            TutorSingelRes res = Common.getSpecificDataObject(objectResponse, TutorSingelRes.class);
                            memberArrayList = (ArrayList<EventMember>) res.response.eventMembers;
                            if (memberArrayList != null && memberArrayList.size() > 0) {
                                mUserHorizonAdapter = new EventuserHorizonAdapter(TutorEventAct.this, memberArrayList);
                                rv_eventUsers.setAdapter(mUserHorizonAdapter);
                            }
                            commentsArrayList = (ArrayList<EventComment>) res.response.eventComments;
                            if (commentsArrayList != null && commentsArrayList.size() > 0) {
                                commentAdapter = new EventCommentAdapter(TutorEventAct.this, commentsArrayList);
                                rv_commnet_tutor.setAdapter(commentAdapter);
                            }


                            txt_Date.setText(res.response.date);
                            txt_tutoringwith.setText(res.response.eventName + " Created By " + res.response.eventCreatedBy);
                            txtinTime.setText(res.response.eventDate + " " + res.response.startTime + "" + res.response.endTime);
                            txtInvitedBy.setText("Invited By " + res.response.firstName + " " + res.response.lastName);
                            txt_estimation.setText(" $ " + res.response.totalPrice);
                            txtPeopleLeft.setText("Max Group Size " + res.response.maxGroupSize);
                            txtPeopleGoing.setText(res.response.peopleInvited + " Going ..");
                            txt_address.setText(res.response.locationAddress);
                            txt_desc.setText(res.response.note);

                            break;
                    }
                } else {
                    Common.showToast(TutorEventAct.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_share:
                break;
            case R.id.ll_book:
                break;
            case R.id.iv_te_back:
                onBackPressed();
                break;
            case R.id.ll_SendMsg:
                if (edtMessage.getText().toString().length() == 0) {
                    Common.showToast(TutorEventAct.this, "Please Enter Comments");
                } else {
//                    new RetrofitRequester(this).callPostServices(obj,3,"",true);
                }
                break;
        }
    }
}
