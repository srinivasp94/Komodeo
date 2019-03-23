package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.activity.PersonalChatActivity;
import com.iprismtech.komodeo.pojo.PersonalChatPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PersonalChatAdapter extends RecyclerView.Adapter<PersonalChatAdapter.ViewHolder> {
    private Context context;
    private List<PersonalChatPojo.ResponseBean> responseBeans;


    // this adapter is not using actually, this is only test case

    public PersonalChatAdapter(PersonalChatActivity personalChatActivity, List<PersonalChatPojo.ResponseBean> responseBeans) {
        this.context = personalChatActivity;
        this.responseBeans = responseBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_personal_chat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (responseBeans.get(i).getUser_type().equalsIgnoreCase("receiver")) {
            viewHolder.rl_other_end.setVisibility(View.VISIBLE);
            viewHolder.message_body_their.setText(responseBeans.get(i).getMessage());
            viewHolder.message_body_my.setVisibility(View.GONE);
            Picasso.with(context).load(Constants.BASE_IMAGE_URL + responseBeans.get(i).getProfile_pic()).error(R.drawable.ic_no_image).into(viewHolder.iv_chatprofile);

        } else {
            viewHolder.rl_other_end.setVisibility(View.GONE);
            viewHolder.message_body_my.setVisibility(View.VISIBLE);
            viewHolder.message_body_my.setText(responseBeans.get(i).getMessage());

        }
    }

    @Override
    public int getItemCount() {
        return responseBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_chatprofile;
        RelativeLayout rl_other_end;
        TextView message_body_their, message_body_my;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_chatprofile = itemView.findViewById(R.id.iv_chatprofile);
            message_body_their = itemView.findViewById(R.id.message_body_their);
            rl_other_end = itemView.findViewById(R.id.rl_other_end);
            message_body_my = itemView.findViewById(R.id.message_body_my);
        }
    }
}
