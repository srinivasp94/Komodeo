package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.pojo.UserRatingPojo;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsAdapterView> {
    private Context context;
    UserRatingPojo userRatingPojo;
    LayoutInflater inflater;
    String[] text = {"John Doe", "Chaush"};


    public ReviewsAdapter(Context context, UserRatingPojo userRatingPojo) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.userRatingPojo = userRatingPojo;

        //   this.text = text;
    }

    @Override
    public ReviewsAdapter.ReviewsAdapterView onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.reviews_item_layout, parent, false);
        return new ReviewsAdapter.ReviewsAdapterView(itemView);
    }


    public void onBindViewHolder(ReviewsAdapter.ReviewsAdapterView holder, int position) {
        try {


            holder.txt_name.setText(userRatingPojo.getResponse().getReviews().get(position).getFirst_name());
            holder.txt_comment.setText(userRatingPojo.getResponse().getReviews().get(position).getReviews());
            Picasso.with(context).load(Constants.BASE_IMAGE_URL + userRatingPojo.getResponse().getReviews().get(position).getProfile_pic()).
                    error(R.drawable.manager).into(holder.iv_profile);

            holder.reviews_ratingbar.setRating(Float.parseFloat(userRatingPojo.getResponse().getReviews().get(position).getRatings()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return userRatingPojo.getResponse().getReviews().size();
    }

    public class ReviewsAdapterView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_name, txt_comment, tvcontact_name, tvcontact_number;
        ImageView iv_profile;
        RatingBar reviews_ratingbar;
        CardView cv_assigned_bookingdetails;
        LinearLayout ll;
        String nationalityID;
        CheckBox checkBox;
        TextView remove_btn;

        /*iv_profile
txt_name
txt_comment
reviews_ratingbar*/

        public ReviewsAdapterView(final View itemView) {

            super(itemView);

            txt_name = itemView.findViewById(R.id.txt_name);
            iv_profile = itemView.findViewById(R.id.iv_profile);


            txt_comment = itemView.findViewById(R.id.txt_comment);

            reviews_ratingbar = itemView.findViewById(R.id.reviews_ratingbar);

//            remove_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
