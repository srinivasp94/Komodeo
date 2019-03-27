package com.iprismtech.komodeo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.FriendRequestsAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.FriendRequestsPojo;
import com.iprismtech.komodeo.request.AcceptFrdRequestReq;
import com.iprismtech.komodeo.request.FriendRequestsListReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

public class FriendRequestsActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private RecyclerView rview_frd_reqs;
    private LinearLayoutManager manager;
    private Object obj;
    private FriendRequestsPojo friendRequestsPojo;
    private FriendRequestsAdapter adapter;
    private int selected_position;
    private ImageView iv_invite_back;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_frd_requests, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_invite_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        rview_frd_reqs = findViewById(R.id.rview_frd_reqs);
        iv_invite_back = findViewById(R.id.iv_invite_back);

        FriendRequestsListReq listReq = new FriendRequestsListReq();
        listReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        listReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);

        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(FriendRequestsListReq.class.getName()).cast(listReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "friend_requests", true);


    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(FriendRequestsActivity.this, "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:

                            friendRequestsPojo = gson.fromJson(jsonString, FriendRequestsPojo.class);
//                            if (chatListPojo.getCommunity().size() > 10) {
//                                t.setVisibility(View.VISIBLE);
//                            }
                            manager = new LinearLayoutManager(FriendRequestsActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_frd_reqs.setLayoutManager(manager);

                            adapter = new FriendRequestsAdapter(FriendRequestsActivity.this, friendRequestsPojo);
                            rview_frd_reqs.setAdapter(adapter);

                            adapter.setOnItemClickListener(new FriendRequestsAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    selected_position = position;
                                    switch (view.getId()) {
                                        case R.id.rv_chat:
                                            callConfirmFrdWs();
                                            break;
                                    }
                                }
                            });
//                            adapter.setOnItemClickListener(new ChatListAdapter.OnitemClickListener() {
//                                @Override
//                                public void onItemClick(View view, int position) {
//                                    switch (view.getId()) {
//                                        case R.id.rv_chat:
//                                            Intent intent = new Intent(FriendRequestsActivity.this, PersonalChatActivity.class);
//                                            intent.putExtra("Key_name", responseBeans.get(position).getFirst_name() + " " + responseBeans.get(position).getLast_name());
//                                            intent.putExtra("Key_ReceiverId", responseBeans.get(position).getReceiver_id());
//                                            intent.putExtra("Key_SenderId", responseBeans.get(position).getSender_id());
//                                            startActivity(intent);
//                                            break;
//                                    }
//                                }
//                            });
                            break;
                        case 2:
                            Toast.makeText(this, "Accepted Successfully", Toast.LENGTH_SHORT).show();
                            //friendRequestsPojo.getResponse().get(selected_position).set
                            recreate();

                            break;

                    }
                } else {
                    Common.showToast(FriendRequestsActivity.this, object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void callConfirmFrdWs() {
        AcceptFrdRequestReq req = new AcceptFrdRequestReq();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        req.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        req.friendId = friendRequestsPojo.getResponse().get(selected_position).getFriend_id();
        req.status = friendRequestsPojo.getResponse().get(selected_position).getStatus();
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(AcceptFrdRequestReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "accept_friend_request", true);

    }
}
