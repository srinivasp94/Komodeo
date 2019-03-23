package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.pojo.UserClassesPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CurrentGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list;
    private UserClassesPojo userClassesPojo;
    LayoutInflater inflater;

    public CurrentGridAdapter(Context context, UserClassesPojo list) {
        this.context = context;
        this.userClassesPojo = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return userClassesPojo.getResponse().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if (holder == null) {
//            convertView = inflater.inflate(R.layout.item_current, parent, false);
//            holder = new ViewHolder();
//
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        holder.count.setText("");
//        holder.title.setText("");
//        holder.ll_item_user_class.setBackgroundColor(Color.parseColor("#cccccc"));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_current, null);
        TextView count, title;
        LinearLayout ll_item_user_class;
        ImageView iv_sub_img;
        ll_item_user_class = convertView.findViewById(R.id.ll_item_user_class);
        title = convertView.findViewById(R.id.tv_sub_code);
//        count = convertView.findViewById(R.id.tv_new_events_count);
//        iv_sub_img = convertView.findViewById(R.id.iv_sub_img);

        title.setText(userClassesPojo.getResponse().get(position).getTitle());
      //  Picasso.with(context).load(Constants.BASE_IMAGE_URL + userClassesPojo.getResponse().get(position).getIcon()).error(R.drawable.addclass).into(iv_sub_img);
        ll_item_user_class.setBackgroundColor(Color.parseColor("#" + userClassesPojo.getResponse().get(position).getColor_code()));

        return convertView;
    }

    public class ViewHolder {
        TextView count, title;
        LinearLayout ll_item_user_class;


    }
}
