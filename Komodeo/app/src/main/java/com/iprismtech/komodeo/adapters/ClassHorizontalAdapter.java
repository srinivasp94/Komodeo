package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.responses.ClassList;

import java.util.ArrayList;

public class ClassHorizontalAdapter extends RecyclerView.Adapter<ClassHorizontalAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ClassList> classLists;

    public ClassHorizontalAdapter(Context context, ArrayList<ClassList> classLists) {
        this.context = context;
        this.classLists = classLists;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_class_selector, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ClassList model = classLists.get(i);
        viewHolder.txt_classes.setText(model.title);
        if (!TextUtils.isEmpty(model.colorCode))
            viewHolder.rlLayout.setBackgroundColor(Color.parseColor("#" + model.colorCode));
    }

    @Override
    public int getItemCount() {
        return classLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_classes;
        RelativeLayout rlLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_classes = itemView.findViewById(R.id.txt_classes);
            rlLayout = itemView.findViewById(R.id.rlLayout);
            rlLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
