package com.iprismtech.komodeo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.FriendsAdapter;
import com.iprismtech.komodeo.adapters.InviteFriendsAdapter;
import com.iprismtech.komodeo.adapters.SearchFriendsAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
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

public class InviteFriendsAct extends BaseAbstractActivity implements RetrofitResponseListener, View.OnClickListener {
    ImageView iv_invite_back;
    private TextView txtInviteContacts;
    private RecyclerView rv_selectFreinds;
    private Object obj;
    private InviteFriendsAdapter adapter;
    private ArrayList<FriendList> list = new ArrayList<>();
    private ArrayList<FriendList> mInviteFriendsList= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.invite_layout);



//        iv_invite_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.invite_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        txtInviteContacts.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        iv_invite_back = findViewById(R.id.iv_invite_back);
        txtInviteContacts = findViewById(R.id.txtInviteContacts);
        rv_selectFreinds = findViewById(R.id.rv_selectFreinds);

        LinearLayoutManager manager = new LinearLayoutManager(InviteFriendsAct.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_selectFreinds.setLayoutManager(manager);

        SimpleReq req = new SimpleReq();
        req.userId = SharedPrefsUtils.getInstance(InviteFriendsAct.this).getId();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {
            obj = Class.forName(SimpleReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "my_friends", true);
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(InviteFriendsAct.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            FriendReq req = Common.getSpecificDataObject(objectResponse, FriendReq.class);
                            list = (ArrayList<FriendList>) req.response;

                            if (list != null && list.size() > 0) {
                                adapter = new InviteFriendsAdapter(InviteFriendsAct.this, list);
                                rv_selectFreinds.setAdapter(adapter);
                            }
                            break;

                    }
                } else {
                    Common.showToast(InviteFriendsAct.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtInviteContacts:
                mInviteFriendsList = adapter.invitedFirends();
                if (mInviteFriendsList != null && mInviteFriendsList.size() > 0) {
                    try {
                        Intent intent = new Intent();
//                            (InviteFriendsAct.this,CreateTutorRequestAct.class);
                        intent.putExtra("Key_Screen", 1);
                        intent.putParcelableArrayListExtra("Key_inviteFrien", mInviteFriendsList);
                        setResult(RESULT_OK, intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }

    }
}
