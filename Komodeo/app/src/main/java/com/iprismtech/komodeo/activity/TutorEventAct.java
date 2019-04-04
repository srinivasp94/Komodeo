package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.EventCommentAdapter;
import com.iprismtech.komodeo.adapters.EventuserHorizonAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.request.BookEventReq;
import com.iprismtech.komodeo.request.EventCommentSubmitReq;
import com.iprismtech.komodeo.request.EventCommentsReq;
import com.iprismtech.komodeo.request.EventSingelReq;
import com.iprismtech.komodeo.responses.EventComment;
import com.iprismtech.komodeo.responses.EventCommentRes;
import com.iprismtech.komodeo.responses.EventMember;
import com.iprismtech.komodeo.responses.TutorSingelRes;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.GPSTracker;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class TutorEventAct extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {

    private ImageView iv_te_back, imgshare;
    private TextView txt_Date, txt_tutoringwith, txtinTime, txtInvitedBy, txttoolbaBook,
            txt_estimation, txtPeopleLeft, txtPeopleGoing, txt_address, txt_desc, txt_map_coming_soon, txt_going, txt_book, tv_title;
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
    private String eventCreateATutorId, eventTitle;
    private String lat, lng;
    private String str_created_by;

   /* book_event
    {
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjU4NjA2OTk1OTQ5NDE2MDJhNzc3OTNjNzAxMzA5MDRkIn0.XtoBKDn3vbov0k3-eP8MK45lrSwIGVZUFKt3Assak0I",
            "user_id":"2",
            "event_id":"2",
            "event_creator_user_id":"1"
    }*/

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
        txttoolbaBook.setOnClickListener(this);
        iv_te_back.setOnClickListener(this);
        txt_map_coming_soon.setOnClickListener(this);
        txt_book.setOnClickListener(this);
        imgshare.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        eventId = getIntent().getExtras().getString("Key_eventId", "");
        eventTitle = getIntent().getExtras().getString("Key_Type", "");
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
        txttoolbaBook = findViewById(R.id.txttoolbaBook);
        txt_map_coming_soon = findViewById(R.id.txt_map_coming_soon);
        txt_going = findViewById(R.id.txt_going);
        txt_book = findViewById(R.id.txt_book);
        imgshare = findViewById(R.id.imgshare);
        tv_title = findViewById(R.id.tv_title);

        edtMessage = findViewById(R.id.edtMessage);

        ll_book = findViewById(R.id.ll_book);
        ll_share = findViewById(R.id.ll_share);
        ll_SendMsg = findViewById(R.id.ll_SendMsg);

        tv_title.setText(eventTitle);
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
                            if (jsonObject.optJSONObject("response").optString("my_book_status").equalsIgnoreCase("accepted")) {
                                txt_going.setVisibility(View.VISIBLE);
                                txt_book.setVisibility(View.GONE);
                                txttoolbaBook.setVisibility(View.GONE);

                            } else if (jsonObject.optJSONObject("response").optString("my_book_status").equalsIgnoreCase("pending")) {
                                txt_going.setText("Pending from event creator");
                                txt_going.setVisibility(View.VISIBLE);
                                txt_book.setVisibility(View.GONE);
                                txttoolbaBook.setVisibility(View.GONE);
                            } else {
                                txt_going.setVisibility(View.GONE);
                                txt_book.setVisibility(View.VISIBLE);
                                txttoolbaBook.setVisibility(View.VISIBLE);
                            }
                            TutorSingelRes res = Common.getSpecificDataObject(objectResponse, TutorSingelRes.class);
                            lat = res.response.locationLat;
                            lng = res.response.locationLng;


                            eventCreateATutorId = res.response.userId;
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

                            txt_Date.setText(res.response.date.replace(" ", "\n"));


                            //txt_tutoringwith.setText(res.response.eventName + " Created By " + res.response.eventCreatedBy);
                            txt_tutoringwith.setText(res.response.eventName);// + " Created By " + res.response.eventCreatedBy);

                            str_created_by = res.response.eventCreatedBy;


                            txtinTime.setText(res.response.eventDate + " : " + res.response.startTime + " to " + res.response.endTime);
                            txtInvitedBy.setText("Invited By " + res.response.firstName + " " + res.response.lastName);
                            txt_estimation.setText(" $ " + res.response.totalPrice);
                            txtPeopleLeft.setText("Max Group Size " + res.response.maxGroupSize);

                            txtPeopleGoing.setText(res.response.peopleInvited + " people Going ");
                            txt_address.setText(res.response.locationAddress);
                            txt_desc.setText(res.response.note);
                            if (res.response.eventCreatedBy.equalsIgnoreCase("my_event")) {
                                Common.showToast(TutorEventAct.this, "You are not able to Book your Events.. ");
                                ll_book.setClickable(false);
                            } else
                                ll_book.setClickable(true);
                            break;
                        case 2:
                            Common.showToast(TutorEventAct.this, jsonObject.optString("message"));
                            edtMessage.setText("");
                            updateCommentsWS();
                            break;
                        case 3:
                            EventCommentRes commentRes = Common.getSpecificDataObject(objectResponse, EventCommentRes.class);
                            commentsArrayList = new ArrayList<>();
                            commentsArrayList = (ArrayList<EventComment>) commentRes.response;
                            if (commentsArrayList != null && commentsArrayList.size() > 0) {
                                commentAdapter = new EventCommentAdapter(TutorEventAct.this, commentsArrayList);
                                rv_commnet_tutor.setAdapter(commentAdapter);
                            }
                            break;
                        case 4:
                            Common.showToast(TutorEventAct.this, jsonObject.optString("message"));
                            finish();
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

    private void updateCommentsWS() {
        EventCommentsReq req = new EventCommentsReq();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        req.eventId = eventId;
        req.count = "0";

        try {
            obj = Class.forName(EventCommentsReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 3, "event_comments", true);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_share:
                break;
            case R.id.ll_book:
                if (str_created_by.equalsIgnoreCase("my_event")) {
                    Toast.makeText(this, "This Event is created by You", Toast.LENGTH_SHORT).show();
                } else {
                    bookEventWS();
                }

                break;
            case R.id.txttoolbaBook:
                if (str_created_by.equalsIgnoreCase("my_event")) {
                    Toast.makeText(this, "This Event is created by You", Toast.LENGTH_SHORT).show();
                } else {
                    bookEventWS();
                }
                break;

            case R.id.txt_book:
                if (str_created_by.equalsIgnoreCase("my_event")) {
                    Toast.makeText(this, "This Event is created by You", Toast.LENGTH_SHORT).show();
                } else {
                    bookEventWS();
                }
                break;
            case R.id.imgshare:
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_SUBJECT, "Komodeo");
//                intent.putExtra(Intent.EXTRA_TEXT, "Select one");
//                startActivity(Intent.createChooser(intent, "choose one"));


                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "\nLet me recommend you this application\n\n";
                sAux = sAux + "http://play.google.com/store/apps/details?id=" + getPackageName();
                intent.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(intent, "choose one"));
                break;
            case R.id.txt_map_coming_soon:
                try {
                    GPSTracker gpsTracker = new GPSTracker(TutorEventAct.this);
                    String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + gpsTracker.getLatitude() + "," + gpsTracker.getLongitude() + "&daddr=" + lat + "," + lng;
                    Intent intent1 = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(Intent.createChooser(intent1, "Select an application"));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Error Maps", e.toString());
                }
                break;
            case R.id.iv_te_back:
                onBackPressed();
                break;
            case R.id.ll_SendMsg:
                if (edtMessage.getText().toString().length() == 0) {
                    Common.showToast(TutorEventAct.this, "Please Enter Comments");
                } else {
                    EventCommentSubmitReq req = new EventCommentSubmitReq();
                    req.userId = SharedPrefsUtils.getInstance(TutorEventAct.this).getId();
                    req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                    req.eventId = eventId;
                    req.comment = edtMessage.getText().toString();
                    try {
                        obj = Class.forName(EventCommentSubmitReq.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 2, "submit_event_comment", true);
                }
                break;
        }
    }

    private void bookEventWS() {
        BookEventReq req = new BookEventReq();
        req.userId = SharedPrefsUtils.getInstance(TutorEventAct.this).getId();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        req.eventId = eventId;
        req.eventCreatorUserId = eventCreateATutorId;

        try {
            obj = Class.forName(BookEventReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 4, "book_event", true);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
