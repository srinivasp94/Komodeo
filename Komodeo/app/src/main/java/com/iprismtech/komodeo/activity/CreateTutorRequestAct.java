package com.iprismtech.komodeo.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.util.Calendar;

public class CreateTutorRequestAct extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {

    TextView txt_datentime, edtStartDate, txt_reoccuring_settings, tv_txt_paymnet, txt_location, txtCreateEvent, txtInvitedFriends, tv_address, tv_payment, tv_title;
    LinearLayout ll_addfriends;
    ImageView iv_back;
    private EditText edtTitle, edtStartTime, edtEndDate,
            edtSessionAmount, edtmaxSize, edtAddNote, edtEndTime;
    private RadioGroup rg_paymentType, rg_privacy_type;
    private RadioButton rb_persession, rb_perHead;
    private RelativeLayout RelativeSelectLocation, RelativeFriendsAdd;
    private Object obj;
    private ArrayList<FriendList> inviteFriendsList = new ArrayList<>();
    private String screenid;
    private String post_Status, selected_lat, selected_lng, selected_address;
    private TimePickerDialog mTimePicker;
    private String str_start_time_12hr, str_start_Time_24hr, str_end_time_12hr, str_end_Time_24hr;
    private int selected_start_time, selected_end_time;
    Calendar mcurrentTime = Calendar.getInstance();
    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
    int minute = mcurrentTime.get(Calendar.MINUTE);
    private String result_paymnet, event_type, class_id, class_name, result_privacy_type;
    private StringBuilder stringBuilder;
    private String friendsIds;

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
        edtEndTime.setOnClickListener(this);
        txtCreateEvent.setOnClickListener(this);
        ll_addfriends.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

//        createTutorIntent.putExtra("Key_Event", "tutor");
//        createTutorIntent.putExtra("Key_ClassId", course_ID);
//        createTutorIntent.putExtra("Key_CourseName", course_name);

        Intent intent = getIntent();
        event_type = intent.getStringExtra("Key_Event");
        class_id = intent.getStringExtra("Key_ClassId");
        class_name = intent.getStringExtra("Key_CourseName");


        txt_datentime = findViewById(R.id.txt_datentime);
        iv_back = findViewById(R.id.iv_back);
        ll_addfriends = findViewById(R.id.ll_addfriends);
        txt_location = findViewById(R.id.txt_location);
        ll_addfriends = findViewById(R.id.ll_addfriends);
        edtTitle = findViewById(R.id.edtTitle);
        edtStartDate = findViewById(R.id.edtStartDate);
        edtStartTime = findViewById(R.id.edtStartTime);
        edtEndTime = findViewById(R.id.edtEndTime);
        edtSessionAmount = findViewById(R.id.edtSessionAmount);
        edtmaxSize = findViewById(R.id.edtmaxSize);
        txtInvitedFriends = findViewById(R.id.txtInvitedFriends);
        edtAddNote = findViewById(R.id.edtAddNote);
        RelativeSelectLocation = findViewById(R.id.RelativeSelectLocation);
        RelativeFriendsAdd = findViewById(R.id.RelativeFriendsAdd);
        txtCreateEvent = findViewById(R.id.txtCreateEvent);
        tv_address = findViewById(R.id.tv_address);
        tv_payment = findViewById(R.id.tv_payment);
        rg_paymentType = findViewById(R.id.rg_paymentType);
        rg_privacy_type = findViewById(R.id.rg_privacy_type);
        tv_title = findViewById(R.id.tv_title);
        tv_txt_paymnet = findViewById(R.id.tv_txt_paymnet);

        tv_title.setText(class_name + " with " + SharedPrefsUtils.getString(SharedPrefsUtils.KEY_NAME) + " " + SharedPrefsUtils.getString(SharedPrefsUtils.KEY_LAST_NAME));
        if (event_type.equalsIgnoreCase("tutor")) {
            txtCreateEvent.setText("Create Tutor Request");
        } else {
            txtCreateEvent.setText("Create Study Request");

            rg_paymentType.setVisibility(View.GONE);
            edtSessionAmount.setVisibility(View.GONE);
            tv_txt_paymnet.setVisibility(View.GONE);
            tv_payment.setVisibility(View.GONE);

        }


//        runOnUiThread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                GPSTracker gpsTracker = new GPSTracker(CreateTutorRequestAct.this);
//                current_lat = gpsTracker.getLatitude();
//                current_lng = gpsTracker.getLongitude();
//                prefsUtils.storelatlangcode(String.valueOf(lat), String.valueOf(lng));
//                String ddress = Common.getAddressString(getActivity(), lat, lng);
//                Variables.current_address = ddress;
//                tv_loc_city.setText(ddress);
//            }
//        });
//    }
        rg_paymentType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    // tv.setText("Checked:" + checkedRadioButton.getText());
                    result_paymnet = checkedRadioButton.getText().toString();
                    if (result_paymnet.equalsIgnoreCase("Per Session")) {
                        tv_payment.setText("Amount Per Session");
                    } else {
                        tv_payment.setText("Amount Per Head");
                    }

//                    Intent intent = getIntent();
//                    if (intent != null) {
//                        screenid = intent.getStringExtra("Key_Screen");
//                        inviteFriendsList = intent.getParcelableArrayListExtra("Key_inviteFrien");
//                        if (inviteFriendsList != null && inviteFriendsList.size() > 0)
//                            txtInvitedFriends.setText(inviteFriendsList.size() + " People Invited");
//                    }
                }

            }
        });


        callPrivacyRadioGroup();

    }

    private void callPrivacyRadioGroup() {

        rg_privacy_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    // tv.setText("Checked:" + checkedRadioButton.getText());
                    result_privacy_type = checkedRadioButton.getText().toString();
                    if (result_privacy_type.equalsIgnoreCase("Public")) {

                        result_privacy_type = "public";
                    } else {
                        //tv_payment.setText("Amount Per Head");
                        result_privacy_type = "invitees";
                    }
                }

            }
        });

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
                            finish();
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

                Intent intent1 = new Intent(CreateTutorRequestAct.this, LocationGetActivity.class);
                startActivityForResult(intent1, 101);

                // startActivityForResult(new Intent(CreateTutorRequestAct.this,LocationGetActivity.class));
                break;
            case R.id.RelativeFriendsAdd:
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.edtStartTime:

                mTimePicker = new TimePickerDialog(CreateTutorRequestAct.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hour = selectedHour;
                        int minutes = selectedMinute;
                        String timeSet = "";
                        if (hour > 12) {
                            hour -= 12;
                            timeSet = "pm";
                        } else if (hour == 0) {
                            hour += 12;
                            timeSet = "pm";
                        } else if (hour == 12) {
                            timeSet = "am";
                        } else {
                            timeSet = "am";
                        }
                        String min = "";
                        if (minutes < 10)
                            min = "0" + minutes;
                        else
                            min = String.valueOf(minutes);

                        // Append in a StringBuilder
                        str_start_time_12hr = new StringBuilder().append(hour).append(':')
                                .append(min).append(" ").append(timeSet).toString();
                        // tv_kid_out_time.setText(str_out_time_12hr);
                        //  tv_opening_time.setText(selectedHour + ":" + selectedMinute);

                        selected_start_time = selectedHour;
                        str_start_Time_24hr = selectedHour + ":" + min + ":" + "00";
                        edtStartTime.setText(str_start_Time_24hr);

                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

                break;


            case R.id.edtEndTime:
                mTimePicker = new TimePickerDialog(CreateTutorRequestAct.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hour = selectedHour;
                        int minutes = selectedMinute;
                        String timeSet = "";
                        if (hour > 12) {
                            hour -= 12;
                            timeSet = "pm";
                        } else if (hour == 0) {
                            hour += 12;
                            timeSet = "pm";
                        } else if (hour == 12) {
                            timeSet = "am";
                        } else {
                            timeSet = "am";
                        }

                        String min = "";
                        if (minutes < 10)
                            min = "0" + minutes;
                        else
                            min = String.valueOf(minutes);

                        // Append in a StringBuilder
                        str_end_time_12hr = new StringBuilder().append(hour).append(':')
                                .append(min).append(" ").append(timeSet).toString();
                        // tv_kid_out_time.setText(str_out_time_12hr);
                        //  tv_opening_time.setText(selectedHour + ":" + selectedMinute);

                        selected_end_time = selectedHour;
                        str_end_Time_24hr = selectedHour + ":" + min + ":" + "00";
                        edtEndTime.setText(str_end_Time_24hr);

                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;
            case R.id.txtCreateEvent:
                if (edtTitle.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please enter title");
                } else if (edtStartDate.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Select Date");
                } else if (edtStartTime.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Select Start Time");
                } else if (edtEndTime.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Select End Time");
                }
//                else if (edtSessionAmount.getText().toString().length() == 0) {
//                    Common.showToast(CreateTutorRequestAct.this, "please Enter Amount");
//                }
                else if (edtmaxSize.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Enter Size");
                } else if (edtAddNote.getText().toString().length() == 0) {
                    Common.showToast(CreateTutorRequestAct.this, "please Enter Note");
                } else {
                    CreateEventReq req = new CreateEventReq();
                    req.userId = SharedPrefsUtils.getInstance(CreateTutorRequestAct.this).getId();
                    req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                    req.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
                    req.classId = class_id;
                    req.eventName = edtTitle.getText().toString();
                    req.eventDate = edtStartDate.getText().toString();
                    /*req.startTime = edtStartTime.getText().toString();
                    req.endTime = edtEndDate.getText().toString();*/
                    req.startTime = edtStartTime.getText().toString();
                    req.endTime = edtEndTime.getText().toString();

                    if (event_type.equalsIgnoreCase("study")) {

                        req.eventPaymentType = "0";
                        req.perSession = "0";
                        req.per_head = "0";
                    } else {
                        if (result_paymnet.equalsIgnoreCase("Per Session")) {
                            req.eventPaymentType = "per_session";
                            req.perSession = edtSessionAmount.getText().toString();
                        } else {
                            req.eventPaymentType = "per_head";
                            req.per_head = edtSessionAmount.getText().toString();
                        }
                    }

                    req.eventType = event_type;
                    req.maxGroupSize = edtmaxSize.getText().toString();
                    req.note = edtAddNote.getText().toString();
                    req.locationAddress = tv_address.getText().toString();
                    req.locationLat = selected_lat;
                    req.locationLng = selected_lng;
                    req.peopleInvited = stringBuilder.toString();
                    req.privacy = result_privacy_type;


//                    req.perSession = "50";
//                    req.per_head = "10";


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

            if (requestCode == 101) {
                post_Status = data.getStringExtra("location_Status");
                if (post_Status.equalsIgnoreCase("ok")) {
//                    TextView count_commnets = adaptet_item_view.getRootView().findViewById(R.id.tv_comments_count);
//                    int str_count_comments = Integer.parseInt(count_commnets.getText().toString());
//                    str_count_comments = str_count_comments + 1;
//                    count_commnets.setText(str_count_comments + "");
                    selected_lat = data.getStringExtra("user_lat");
                    selected_lng = data.getStringExtra("user_lang");
                    selected_address = data.getStringExtra("user_address");
                    tv_address.setText(selected_address);
                }
            } else {
                inviteFriendsList = data.getParcelableArrayListExtra("Key_inviteFrien");
                if (inviteFriendsList != null && inviteFriendsList.size() > 0) {
                    txtInvitedFriends.setText(inviteFriendsList.size() + " People Invited");
                    stringBuilder = new StringBuilder();
                    friendsIds = "";
                    for (int i = 0; i < inviteFriendsList.size(); i++) {
                        friendsIds = inviteFriendsList.get(i).id + ",";
                        stringBuilder.append(friendsIds);
                    }

                    Toast.makeText(context, "" + stringBuilder, Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}
