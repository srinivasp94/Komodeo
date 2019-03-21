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
import com.iprismtech.komodeo.activity.UserPostCommentActivity;
import com.iprismtech.komodeo.pojo.CommentsPostPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {
    private Context context;
    private List<CommentsPostPojo.ResponseBean> responseBeans;

    public CommentsAdapter(UserPostCommentActivity userPostCommentActivity, List<CommentsPostPojo.ResponseBean> responseBeans) {
        this.context = userPostCommentActivity;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_post_comment, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_posted_comment.setText(responseBeans.get(i).getComment());
        viewHolder.tv_post_name.setText(responseBeans.get(i).getFirst_name() + " " + responseBeans.get(i).getLast_name());
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + responseBeans.get(i).getProfile_pic()).error(R.drawable.ic_no_image).into(viewHolder.iv_posted_person);
    }

    @Override
    public int getItemCount() {
        return responseBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_posted_comment, tv_post_name;
        ImageView iv_posted_person;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_posted_comment = itemView.findViewById(R.id.tv_posted_comment);
            tv_post_name = itemView.findViewById(R.id.tv_post_name);
            iv_posted_person = itemView.findViewById(R.id.iv_posted_person);
        }
    }
}
