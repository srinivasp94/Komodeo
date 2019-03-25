package com.iprismtech.komodeo.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.NotificationsAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.NotificationsPojo;
import com.iprismtech.komodeo.request.NotoficationsReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

public class NotificationsActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private RecyclerView rview_notifications;
    private LinearLayoutManager manager;
    private NotificationsPojo notificationsPojo;
    private NotificationsAdapter adapter;
    private Object obj;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.notification_layout, null);
        return view;
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        rview_notifications = findViewById(R.id.rview_notifications);


        NotoficationsReq notoficationsReq = new NotoficationsReq();
        notoficationsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(NotoficationsReq.class.getName()).cast(notoficationsReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "notifications", true);

    }


    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(NotificationsActivity.this, "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:

                            notificationsPojo = gson.fromJson(jsonString, NotificationsPojo.class);
//                                if (commentsPostPojo.getResponse().size() > 10) {
//                                    tv_load_more.setVisibility(View.VISIBLE);
//                                }
                            manager = new LinearLayoutManager(NotificationsActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_notifications.setLayoutManager(manager);
                            // responseBeans.addAll(commentsPostPojo.getResponse());
                            adapter = new NotificationsAdapter(NotificationsActivity.this, notificationsPojo);
                            rview_notifications.setAdapter(adapter);
                            break;

                    }
                } else {
                    Common.showToast(NotificationsActivity.this, object.optString("message"));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
