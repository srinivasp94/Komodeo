package com.iprismtech.komodeo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.ChatListAdapter;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.pojo.ChattingListPojo;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;

public class ChatAct extends BaseAbstractFragment implements RetrofitResponseListener {
    private ImageView iv_chatback;
    private RecyclerView rview_chat;
    private LinearLayoutManager manager;
    private ChatListAdapter chatListAdapter;
    private ChattingListPojo chattingListPojo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
    }

    @Override
    protected View getFragmentView() {
        View view = getLayoutInflater().inflate(R.layout.chat_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }
}
