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
import com.iprismtech.komodeo.activity.FriendRequestsActivity;
import com.iprismtech.komodeo.pojo.FriendRequestsPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

public class FriendRequestsAdapter extends RecyclerView.Adapter<FriendRequestsAdapter.ViewHolder> {
    private Context context;
    private FriendRequestsPojo friendRequestsPojo;

    public FriendRequestsAdapter(FriendRequestsActivity friendRequestsActivity, FriendRequestsPojo friendRequestsPojo) {
        this.context = friendRequestsActivity;
        this.friendRequestsPojo = friendRequestsPojo;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_frd_reqs, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //  Picasso.with(context).load(Constants.BASE_IMAGE_URL + friendRequestsPojo.getResponse().get(i).get).error(R.drawable.ic_no_image).into(viewHolder.imgPic);
        viewHolder.txtName.setText(friendRequestsPojo.getResponse().get(i).getFirst_name() + " " + friendRequestsPojo.getResponse().get(i).getLast_name());
    }

    @Override
    public int getItemCount() {
        return friendRequestsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPic;
        TextView txtName, txt_confirm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPic = itemView.findViewById(R.id.imgPic);
            txtName = itemView.findViewById(R.id.txtName);
            txt_confirm = itemView.findViewById(R.id.txt_confirm);
            txt_confirm.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
