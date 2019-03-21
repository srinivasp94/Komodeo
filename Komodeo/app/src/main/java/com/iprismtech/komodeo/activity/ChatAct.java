package com.iprismtech.komodeo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractFragment;

public class ChatAct extends BaseAbstractFragment {
    ImageView iv_chatback;

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

}
