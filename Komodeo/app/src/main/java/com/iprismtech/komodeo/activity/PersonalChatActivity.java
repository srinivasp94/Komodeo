package com.iprismtech.komodeo.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.ChatAdapter;
import com.iprismtech.komodeo.adapters.PersonalChatAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.PersonalChatPojo;
import com.iprismtech.komodeo.request.ChatMsgSendReq;
import com.iprismtech.komodeo.request.PersonalChatRequest;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonalChatActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private RecyclerView rview_personal_chat;
    private TextView tv_chat_personal_name, tv_load_more;
    private LinearLayoutManager manager;
    private PersonalChatPojo personalChatPojo;
    private PersonalChatAdapter personalChatAdapter;
    private ListView chatlist;
    private List<PersonalChatPojo.ResponseBean> responseBeans;
    private List<PersonalChatPojo.ResponseBean> responseBeans_next;
    private LinearLayout ll_chat_send;
    private EditText et_chat_text;
    private int chat_count = 0;
    private Object obj;
    private ChatAdapter adapter;
    private Handler handler;
    private int idx_count = 0;
    private Runnable runnable;

    Thread t1;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_load_more:
                chat_count = chat_count + 15;
                PersonalChatRequest request = new PersonalChatRequest();
                request.numItems = String.valueOf(chat_count);
                request.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                request.receiverId = "2";
                request.senderId = "1";
                //flatListRequest.building_id="4";

                try {
                    obj = Class.forName(PersonalChatRequest.class.getName()).cast(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(this).callPostServices(obj, 1, "chat_details", true);

                break;
            case R.id.ll_chat_send:
                if (et_chat_text.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Message should not be empty", Toast.LENGTH_SHORT).show();
                } else {

                    ChatMsgSendReq chatMsgSendReq = new ChatMsgSendReq();
                    chatMsgSendReq.numItems = String.valueOf(chat_count);
                    chatMsgSendReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                    chatMsgSendReq.receiverId = "2";
                    chatMsgSendReq.senderId = "1";
                    chatMsgSendReq.message = et_chat_text.getText().toString();
                    chatMsgSendReq.numItems = "0";

                    //flatListRequest.building_id="4";

                    try {
                        obj = Class.forName(ChatMsgSendReq.class.getName()).cast(chatMsgSendReq);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 3, "submit_chat_message", false);
                    et_chat_text.setText("");
                }
                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.personal_chat_layout, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_load_more.setOnClickListener(this);
        ll_chat_send.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        responseBeans = new ArrayList<>();
        responseBeans_next = new ArrayList<>();
        //rview_personal_chat = findViewById(R.id.rview_personal_chat);
        tv_chat_personal_name = findViewById(R.id.tv_chat_personal_name);
        tv_load_more = findViewById(R.id.tv_load_more);
        ll_chat_send = findViewById(R.id.ll_chat_send);
        et_chat_text = findViewById(R.id.et_chat_text);
        chatlist = findViewById(R.id.chatlist);


        PersonalChatRequest request = new PersonalChatRequest();
        request.numItems = String.valueOf(chat_count);
        request.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        request.receiverId = "2";
        request.senderId = "1";

        t1 = new Thread();

        handler = new Handler();
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(PersonalChatRequest.class.getName()).cast(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "chat_details", true);

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        {
            if (objectResponse == null || objectRequest.equals("")) {
                Common.showToast(PersonalChatActivity.this, "PLease Try again");
            } else {
                try {
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(objectResponse);
                    JSONObject object = new JSONObject(jsonString);
                    if (object.optBoolean("status") == true) {
                        switch (requestId) {
                            case 1:

                                personalChatPojo = gson.fromJson(jsonString, PersonalChatPojo.class);
//                                manager = new LinearLayoutManager(PersonalChatActivity.this);
//                                manager.setReverseLayout(true);
//                                manager.setOrientation(LinearLayoutManager.VERTICAL);
//                                rview_personal_chat.setLayoutManager(manager);
                                responseBeans = personalChatPojo.getResponse();
                                Collections.reverse(responseBeans);
                                responseBeans_next.addAll(0, responseBeans);
                                // idx_count = idx_count + 15;
                                responseBeans.clear();

                                adapter = new ChatAdapter(PersonalChatActivity.this, responseBeans_next);
                                chatlist.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
                                chatlist.setAdapter(adapter);
                                //   personalChatAdapter = new PersonalChatAdapter(PersonalChatActivity.this, responseBeans);
                                // rview_personal_chat.scrollToPosition(responseBeans.size());
                                //   rview_personal_chat.setAdapter(personalChatAdapter);

//                                rview_personal_chat.post(new Runnable() {
//                                    public void run() {
//                                        rview_personal_chat.scrollToPosition(responseBeans.size()-1);
//                                    }
//                                });


                                break;
                            case 2:
                                personalChatPojo = gson.fromJson(jsonString, PersonalChatPojo.class);
                                Collections.reverse(personalChatPojo.getResponse());
                                responseBeans_next.addAll(personalChatPojo.getResponse());
                                adapter = new ChatAdapter(PersonalChatActivity.this, responseBeans_next);
                                chatlist.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
                                chatlist.setAdapter(adapter);

                                break;
                            case 3:

                                makeServiceCallWS();


//                                  @Override
//                                  public void handleMessage(Message msg) {
//                                      h
//                                  }
//                              };


//                                runnable = new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        makeServiceCallWS();
//                                        handler.postDelayed(this, 10000);
//                                    }
//                                };


                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        makeServiceCallWS();
                                        //handler.postDelayed(this, 10000);
                                    }
                                }, 9000);


                                break;
                        }
                    } else {
                        Common.showToast(PersonalChatActivity.this, object.optString("message"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void makeServiceCallWS() {
        responseBeans = new ArrayList<>();
        responseBeans_next = new ArrayList<>();
        chat_count = 0;

        PersonalChatRequest request = new PersonalChatRequest();
        request.numItems = String.valueOf(chat_count);
        request.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        request.receiverId = "2";
        request.senderId = "1";
        //flatListRequest.building_id="4";
        try {
            obj = Class.forName(PersonalChatRequest.class.getName()).cast(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "chat_details", false);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        makeServiceCallWS();
//    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null)
            handler.removeCallbacks(runnable);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (handler != null)
            handler.removeCallbacks(runnable);


    }

    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null)
            handler.removeCallbacks(runnable);
    }
}
