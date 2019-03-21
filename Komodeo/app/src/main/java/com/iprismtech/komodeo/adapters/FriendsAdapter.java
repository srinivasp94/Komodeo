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
import com.iprismtech.komodeo.responses.FriendList;

import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FriendList> list;

    public FriendsAdapter(Context context, ArrayList<FriendList> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_friends, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FriendList member = list.get(i);
        /*Picasso.with(context).load(Constants.BASE_IMAGE_URL + member.).
                error(R.drawable.manager).into(viewHolder.imgPic);*/
        viewHolder.txtName.setText(member.firstName + " " + member.lastName);
        viewHolder.txt_subject.setText(member.events);
        viewHolder.txt_mutualfriends.setText(member.friendRequests);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPic, imgMessage;
        TextView txtName, txt_subject, txt_mutualfriends;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPic = itemView.findViewById(R.id.imgPic);
            imgMessage = itemView.findViewById(R.id.imgMessage);
            txtName = itemView.findViewById(R.id.txt_name);
            txt_subject = itemView.findViewById(R.id.txt_subject);
            txt_mutualfriends = itemView.findViewById(R.id.txt_mutualfriends);
            imgMessage.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
