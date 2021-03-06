package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageView;
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
    private String str_name, str_receiver_id, str_senderID;
    private ImageView iv_chatback;

    Thread t1;
    private boolean flag_loading = false;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_load_more:
                chat_count = chat_count + 30;
                PersonalChatRequest request = new PersonalChatRequest();
                request.numItems = String.valueOf(chat_count);
                request.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                request.receiverId = str_receiver_id;
                request.senderId = str_senderID;
                //flatListRequest.building_id="4";

                try {
                    obj = Class.forName(PersonalChatRequest.class.getName()).cast(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                new RetrofitRequester(this).callPostServices(obj, 1, "chat_details", true);

                break;
            case R.id.iv_chatback:
                onBackPressed();
                finish();
                break;
            case R.id.ll_chat_send:
                if (et_chat_text.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Message should not be empty", Toast.LENGTH_SHORT).show();
                } else {

                    ChatMsgSendReq chatMsgSendReq = new ChatMsgSendReq();
                    chatMsgSendReq.numItems = String.valueOf(chat_count);
                    chatMsgSendReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                    chatMsgSendReq.receiverId = str_receiver_id;
                    chatMsgSendReq.senderId = str_senderID;
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
        iv_chatback.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

//        intent.putExtra("Key_name", responseBeans.get(position).getFirst_name() + " " + responseBeans.get(position).getLast_name());
//        intent.putExtra("Key_ReceiverId", responseBeans.get(position).getReceiver_id());
//        intent.putExtra("Key_SenderId", responseBeans.get(position).getSender_id());

        Intent intent = getIntent();
        str_name = intent.getStringExtra("Key_name");
        str_receiver_id = intent.getStringExtra("Key_ReceiverId");
        str_senderID = intent.getStringExtra("Key_SenderId");


        responseBeans = new ArrayList<>();
        responseBeans_next = new ArrayList<>();
        //rview_personal_chat = findViewById(R.id.rview_personal_chat);
        tv_chat_personal_name = findViewById(R.id.tv_chat_personal_name);
        tv_load_more = findViewById(R.id.tv_load_more);
        ll_chat_send = findViewById(R.id.ll_chat_send);
        et_chat_text = findViewById(R.id.et_chat_text);
        chatlist = findViewById(R.id.chatlist);
        iv_chatback = findViewById(R.id.iv_chatback);
        tv_chat_personal_name.setText(str_name);

        PersonalChatRequest request = new PersonalChatRequest();
        request.numItems = String.valueOf(chat_count);
        request.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        request.receiverId = str_receiver_id;
        request.senderId = str_senderID;
        try {
            obj = Class.forName(PersonalChatRequest.class.getName()).cast(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "chat_details", true);


        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                makeServiceCallWS();
                handler.postDelayed(this, 10000);
            }
        };

//        chatlist.setOnScrollListener(new ListView.OnScrollListener() {
//
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//
//            }
//
//            public void onScroll(AbsListView view, int firstVisibleItem,
//                                 int visibleItemCount, int totalItemCount) {
//
//                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
//                    if (flag_loading == false) {
//                        flag_loading = true;
//                        callAutoLoad();
//
//                    }
//                }
//            }
//        });


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
                                if (personalChatPojo.getResponse().size() < 30) {
                                    tv_load_more.setVisibility(View.GONE);
                                } else if (personalChatPojo.getResponse().size() == 30) {
                                    tv_load_more.setVisibility(View.VISIBLE);
                                }
                                responseBeans = personalChatPojo.getResponse();
                                Collections.reverse(responseBeans);
                                responseBeans_next.addAll(0, responseBeans);
                                // idx_count = idx_count + 15;
                                responseBeans.clear();
                                //  flag_loading = false;
                                adapter = new ChatAdapter(PersonalChatActivity.this, responseBeans_next);


                                chatlist.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
                                chatlist.setAdapter(adapter);

// save index and top position
                                int index = chatlist.getFirstVisiblePosition();
                                View v = chatlist.getChildAt(0);
                                int top = (v == null) ? 0 : (v.getTop() - chatlist.getPaddingTop());
// ...
// restore index and position
                                chatlist.setSelectionFromTop(index, top);


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


//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        makeServiceCallWS();
//                                        handler.postDelayed(this, 9000);
//                                    }
//                                }, 9000);
                                handler.postDelayed(runnable, 10000);


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
        request.receiverId = str_receiver_id;
        request.senderId = str_senderID;
        //flatListRequest.building_id="4";
        try {
            obj = Class.forName(PersonalChatRequest.class.getName()).cast(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "chat_details", false);
    }

    private void callAutoLoad() {
        chat_count = chat_count + 30;
        PersonalChatRequest request = new PersonalChatRequest();
        request.numItems = String.valueOf(chat_count);
        request.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        request.receiverId = str_receiver_id;
        request.senderId = str_senderID;
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(PersonalChatRequest.class.getName()).cast(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        new RetrofitRequester(this).callPostServices(obj, 1, "chat_details", true);


    }

    @Override
    protected void onResume() {
        super.onResume();
        // makeServiceCallWS();
    }

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
        if (handler != null)
            handler.removeCallbacks(runnable);
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null)
            handler.removeCallbacks(runnable);
    }
}
