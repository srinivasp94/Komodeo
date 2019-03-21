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
import com.iprismtech.komodeo.activity.SignupAct;
import com.iprismtech.komodeo.pojo.UniversitiSearchPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

public class UniversitySearchAdapter extends RecyclerView.Adapter<UniversitySearchAdapter.ViewHolder> {
    private Context context;
    private UniversitiSearchPojo universitiSearchPojo;

    public UniversitySearchAdapter(SignupAct signupAct, UniversitiSearchPojo universitiSearchPojo) {
        this.context = signupAct;
        this.universitiSearchPojo = universitiSearchPojo;
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
    public UniversitySearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_university_search, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversitySearchAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_university_name.setText(universitiSearchPojo.getResponse().get(i).getTitle());
        viewHolder.tv_location.setText(universitiSearchPojo.getResponse().get(i).getLocation());
        Picasso.with(context).load(Constants.BASE_IMAGE_URL + universitiSearchPojo.getResponse().get(i).getIcon()).error(R.drawable.addclass).into(viewHolder.iv_logo);

    }

    @Override
    public int getItemCount() {
        return universitiSearchPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_logo;
        TextView tv_university_name, tv_location;
        LinearLayout ll_item_uni_search;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_logo = itemView.findViewById(R.id.iv_logo);
            tv_university_name = itemView.findViewById(R.id.tv_university_name);
            tv_location = itemView.findViewById(R.id.tv_location);
            ll_item_uni_search = itemView.findViewById(R.id.ll_item_uni_search);
            ll_item_uni_search.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
