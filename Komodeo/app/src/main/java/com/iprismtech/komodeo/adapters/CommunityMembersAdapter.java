package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.activity.CommunityActivity;
import com.iprismtech.komodeo.pojo.CommunityMembersPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CommunityMembersAdapter extends RecyclerView.Adapter<CommunityMembersAdapter.ViewHolder> {
    private Context context;
    private List<CommunityMembersPojo.CommunityBean> responseBeans;

    public CommunityMembersAdapter(CommunityActivity communityActivity, List<CommunityMembersPojo.CommunityBean> responseBeans) {
        this.context = communityActivity;
        this.responseBeans = responseBeans;
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
        // void onItemClickEvent(View view, int position);

    }

    @NonNull
    @Override
    public CommunityMembersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_community, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityMembersAdapter.ViewHolder viewHolder, int i) {
        viewHolder.txt_name_community.setText(responseBeans.get(i).getFirst_name() + " " + responseBeans.get(i).getLast_name());
        if (responseBeans.get(i).getMajor() == null) {
        } else {
            viewHolder.txt_subject_community.setText(responseBeans.get(i).getMajor());
        }

        if (responseBeans.get(i).getFriend().equalsIgnoreCase("yes")) {
            viewHolder.iv_frd_confirmed.setVisibility(View.VISIBLE);
            viewHolder.iv_chat.setVisibility(View.VISIBLE);
            viewHolder.iv_request_sent.setVisibility(View.GONE);
            viewHolder.iv_add_frd.setVisibility(View.GONE);
        } else if (responseBeans.get(i).getFriend_request_sent().equalsIgnoreCase("yes")) {
            viewHolder.iv_frd_confirmed.setVisibility(View.GONE);
            viewHolder.iv_chat.setVisibility(View.GONE);
            viewHolder.iv_request_sent.setVisibility(View.VISIBLE);
            viewHolder.iv_add_frd.setVisibility(View.GONE);
        } else {
            viewHolder.iv_frd_confirmed.setVisibility(View.GONE);
            viewHolder.iv_chat.setVisibility(View.GONE);
            viewHolder.iv_request_sent.setVisibility(View.GONE);
            viewHolder.iv_add_frd.setVisibility(View.VISIBLE);
        }

        Picasso.with(context).load(Constants.BASE_IMAGE_URL + responseBeans.get(i).getProfile_pic()).error(R.drawable.ic_no_image).into(viewHolder.iv_community_post_layout);

        //viewHolder.txt_mutual_friends.setText();
    }

    @Override
    public int getItemCount() {
        return responseBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_community_post_layout, iv_add_frd, iv_chat, iv_request_sent, iv_frd_confirmed;
        TextView txt_name_community, txt_subject_community, txt_mutual_friends;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_community_post_layout = itemView.findViewById(R.id.iv_community_post_layout);
            iv_request_sent = itemView.findViewById(R.id.iv_request_sent);
            iv_frd_confirmed = itemView.findViewById(R.id.iv_frd_confirmed);
            iv_chat = itemView.findViewById(R.id.iv_chat);
            iv_add_frd = itemView.findViewById(R.id.iv_add_frd);
            txt_name_community = itemView.findViewById(R.id.txt_name);
            txt_subject_community = itemView.findViewById(R.id.txt_subject);
            iv_chat.setOnClickListener(this);
            iv_add_frd.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
