package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.activity.PersonalChatActivity;
import com.iprismtech.komodeo.pojo.PersonalChatPojo;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private Context context;
    List<PersonalChatPojo.ResponseBean> responseBeans;
    private LayoutInflater inflater;


    public ChatAdapter(PersonalChatActivity context, List<PersonalChatPojo.ResponseBean> responseBeans) {
        this.context = context;
        this.responseBeans = responseBeans;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return responseBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageViewHolder holder = new MessageViewHolder();
        if (responseBeans.get(position).getUser_type().equalsIgnoreCase("receiver")) {
            convertView = inflater.inflate(R.layout.their_message, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.txt_message);
            convertView.setTag(holder);
            holder.messageBody.setText(responseBeans.get(position).getMessage());
        } else {
            convertView = inflater.inflate(R.layout.mymessage, null);
            holder.messageBody = (TextView) convertView.findViewById(R.id.my_txt_message);
            convertView.setTag(holder);
            holder.messageBody.setText(responseBeans.get(position).getMessage());
        }


//        if (responseBeans.get(i).getUser_type().equalsIgnoreCase("receiver")) {
//            viewHolder.rl_other_end.setVisibility(View.VISIBLE);
//            viewHolder.message_body_their.setText(responseBeans.get(i).getMessage());
//            viewHolder.message_body_my.setVisibility(View.GONE);
//            Picasso.with(context).load(Constants.BASE_IMAGE_URL + responseBeans.get(i).getProfile_pic()).error(R.drawable.ic_no_image).into(viewHolder.iv_chatprofile);
//
//        } else {
//            viewHolder.rl_other_end.setVisibility(View.GONE);
//            viewHolder.message_body_my.setVisibility(View.VISIBLE);
//            viewHolder.message_body_my.setText(responseBeans.get(i).getMessage());
//
//        }

        return convertView;
    }

    public class MessageViewHolder {
        public TextView messageBody;

    }
}
