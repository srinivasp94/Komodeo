//package com.iprismtech.komodeo.activity;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.intel.homize.BaseAbstractActivity;
//import com.example.intel.homize.R;
//import com.example.intel.homize.controllers.ApplicationController;
//import com.example.intel.homize.models.ChatHistoryRequest;
//import com.example.intel.homize.retrofitnetwork.RetrofitRequester;
//import com.example.intel.homize.retrofitnetwork.RetrofitResponseListener;
//import com.example.intel.homize.utilities.Common;
//import com.example.intel.homize.utilities.SharedPrefsUtils;
//import com.google.gson.Gson;
//
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.LinkedHashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.TreeSet;
//
//public class ChatActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {
//
//    private TextView title;
//    private ImageView back, messageSend;
//    private ListView listview_Chat;
//    private EditText editText_message;
//    private Object obj;
//    private Runnable runnable;
//    private Handler handler;
//
//    private ArrayList<ChatResponse.ChatmessageList> chatmessageLists = new ArrayList<>();
//    private ArrayList<ChatResponse.ChatmessageList> messageslist= new ArrayList<>();
//    private ChatAdapter adapter;
//    private int count = 0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_chat);
//        ApplicationController.getInstance().setContext(context);
//
//    }
//
//    @Override
//    protected View getView() {
//        View view = getLayoutInflater().inflate(R.layout.activity_chat, null);
//        return view;
//    }
//
//    @Override
//    protected void setListenerToViews() {
//        super.setListenerToViews();
//        back.setOnClickListener(this);
//        messageSend.setOnClickListener(this);
//    }
//
//    @Override
//    protected void initializeViews() {
//        super.initializeViews();
//        title = findViewById(R.id.toot_title);
//        back = findViewById(R.id.img_back);
//        listview_Chat = findViewById(R.id.chatlist);
//        messageSend = findViewById(R.id.img_chatsend);
//        editText_message = findViewById(R.id.edt_chatmessage);
//        title.setText("Chat");
//
//        makeServiceCallWS();
//
//        handler = new Handler();
//    }
//
//    private void makeServiceCallWS() {
//        ChatHistoryRequest req = new ChatHistoryRequest();
//
//        req.userId = SharedPrefsUtils.getInstance(ChatActivity.this).getUserId();
//        req.numItems = count;
//        Common.commonLogs(ChatActivity.this,"##numitems "+count);
//        try {
//            obj = Class.forName(ChatHistoryRequest.class.getName()).cast(req);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        new RetrofitRequester(ChatActivity.this).callPostServices(obj, 2, "chathistory", false);
//
//    }
//
//    @Override
//    public void setPresenter() {
//
//    }
//
//    @Override
//    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
//        if (objectResponse == null || objectResponse.equals("")) {
//            Toast.makeText(context, "Please try again", Toast.LENGTH_SHORT).show();
//        } else {
//            try {
//                Gson gson = new Gson();
//                String JsonString = gson.toJson(objectResponse);
//                JSONObject object = new JSONObject(JsonString);
//                if (object.optBoolean("status") == true) {
//
//                    switch (requestId) {
//                        case 1:
//
//                            makeServiceCallWS();
//
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//                                    makeServiceCallWS();
//                                }
//                            }, 90000);//        chathistory
//
//
//                           /* chatmessageLists.clear();
//                            ChatResponse response = Common.getSpecificDataObject(objectResponse, ChatResponse.class);
//                            chatmessageLists = (ArrayList<ChatResponse.ChatmessageList>) response.response;
//                            if (chatmessageLists != null && chatmessageLists.size() > 0) {
//                                listview_Chat.setAdapter(new ChatAdapter(this, chatmessageLists));
//                            } else {
//                                Common.showToast(this, "No Messages ");
//                            }*/
//                            break;
//                        case 2:
//
//                            ChatResponse response = Common.getSpecificDataObject(objectResponse, ChatResponse.class);
//                            chatmessageLists = (ArrayList<ChatResponse.ChatmessageList>) response.response;
//
//
//                            ChatResponse myCurrentChat = new Gson().fromJson(JsonString, ChatResponse.class);
//                            for (int i = 0; i < myCurrentChat.response.size(); i++)
//                                chatmessageLists.add(myCurrentChat.response.get(i));
//                            Set set = new TreeSet(new Comparator<ChatResponse.ChatmessageList>() {
//                                @Override
//                                public int compare(ChatResponse.ChatmessageList o1, ChatResponse.ChatmessageList o2) {
//                                    if (o1.id.equalsIgnoreCase(o2.id)) {
//                                        return 0;
//                                    }
//                                    return 1;
//                                }
//                            });
//                            set.addAll(chatmessageLists);
//                            final ArrayList newList = new ArrayList(set);
//                            chatmessageLists= newList;
//                            LinkedHashSet<ChatResponse.ChatmessageList> linkedHashSet = new LinkedHashSet<>(chatmessageLists);
//                            List<ChatResponse.ChatmessageList> message = new ArrayList<>(linkedHashSet);
//
//                            count = message.size();
//
//                            adapter = new ChatAdapter(this, message);
//                            listview_Chat.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
////                                lstview.setAdapter(messageAdapter);
//                            listview_Chat.setAdapter(adapter);
//
//
//
//
//
//
//
//
///*                            if (chatmessageLists != null && chatmessageLists.size() > 0) {
//
//                                Set set = new TreeSet(new Comparator<ChatResponse.ChatmessageList>() {
//
//                                    @Override
//                                    public int compare(ChatResponse.ChatmessageList chatmessageList, ChatResponse.ChatmessageList t1) {
//                                        if (chatmessageList.id.equalsIgnoreCase(t1.id)) {
//                                            return 0;
//                                        }
//                                        return 1;
//                                    }
//                                });
//                                set.addAll(chatmessageLists);
//
//                                final ArrayList newList = new ArrayList(set);
//                                chatmessageLists = newList;
//
//                                LinkedHashSet<ChatResponse.ChatmessageList> linkedHashSet = new LinkedHashSet<>(chatmessageLists);
//                                List<ChatResponse.ChatmessageList> message = new ArrayList<>(linkedHashSet);
//                                adapter = new ChatAdapter(this, message);
//                                listview_Chat.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
////                                lstview.setAdapter(messageAdapter);
//                                listview_Chat.setAdapter(adapter);
//
////                                handler.postDelayed(runnable, 10000);
//                            } else {
//                                Common.showToast(this, "No Messages ");
//                            }*/
//
//
//                            break;
//                    }
//                } else {
//                    Common.showToast(this, object.optString("message"));
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.img_back:
//                onBackPressed();
//                break;
//            case R.id.img_chatsend:
//                if (editText_message.getText().toString().length() == 0) {
//                    Common.showToast(this, "Please Enter Message");
//                } else {
//                    CharRequest charRequest = new CharRequest();
//                    charRequest.userId = SharedPrefsUtils.getInstance(this).getUserId();
////                    charRequest.userId = 3;
////                    charRequest.receiverId = "1";
//                    charRequest.message = editText_message.getText().toString();
//                    try {
//                        obj = Class.forName(CharRequest.class.getName()).cast(charRequest);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    new RetrofitRequester(this).callPostServices(obj, 1, "user_chatsend", false);
//                    editText_message.setText("");
//                }
//                break;
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if (handler != null)
//            handler.removeCallbacks(runnable);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (handler != null)
//            handler.removeCallbacks(runnable);
//
//
//    }
//}
