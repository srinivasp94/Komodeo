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
import com.iprismtech.komodeo.responses.EventsList;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<EventsList> list;

    public EventsAdapter(Context context, ArrayList<EventsList> list) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_tutors, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EventsList model = list.get(i);
        if (model.firstName != null || model.lastName != null)
            viewHolder.txt_personName.setText(model.firstName);
        if (model.eventName != null)
            viewHolder.txt_name.setText(model.eventName);
        if (model.maxGroupSize != null)
            viewHolder.txtMax.setText("Max " + model.maxGroupSize);
        viewHolder.txt_subject.setText(model.eventDate + " " + model.startTime + " " + model.endTime);
        if (model.locationAddress != null)
            viewHolder.txt_mutual_friends.setText(model.locationAddress);
        if (model.totalPrice != null)
            viewHolder.txt_price.setText("$ " + model.totalPrice);
        if (model.ratings != null)
            viewHolder.txtRating.setText(model.ratings);
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + model.profilePic).
                error(R.drawable.boy).into(viewHolder.iv_post_layout);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_post_layout, create_tutor_event;
        TextView txt_name, txt_subject, txt_mutual_friends,
                txt_price, txt_personName, txtMax, txtRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_post_layout = itemView.findViewById(R.id.iv_post_layout);
            create_tutor_event = itemView.findViewById(R.id.create_tutor_event);
            create_tutor_event.setVisibility(View.GONE);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_subject = itemView.findViewById(R.id.txt_subject);
            txt_mutual_friends = itemView.findViewById(R.id.txt_mutual_friends);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_personName = itemView.findViewById(R.id.txt_personName);
            txtMax = itemView.findViewById(R.id.txtMax);
            txtRating = itemView.findViewById(R.id.txtRating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
