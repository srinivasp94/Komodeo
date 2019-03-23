package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.MapDialog;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.request.CreateEventReq;
import com.iprismtech.komodeo.responses.FriendList;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.DatePickerDaloge;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class CreateTutorRequestAct extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {

    TextView txt_datentime, txt_reoccuring_settings, txt_location, txtCreateEvent, txtInvitedFriends;
    LinearLayout ll_addfriends;
    ImageView iv_back;
    private EditText edtTitle, edtStartDate, edtStartTime, edtEndDate,
            edtSessionAmount, edtmaxSize, edtAddNote;
    private RadioGroup rg_paymentType;
    private RelativeLayout RelativeSelectLocation, RelativeFriendsAdd;
    private Object obj;
    private ArrayList<FriendList> inviteFriendsList = new ArrayList<>();
    private String screenid;
    private StringBuilder stringBuilder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        setContentView(R.layout.create_tutor_event_request_layout);


        txt_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapDialog ratingDialog = new MapDialog();
                FragmentManager fragmentManager = getSupportFragmentManager();
                ratingDialog.show(fragmentManager, "Dialog fragment");
            }
        });


    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.create_tutor_event_request_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        RelativeSelectLocation.setOnClickListener(this);
        RelativeFriendsAdd.setOnClickListener(this);
        edtStartDate.setOnClickListener(this);
        edtStartTime.setOnClickListener(this);
        edtEndDate.setOnClickListener(this);
        txtCreateEvent.setOnClickListener(this);
        ll_addfriends.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        txt_datentime = findViewById(R.id.txt_datentime);
        iv_back = findViewById(R.id.iv_back);
        ll_addfriends = findViewById(R.id.ll_addfriends);
        txt_location = findViewById(R.id.txt_location);
        ll_addfriends = findViewById(R.id.ll_addfriends);
        edtTitle = findViewById(R.id.edtTitle);
        edtStartDate = findViewById(R.id.edtStartDate);
        edtStartTime = findViewById(R.id.edtStartTime);
        edtEndDate = findViewById(R.id.edtEndDate);
        edtSessionAmount = findViewById(R.id.edtSessionAmount);
        edtmaxSize = findViewById(R.id.edtmaxSize);
        txtInvitedFriends = findViewById(R.id.txtInvitedFriends);
        edtAddNote = findViewById(R.id.edtAddNote);
        RelativeSelectLocation = findViewById(R.id.RelativeSelectLocation);
        RelativeFriendsAdd = findViewById(R.id.RelativeFriendsAdd);
        txtCreateEvent = findViewById(R.id.txtCreateEvent);

        rg_paymentType = findViewById(R.id.rg_paymentType);

        Intent intent = getIntent();
        if (intent != null) {
            screenid = intent.getStringExtra("Key_Screen");
            inviteFriendsList = intent.getParcelableArrayListExtra("Key_inviteFrien");
            if (inviteFriendsList != null && inviteFriendsList.size() > 0)
                txtInvitedFriends.setText(inviteFriendsList.size() + " People Invited");
        }


    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(CreateTutorRequestAct.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            String mMessage = jsonObject.optString("message");
                            Common.showToast(CreateTutorRequestAct.this, mMessage);
                            break;
                    }
                } else {
                    Common.showToast(CreateTutorRequestAct.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.RelativeSelectLocation:
                break;
            case R.id.RelativeFriendsAdd:
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.txtCreateEvent:
                if (edtTitle.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please enter title");
                } else if (edtStartDate.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Selct Date");
                } else if (edtStartTime.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Selct Start Time");
                } else if (edtEndDate.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Selct End Time");
                } else if (edtSessionAmount.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Enter Amount");
                } else if (edtmaxSize.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Enter Size");
                } else if (edtAddNote.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Enter Note");
                } else {
                    CreateEventReq req = new CreateEventReq();
                    req.userId = SharedPrefsUtils.getInstance(CreateTutorRequestAct.this).getId();
                    req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                    req.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
                    req.classId = "1";
                    req.eventName = "new Event Srinivasa Testing";
                    req.eventDate = edtStartDate.getText().toString();
                    /*req.startTime = edtStartTime.getText().toString();
                    req.endTime = edtEndDate.getText().toString();*/
                    req.startTime = "11:15 AM";
                    req.endTime = "2.00 PM";
                    req.eventPaymentType = "persession";
                    req.eventType = "Tutor";
                    req.maxGroupSize = edtmaxSize.getText().toString();
                    req.note = edtAddNote.getText().toString();
                    req.locationAddress = "Hyderabad";
                    req.locationLat = "17.34";
                    req.locationLng = "77.52";
                    req.peopleInvited = "1";
                    req.privacy = "public";
                    req.perSession = "50";
                    req.per_head = "10";


                    try {
                        obj = Class.forName(CreateEventReq.class.getName()).cast(req);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "create_event", true);

                }
                break;
            case R.id.edtStartDate:
                DatePickerDaloge.showDatePickerDialog(CreateTutorRequestAct.this, "", edtStartDate);
                break;
            case R.id.ll_addfriends:
                Intent intent = new Intent(CreateTutorRequestAct.this, InviteFriendsAct.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            inviteFriendsList = data.getParcelableArrayListExtra("Key_inviteFrien");
            if (inviteFriendsList != null && inviteFriendsList.size() > 0) {
                txtInvitedFriends.setText(inviteFriendsList.size() + " People Invited");
                stringBuilder = new StringBuilder();
                String friendsIds = "";
                for (int i = 0; i < inviteFriendsList.size(); i++) {
                    friendsIds = inviteFriendsList.get(i).id + ",";
                    stringBuilder.append(friendsIds);
                }
                Toast.makeText(context, "" + stringBuilder, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
