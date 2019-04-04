package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.FriendsAdapter;
import com.iprismtech.komodeo.adapters.SearchFriendsAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.request.SearchUsesReq;
import com.iprismtech.komodeo.request.SimpleReq;
import com.iprismtech.komodeo.responses.FriendList;
import com.iprismtech.komodeo.responses.FriendReq;
import com.iprismtech.komodeo.responses.SearchFriendList;
import com.iprismtech.komodeo.responses.SearchFriendRes;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class FriendsAct extends BaseAbstractActivity implements RetrofitResponseListener {
    ImageView iv_arrow;
    private EditText edtFriendsSearch;
    private RecyclerView rv_friends, rv_friendRequests;
    private Object obj;
    private ArrayList<FriendList> friendLists = new ArrayList<>();
    private FriendsAdapter friendsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.friends_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        iv_arrow = findViewById(R.id.iv_arrow);
        rv_friends = findViewById(R.id.rv_friends);
        rv_friendRequests = findViewById(R.id.rv_friendRequests);
        edtFriendsSearch = findViewById(R.id.edtFriendsSearch);

        LinearLayoutManager managerComment = new LinearLayoutManager(FriendsAct.this);
        managerComment.setOrientation(LinearLayoutManager.VERTICAL);
        rv_friends.setLayoutManager(managerComment);


        edtFriendsSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtFriendsSearch.getText().toString().length() > 1)
                    callSearchUserWS();

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtFriendsSearch.getText().toString().length() == 1) {
//                    callSearchUserWS();
                    if (rv_friends.getVisibility() == View.GONE) {
                        rv_friends.setVisibility(View.VISIBLE);
                        rv_friendRequests.setVisibility(View.GONE);
                    }
                }
            }
        });


        SimpleReq req = new SimpleReq();
        req.userId = SharedPrefsUtils.getInstance(FriendsAct.this).getId();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {
            obj = Class.forName(SimpleReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "my_friends", true);
    }

    private void callSearchUserWS() {
        SearchUsesReq req = new SearchUsesReq();
        req.userId = SharedPrefsUtils.getInstance(FriendsAct.this).getId();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        req.keyword = edtFriendsSearch.getText().toString();
        try {
            obj = Class.forName(SearchUsesReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "search_users", false);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(FriendsAct.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            FriendReq req = Common.getSpecificDataObject(objectResponse, FriendReq.class);
                            friendLists = (ArrayList<FriendList>) req.response;

                            if (friendLists != null && friendLists.size() > 0) {
                                friendsAdapter = new FriendsAdapter(FriendsAct.this, friendLists);
                                rv_friends.setAdapter(friendsAdapter);
                                friendsAdapter.setOnItemClickListener(new FriendsAdapter.OnitemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        switch (view.getId()) {
                                            case R.id.imgMessage:
                                                Intent intent = new Intent(FriendsAct.this, PersonalChatActivity.class);
                                                intent.putExtra("Key_name", friendLists.get(position).firstName + " " + friendLists.get(position).lastName);
                                                intent.putExtra("Key_ReceiverId", friendLists.get(position).id);
                                                intent.putExtra("Key_SenderId", SharedPrefsUtils.getInstance(FriendsAct.this).getId());
                                                startActivity(intent);

                                                break;
                                        }
                                    }
                                });
                            }
                            break;
                        case 2:
                            ArrayList<SearchFriendList> searchFriendList = new ArrayList<>();
                            SearchFriendRes res = Common.getSpecificDataObject(objectResponse, SearchFriendRes.class);
                            searchFriendList = (ArrayList<SearchFriendList>) res.response;
                            rv_friendRequests.setVisibility(View.VISIBLE);
                            rv_friends.setVisibility(View.GONE);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(FriendsAct.this);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            rv_friendRequests.setLayoutManager(layoutManager);
                            if (searchFriendList != null && searchFriendList.size() > 0) {
                                SearchFriendsAdapter adapter = new SearchFriendsAdapter(FriendsAct.this, searchFriendList);
                                rv_friendRequests.setAdapter(adapter);
                            }
                            break;
                    }
                } else {
                    Common.showToast(FriendsAct.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FriendsAct.this,MainActivity.class));
        finish();
    }
}
