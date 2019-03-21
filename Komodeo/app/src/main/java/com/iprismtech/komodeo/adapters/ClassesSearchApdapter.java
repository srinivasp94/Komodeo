package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.pojo.SearchClassesPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

public class ClassesSearchApdapter extends RecyclerView.Adapter<ClassesSearchApdapter.ViewHolder> {
    private Context context;
    private SearchClassesPojo searchClassesPojo;

    public ClassesSearchApdapter(FragmentActivity activity, SearchClassesPojo searchClassesPojo) {
        this.context = activity;
        this.searchClassesPojo = searchClassesPojo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classes_search, viewGroup, false);
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tv_class_code.setText(searchClassesPojo.getResponse().get(i).getTitle());
        viewHolder.tv_sub_class.setText(searchClassesPojo.getResponse().get(i).getSubject_name());
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + searchClassesPojo.getResponse().get(i).getIcon()).error(R.drawable.addclass).into(viewHolder.iv_item_classes_search);

    }

    @Override
    public int getItemCount() {
        return searchClassesPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_item_classes_search;
        TextView tv_class_code, tv_sub_class;
        LinearLayout ll_item_class_search;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_item_classes_search = itemView.findViewById(R.id.iv_item_classes_search);
            tv_class_code = itemView.findViewById(R.id.tv_class_code);
            tv_sub_class = itemView.findViewById(R.id.tv_sub_class);
            ll_item_class_search = itemView.findViewById(R.id.ll_item_class_search);
            ll_item_class_search.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
