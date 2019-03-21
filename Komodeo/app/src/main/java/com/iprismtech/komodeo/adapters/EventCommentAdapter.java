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
import com.iprismtech.komodeo.responses.EventComment;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventCommentAdapter extends RecyclerView.Adapter<EventCommentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<EventComment> list;

    public EventCommentAdapter(Context context, ArrayList<EventComment> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment_tutor, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EventComment member = list.get(i);
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + member.profilePic).
                error(R.drawable.manager).into(viewHolder.imgPic);
        viewHolder.txtName.setText(member.firstName + " " + member.lastName);
        viewHolder.txtDate.setText(member.createdOn);
        viewHolder.txtComment.setText(member.comment);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPic;
        TextView txtName, txtDate, txtComment;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPic = itemView.findViewById(R.id.imgPic);
            txtName = itemView.findViewById(R.id.txtName);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtComment = itemView.findViewById(R.id.txtComment);
        }
    }
}
