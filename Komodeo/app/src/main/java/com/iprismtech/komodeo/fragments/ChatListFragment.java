package com.iprismtech.komodeo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.activity.FriendRequestsActivity;
import com.iprismtech.komodeo.activity.PersonalChatActivity;
import com.iprismtech.komodeo.adapters.ChatListAdapter;
import com.iprismtech.komodeo.adapters.SearchChatAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.pojo.ChatListPojo;
import com.iprismtech.komodeo.pojo.ChatSearchPojo;
import com.iprismtech.komodeo.request.ChatListReq;
import com.iprismtech.komodeo.request.SearchChatReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatListFragment extends BaseAbstractFragment implements View.OnClickListener, RetrofitResponseListener {
    private RecyclerView rview_chat_list, rview_serach_chat;
    private LinearLayoutManager manager;
    private int chat_count = 0;
    private Object obj;
    private ChatListPojo chatListPojo;
    private ChatSearchPojo chatSearchPojo;
    private List<ChatListPojo.ResponseBean> responseBeans;
    private List<ChatSearchPojo.ResponseBean> responseBeans_search;
    private ChatListAdapter chatListAdapter;
    private SearchChatAdapter adapter;
    private TextView tv_load_more;
    private LinearLayout ll_frd_reqs;
    private EditText et_search_name;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_load_more:
                chat_count = chat_count + 10;
                ChatListReq listReq = new ChatListReq();
                listReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                listReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
                listReq.numItems = String.valueOf(chat_count);
                //flatListRequest.building_id="4";

                try {
                    obj = Class.forName(ChatListReq.class.getName()).cast(listReq);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(this).callPostServices(obj, 1, "chats", true);

                break;
            case R.id.ll_frd_reqs:
                Intent intent = new Intent(getActivity(), FriendRequestsActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_load_more.setOnClickListener(this);
        ll_frd_reqs.setOnClickListener(this);
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        responseBeans = new ArrayList<>();
        rview_chat_list = view.findViewById(R.id.rview_chat_list);
        tv_load_more = view.findViewById(R.id.tv_load_more);
        ll_frd_reqs = view.findViewById(R.id.ll_frd_reqs);
        et_search_name = view.findViewById(R.id.et_search_name);
        rview_serach_chat = view.findViewById(R.id.rview_serach_chat);

        ChatListReq listReq = new ChatListReq();
        listReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        listReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        listReq.numItems = String.valueOf(chat_count);
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(ChatListReq.class.getName()).cast(listReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "chats", true);

        et_search_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_search_name.getText().toString().length() > 1)
                    callSearchUserWS();

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_search_name.getText().toString().length() == 1) {
//                    callSearchUserWS();
                    if (rview_chat_list.getVisibility() == View.GONE) {
                        rview_serach_chat.setVisibility(View.VISIBLE);
                    }
                } else if (et_search_name.getText().toString().length() < 1) {
                    rview_chat_list.setVisibility(View.VISIBLE);
                    rview_serach_chat.setVisibility(View.GONE);
                }
            }
        });


    }

    private void callSearchUserWS() {
        SearchChatReq req = new SearchChatReq();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        req.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        req.keyword = et_search_name.getText().toString();
        req.numItems = "0";

        try {
            obj = Class.forName(SearchChatReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "search_chats", false);

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
    public void onResponseSuccess(Object objectResponse, Object objectRequest, final int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getActivity(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:

                            chatListPojo = gson.fromJson(jsonString, ChatListPojo.class);
//                            if (chatListPojo.getCommunity().size() > 10) {
//                                t.setVisibility(View.VISIBLE);
//                            }
                            manager = new LinearLayoutManager(getActivity());
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_chat_list.setLayoutManager(manager);
                            responseBeans.addAll(chatListPojo.getResponse());
                            chatListAdapter = new ChatListAdapter(getActivity(), responseBeans);
                            rview_chat_list.setAdapter(chatListAdapter);

                            chatListAdapter.setOnItemClickListener(new ChatListAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.rv_chat:
                                            Intent intent = new Intent(getActivity(), PersonalChatActivity.class);
                                            intent.putExtra("Key_name", responseBeans.get(position).getFirst_name() + " " + responseBeans.get(position).getLast_name());
                                            intent.putExtra("Key_ReceiverId", responseBeans.get(position).getReceiver_id());
                                            intent.putExtra("Key_SenderId", responseBeans.get(position).getSender_id());
                                            startActivity(intent);
                                            break;
                                    }
                                }
                            });
                            break;
                        case 2:

                            rview_serach_chat.setVisibility(View.VISIBLE);
                            rview_chat_list.setVisibility(View.GONE);

                            chatSearchPojo = gson.fromJson(jsonString, ChatSearchPojo.class);
//                            if (chatListPojo.getCommunity().size() > 10) {
//                                t.setVisibility(View.VISIBLE);
//                            }
                            manager = new LinearLayoutManager(getActivity());
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_serach_chat.setLayoutManager(manager);
                            responseBeans_search = chatSearchPojo.getResponse();
//                            responseBeans_search.addAll(chatSearchPojo.getResponse());
                            adapter = new SearchChatAdapter(getActivity(), responseBeans_search);
                            rview_serach_chat.setAdapter(adapter);

                            adapter.setOnItemClickListener(new SearchChatAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.rv_chat:
                                            Intent intent = new Intent(getActivity(), PersonalChatActivity.class);
                                            intent.putExtra("Key_name", responseBeans_search.get(position).getFirst_name() + " " + responseBeans.get(position).getLast_name());
                                            intent.putExtra("Key_ReceiverId", responseBeans_search.get(position).getReceiver_id());
                                            intent.putExtra("Key_SenderId", responseBeans_search.get(position).getSender_id());
                                            startActivity(intent);

                                            break;
                                    }
                                }
                            });
                            break;

                    }
                } else {
                    Common.showToast(getActivity(), object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected View getFragmentView() {
        View view = getLayoutInflater().inflate(R.layout.chat_list_activity, null);
        return view;
    }


}
