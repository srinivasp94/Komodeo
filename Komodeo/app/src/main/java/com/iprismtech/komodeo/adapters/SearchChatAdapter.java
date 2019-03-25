package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.pojo.ChatSearchPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchChatAdapter extends RecyclerView.Adapter<SearchChatAdapter.ViewHolder> {
    private Context context;
    private List<ChatSearchPojo.ResponseBean> responseBeans;

    public SearchChatAdapter(FragmentActivity activity, List<ChatSearchPojo.ResponseBean> responseBeans) {
        this.context = activity;
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chatlist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + responseBeans.get(i).getProfile_pic()).error(R.drawable.ic_no_image).into(viewHolder.iv_profile);
        viewHolder.txt_chat_name.setText(responseBeans.get(i).getFirst_name() + " " + responseBeans.get(i).getLast_name());
        viewHolder.tv_last_msg.setText(responseBeans.get(i).getMessage());
        viewHolder.tv_last_chat_time.setText(responseBeans.get(i).getCreated_on());

    }

    @Override
    public int getItemCount() {
        return responseBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_profile;
        TextView txt_chat_name, tv_last_msg, tv_last_chat_time;
        RelativeLayout rv_chat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_profile);
            txt_chat_name = itemView.findViewById(R.id.txt_chat_name);
            tv_last_msg = itemView.findViewById(R.id.tv_last_msg);
            tv_last_chat_time = itemView.findViewById(R.id.tv_last_chat_time);
            rv_chat = itemView.findViewById(R.id.rv_chat);

            rv_chat.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }

}
