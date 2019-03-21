package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.activity.CommunityDiscussionsActivity;
import com.iprismtech.komodeo.pojo.DiscussionsPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DiscussionsAdapter extends RecyclerView.Adapter<DiscussionsAdapter.ViewHolder> {
    private Context context;
    private DiscussionsPojo discussionsPojo;
    private List<DiscussionsPojo.ResponseBean> responseBeans;

    public DiscussionsAdapter(CommunityDiscussionsActivity communityDiscussionsActivity, List<DiscussionsPojo.ResponseBean> discussionsPojo) {
        this.responseBeans = discussionsPojo;
        this.context = communityDiscussionsActivity;

    }

    @NonNull
    @Override
    public DiscussionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_discussions, viewGroup, false);
        return new ViewHolder(view);
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
        // void onItemClickEvent(View view, int position);

    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.txt_post.setText(responseBeans.get(i).getDescription());
        viewHolder.tv_post_time.setText(responseBeans.get(i).getTime());
        viewHolder.txt_postername.setText(responseBeans.get(i).getFirst_name() + " " + responseBeans.get(i).getLast_name());
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + responseBeans.get(i).getImage()).error(R.drawable.addclass).into(viewHolder.iv_post_layout);

        viewHolder.tv_count_likes.setText(responseBeans.get(i).getLikes());
        viewHolder.tv_comments_count.setText(responseBeans.get(i).getComments());

        if (responseBeans.get(i).getLiked().equalsIgnoreCase("yes")) {
            viewHolder.iv_likes.setImageResource(R.mipmap.ic_liked);
            viewHolder.iv_post_like.setImageResource(R.drawable.ic_like_blue);
            viewHolder.tv_likestatus.setText("Liked");
        }


    }


    @Override
    public int getItemCount() {
        return responseBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_post_layout, iv_likes, iv_post_like, iv_podt_comment;
        TextView txt_postername, tv_post_time, txt_post, tv_count_likes, tv_comments_count, tv_likestatus;
        LinearLayout ll_submit_like, ll_comment, ll_comments_posted;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_post_layout = itemView.findViewById(R.id.iv_post_layout);
            iv_likes = itemView.findViewById(R.id.iv_likes);
            tv_post_time = itemView.findViewById(R.id.tv_post_time);
            txt_postername = itemView.findViewById(R.id.txt_postername);
            txt_post = itemView.findViewById(R.id.txt_post);
            tv_count_likes = itemView.findViewById(R.id.tv_count_likes);
            tv_comments_count = itemView.findViewById(R.id.tv_comments_count);
            tv_likestatus = itemView.findViewById(R.id.tv_likestatus);
            iv_post_like = itemView.findViewById(R.id.iv_post_like);
            iv_podt_comment = itemView.findViewById(R.id.iv_podt_comment);

            ll_submit_like = itemView.findViewById(R.id.ll_submit_like);
            ll_comment = itemView.findViewById(R.id.ll_comment);
            ll_comments_posted = itemView.findViewById(R.id.ll_comments_posted);

            iv_post_like.setOnClickListener(this);
            iv_podt_comment.setOnClickListener(this);
            iv_likes.setOnClickListener(this);

           // ll_submit_like.setOnClickListener(this);
            ll_comments_posted.setOnClickListener(this);
            ll_comment.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
