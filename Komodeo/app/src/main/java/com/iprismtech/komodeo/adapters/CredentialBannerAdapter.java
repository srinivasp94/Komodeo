package com.iprismtech.komodeo.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.pojo.UserRatingPojo;
import com.iprismtech.komodeo.utils.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by USER on 01-05-2018.
 */

public class CredentialBannerAdapter extends PagerAdapter {

    private Context context;

    UserRatingPojo bannersPojo;

    LayoutInflater layoutInflater;

    public CredentialBannerAdapter(Context context, UserRatingPojo bannersPojo) { /*JSONArray bannerJSONArray*/
        this.context = context;

        this.bannersPojo = bannersPojo;
        //layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {

        return bannersPojo.getResponse().getCredentials().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.credentials_item_layout, container, false);
        try {
            assert view != null;
            ImageView bannerImageView = view.findViewById(R.id.iv_images);
            Picasso.with(context).load(Constants.BASE_IMAGE_URL + bannersPojo.getResponse().getCredentials().get(position).getImage()).
                    error(R.drawable.manager).into(bannerImageView);

//            Util.getInstance().setGlideImage(context, bannerImageView, bannersPojo.getResponse().get(position).getBanner(), false);
            container.addView(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
