package com.iprismtech.komodeo;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubjectsRecyclerAdapter extends RecyclerView.Adapter<SubjectsRecyclerAdapter.AssignedAdapterView> {

    private Context context;
    int[] icons;


    LayoutInflater inflater;


    public SubjectsRecyclerAdapter(Context context, int[] icons) {
        this.context = context;
        this.icons = icons;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public SubjectsRecyclerAdapter.AssignedAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.subjects_layout, parent, false);
        return new SubjectsRecyclerAdapter.AssignedAdapterView(itemView);
    }


    public void onBindViewHolder(SubjectsRecyclerAdapter.AssignedAdapterView holder, int position) {
        try {
            holder.iv_sub.setImageResource(icons[position]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }

    public class AssignedAdapterView extends RecyclerView.ViewHolder {
        TextView tv_arabic, tv_english;
        ImageView iv_sub;
        CardView cv_assigned_bookingdetails;
        LinearLayout ll;
        String nationalityID;

        CheckBox checkBox;

        public AssignedAdapterView(final View itemView) {
            super(itemView);


            iv_sub = itemView.findViewById(R.id.iv_sub);


        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
