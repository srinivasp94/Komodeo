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
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + responseBeans.get(i).getProfile_pic()).error(R.drawable.ic_no_image).into(viewHolder.iv_community_post_layout);

        //viewHolder.txt_mutual_friends.setText();
    }

    @Override
    public int getItemCount() {
        return responseBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_community_post_layout, iv_add_frd, iv_chat;
        TextView txt_name_community, txt_subject_community, txt_mutual_friends;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_community_post_layout = itemView.findViewById(R.id.iv_community_post_layout);
            txt_name_community = itemView.findViewById(R.id.txt_name);
            txt_subject_community = itemView.findViewById(R.id.txt_subject);
        }
    }
}
