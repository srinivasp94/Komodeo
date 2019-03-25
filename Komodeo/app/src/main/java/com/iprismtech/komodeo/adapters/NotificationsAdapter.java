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
import com.iprismtech.komodeo.activity.NotificationsActivity;
import com.iprismtech.komodeo.pojo.NotificationsPojo;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private Context context;
    private NotificationsPojo notificationsPojo;

    public NotificationsAdapter(NotificationsActivity notificationsActivity, NotificationsPojo notificationsPojo) {
        this.context = notificationsActivity;
        this.notificationsPojo = notificationsPojo;
    }

    @NonNull
    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.noti_adapter_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.txt_noti_title.setText(notificationsPojo.getResponse().get(i).getTitle());
        viewHolder.tv_noti_disc.setText(notificationsPojo.getResponse().get(i).getDescription());
       // viewHolder.tv_noti_time.setText(notificationsPojo.getResponse().get(i).getDescription());

    }

    @Override
    public int getItemCount() {
        return notificationsPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_noti_img;
        TextView txt_noti_title, tv_noti_time, tv_noti_disc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           // iv_noti_img = itemView.findViewById(R.id.iv_noti_img);
            txt_noti_title = itemView.findViewById(R.id.txt_noti_title);
            tv_noti_time = itemView.findViewById(R.id.tv_noti_time);
            tv_noti_disc = itemView.findViewById(R.id.tv_noti_disc);

        }
    }
}
