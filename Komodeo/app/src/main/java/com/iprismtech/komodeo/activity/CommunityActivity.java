package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.CommunityMembersAdapter;
import com.iprismtech.komodeo.adapters.SearchCommunityMembersAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.CommunityMembersPojo;
import com.iprismtech.komodeo.pojo.SearchCommunityPojo;
import com.iprismtech.komodeo.request.CommunityMembersReq;
import com.iprismtech.komodeo.request.FriendRequstReq;
import com.iprismtech.komodeo.request.SearchUsesReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;

public class CommunityActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private LinearLayout ll_discussiontab, ll_currenttab_events, ll_communitymembers;
    private TextView tv_course_name, tv_load_more;
    private EditText et_search_name;
    private ImageView iv_notification;
    private RecyclerView rview_comminty_members, rview_serach_comminty_members;
    private LinearLayoutManager manager;
    private CommunityMembersAdapter communityMembersAdapter;
    private SearchCommunityMembersAdapter searchCommunityMembersAdapter;
    private Object obj;
    private int discussions_count = 0;
    private String course_name, course_ID;
    private CommunityMembersPojo communityMembersPojo;
    private SearchCommunityPojo searchCommunityPojo;
    private List<CommunityMembersPojo.CommunityBean> responseBeans;
    private List<SearchCommunityPojo.ResponseBean> search_responseBeans;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_load_more:
                discussions_count = discussions_count + 10;
                CommunityMembersReq communityMembersReq = new CommunityMembersReq();
                communityMembersReq.classId = course_ID;
                communityMembersReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                communityMembersReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
                communityMembersReq.count = String.valueOf(discussions_count);
                //flatListRequest.building_id="4";

                try {
                    obj = Class.forName(CommunityMembersReq.class.getName()).cast(communityMembersReq);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(this).callPostServices(obj, 1, "community", true);
                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.community_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(CommunityActivity.this, "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            communityMembersPojo = gson.fromJson(jsonString, CommunityMembersPojo.class);
                            if (communityMembersPojo.getCommunity().size() > 10) {
                                tv_load_more.setVisibility(View.VISIBLE);
                            }
                            manager = new LinearLayoutManager(CommunityActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_comminty_members.setLayoutManager(manager);
                            responseBeans.addAll(communityMembersPojo.getCommunity());
                            communityMembersAdapter = new CommunityMembersAdapter(CommunityActivity.this, responseBeans);
                            rview_comminty_members.setAdapter(communityMembersAdapter);
                            communityMembersAdapter.setOnItemClickListener(new CommunityMembersAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.iv_chat:

                                            break;
                                        case R.id.iv_add_frd:
                                            callSendFriendReqWs(position);



                                            break;
                                    }
                                }
                            });
                            break;
                        case 2:
                            searchCommunityPojo = gson.fromJson(jsonString, SearchCommunityPojo.class);
                            rview_serach_comminty_members.setVisibility(View.VISIBLE);
                            ll_communitymembers.setVisibility(View.GONE);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(CommunityActivity.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_serach_comminty_members.setLayoutManager(layoutManager);

                            if (searchCommunityPojo.getResponse() != null && searchCommunityPojo.getResponse().size() > 0) {
                                search_responseBeans = searchCommunityPojo.getResponse();
                                SearchCommunityMembersAdapter adapter = new SearchCommunityMembersAdapter(CommunityActivity.this, search_responseBeans);
                                rview_serach_comminty_members.setAdapter(adapter);
                            }
                            break;
                        case 3:
                            Toast.makeText(this, "Friend Request Sent Successfully", Toast.LENGTH_SHORT).show();

                            break;
                    }
                } else {
                    Common.showToast(CommunityActivity.this, object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void callSendFriendReqWs(int position) {
        FriendRequstReq friendRequstReq = new FriendRequstReq();
        friendRequstReq.friendId = communityMembersPojo.getCommunity().get(position).getId();
        friendRequstReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        friendRequstReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(FriendRequstReq.class.getName()).cast(friendRequstReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 3, "send_friend_request", true);

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_discussiontab.setOnClickListener(this);
        ll_currenttab_events.setOnClickListener(this);
        tv_load_more.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        responseBeans = new ArrayList<>();
        Intent intent = getIntent();
        course_name = intent.getStringExtra("Key_CurseName");
        course_ID = intent.getStringExtra("Key_CourseID");

        ll_discussiontab = findViewById(R.id.ll_discussiontab);
        ll_currenttab_events = findViewById(R.id.ll_currenttab_events);
        tv_course_name = findViewById(R.id.tv_course_name);
        et_search_name = findViewById(R.id.et_search_name);
        iv_notification = findViewById(R.id.iv_notification);
        rview_comminty_members = findViewById(R.id.rview_comminty_members);
        tv_load_more = findViewById(R.id.tv_load_more);
        ll_communitymembers = findViewById(R.id.ll_communitymembers);
        rview_serach_comminty_members = findViewById(R.id.rview_serach_comminty_members);

        tv_course_name.setText(course_name);


        CommunityMembersReq communityMembersReq = new CommunityMembersReq();
        communityMembersReq.classId = course_ID;
        communityMembersReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        communityMembersReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        communityMembersReq.count = String.valueOf(discussions_count);
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(CommunityMembersReq.class.getName()).cast(communityMembersReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "community", true);


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
                    if (ll_communitymembers.getVisibility() == View.GONE) {
                        rview_serach_comminty_members.setVisibility(View.VISIBLE);

                    }
                }
            }
        });

    }

    private void callSearchUserWS() {
        SearchUsesReq req = new SearchUsesReq();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        ;
        req.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        req.keyword = et_search_name.getText().toString();
        try {
            obj = Class.forName(SearchUsesReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "search_users", false);
    }
}
