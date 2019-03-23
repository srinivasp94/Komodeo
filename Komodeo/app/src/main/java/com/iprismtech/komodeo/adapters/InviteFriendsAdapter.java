package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.responses.FriendList;

import java.util.ArrayList;

public class InviteFriendsAdapter extends RecyclerView.Adapter<InviteFriendsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FriendList> list;

    public InviteFriendsAdapter(Context context, ArrayList<FriendList> list) {
        this.context = context;
        this.list = list;
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_invite_friends, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final FriendList member = list.get(i);
        /*Picasso.with(context).load(Constants.BASE_IMAGE_URL + member.).
                error(R.drawable.manager).into(viewHolder.imgPic);*/
        viewHolder.txtName.setText(member.firstName + " " + member.lastName);
        viewHolder.txt_subject.setText(member.events);
        viewHolder.txt_mutualfriends.setText(member.friendRequests);

        if (!member.isInvite()) {
            viewHolder.rl_bgr.setBackgroundColor(member.isInvite() ? Color.CYAN : Color.WHITE);
        } else {
            viewHolder.rl_bgr.setBackgroundColor(member.isInvite() ? Color.CYAN : Color.WHITE);
        }

        viewHolder.txtInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member.setInvite(!member.isInvite());
                if (!member.isInvite()) {
                    viewHolder.rl_bgr.setBackgroundColor(member.isInvite() ? Color.CYAN : Color.WHITE);
                } else {
                    viewHolder.rl_bgr.setBackgroundColor(member.isInvite() ? Color.CYAN : Color.WHITE);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPic, imgMessage;
        TextView txtName, txt_subject, txt_mutualfriends, txtInvite;
        RelativeLayout rl_bgr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPic = itemView.findViewById(R.id.imgPic);
            txtName = itemView.findViewById(R.id.txt_name3);
            txt_subject = itemView.findViewById(R.id.txt_subject3);
            txt_mutualfriends = itemView.findViewById(R.id.txt_mutualfriends3);
            txtInvite = itemView.findViewById(R.id.txtInvite);
            rl_bgr = itemView.findViewById(R.id.rl_bgr);
//            txtInvite.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public ArrayList<FriendList> invitedFirends() {
        ArrayList<FriendList> invitedfrndLists = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isInvite())
                invitedfrndLists.add(list.get(i));
        }

        return invitedfrndLists;

    }
}
