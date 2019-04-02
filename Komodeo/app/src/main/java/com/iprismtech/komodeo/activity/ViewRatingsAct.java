package com.iprismtech.komodeo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.CredentialBannerAdapter;
import com.iprismtech.komodeo.adapters.ReviewsAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.GiveRatingPojo;
import com.iprismtech.komodeo.pojo.UserRatingPojo;
import com.iprismtech.komodeo.request.GiveRatingReq;
import com.iprismtech.komodeo.request.SimpleReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.Constants;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.List;

public class ViewRatingsAct extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    ImageView iv_back;
    ImageView iv_back_ratings;
    RatingBar ratingbar;
    private Object obj;
    ImageView iv_profile_ratings;
    AlertDialog alert;
    CredentialBannerAdapter credentialBannerAdapter;
    GiveRatingPojo giveRatingPojo;
    String rating;
    List jsonArray;
    TextView txtoneratings, txt_tworatings, txt_threeratings, txt_fourratings, txt_fiveratings;
    final String[] ratingValue = {null};
    RecyclerView rv_reviews;
    EditText edt_reviews;
    TextView txt_viewcredentials;
    UserRatingPojo res;
    ReviewsAdapter reviewsAdapter;
    ImageView iv_submit_rating;
    TextView txt_name,
            txt_designation,
            txt_ratings;
    ImageView iv_profile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.viewrating_layout);


    }

    @Override
    protected View getView() {

        View view = getLayoutInflater().inflate(R.layout.viewrating_layout, null);
        return view;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_submit_rating:

                GiveRatingReq giveRatingReq = new GiveRatingReq();

                if (ratingValue[0] == null) {
                    Toast.makeText(context, "Please give rating", Toast.LENGTH_SHORT).show();


                } else if (giveRatingReq.givenBy == "" && giveRatingReq.givenBy == "null") {
                    Toast.makeText(context, "Please select class", Toast.LENGTH_SHORT).show();
                    /*                    if (etcommentbox.getText().toString().trim().length() == 0) {
                     */

                } else if (edt_reviews.getText().toString().trim().length() == 0) {
                    Toast.makeText(context, "Please give review", Toast.LENGTH_SHORT).show();

                } else {

                    try {

                        giveRatingReq.userId = SharedPrefsUtils.getInstance(ViewRatingsAct.this).getId();

                        giveRatingReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);

                        giveRatingReq.givenBy = "2";

                        giveRatingReq.ratings = ratingValue[0];

                        giveRatingReq.reviews = edt_reviews.getText().toString();

                        obj = Class.forName(GiveRatingReq.class.getName()).cast(giveRatingReq);
                        new RetrofitRequester(this).callPostServices(obj, 3, "give_ratings", true);


                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.txt_viewcredentials:
                opeDialog();
                break;
        }

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse == "") {

            Common.showToast(ViewRatingsAct.this, "Please Try Again");

        } else {

            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 2:
                            res = Common.getSpecificDataObject(objectResponse, UserRatingPojo.class);
                            reviewsAdapter = new ReviewsAdapter(context, res);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            rv_reviews.setLayoutManager(mLayoutManager);
                            rv_reviews.setItemAnimator(new DefaultItemAnimator());
                            rv_reviews.setAdapter(reviewsAdapter);
                            Common.showToast(ViewRatingsAct.this, jsonObject.optString("message"));
                            txt_name.setText(res.getResponse().getFirst_name() + " " + res.getResponse().getLast_name());
                            txt_designation.setText(res.getResponse().getMajor());
                            Picasso.with(context).load(Constants.BASE_IMAGE_URL + res.getResponse().getProfile_pic()).
                                    error(R.drawable.manager).into(iv_profile_ratings);
                            txtoneratings.setText(res.getResponse().getRating_details().get(0).getRate1());
                            txt_tworatings.setText(res.getResponse().getRating_details().get(0).getRate2());
                            txt_threeratings.setText(res.getResponse().getRating_details().get(0).getRate3());
                            txt_fourratings.setText(res.getResponse().getRating_details().get(0).getRate4());
                            txt_fiveratings.setText(res.getResponse().getRating_details().get(0).getRate5());
                            txt_ratings.setText(res.getResponse().getRatings().toString());
                            break;

                        case 3:

                            GiveRatingPojo giveRatingPojo = Common.getSpecificDataObject(objectResponse, GiveRatingPojo.class);
                            Common.showToast(ViewRatingsAct.this, jsonObject.optString("message"));
                            edt_reviews.setText("");

                            break;

                    }
                } else {
                    Common.showToast(ViewRatingsAct.this, jsonObject.optString("message"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ratingbar = findViewById(R.id.ratingbar);
        rv_reviews = findViewById(R.id.rv_reviews);

        ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String rateValue = String.valueOf(ratingbar.getRating());
                System.out.println("Rate for Module is" + rateValue);
                ratingbar.setRating(Float.parseFloat(rateValue));
                ratingValue[0] = rateValue;
            }
        });
        edt_reviews = findViewById(R.id.edt_reviews);
        iv_submit_rating = findViewById(R.id.iv_submit_rating);

        /* TextView txt_name,
    txt_designation,
            txt_ratings;
    ImageView iv_profile;
*/


        txt_name = findViewById(R.id.txt_name);
        /*txtoneratings, txt_tworatings, txt_threeratings, txt_fourratings, txt_fiveratings*/

        txtoneratings = findViewById(R.id.txtoneratings);

        txt_tworatings = findViewById(R.id.txt_tworatings);
        txt_threeratings = findViewById(R.id.txt_threeratings);
        txt_fourratings = findViewById(R.id.txt_fourratings);
        txt_fiveratings = findViewById(R.id.txt_fiveratings);


        txt_designation = findViewById(R.id.txt_designation);

        txt_ratings = findViewById(R.id.txt_ratings);

        iv_profile_ratings = findViewById(R.id.iv_profile_ratings);

        txt_viewcredentials = findViewById(R.id.txt_viewcredentials);

        iv_back_ratings = findViewById(R.id.iv_back_ratings);

        iv_back_ratings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        SimpleReq simpleReq = new SimpleReq();

        simpleReq.userId = SharedPrefsUtils.getInstance(ViewRatingsAct.this).getId();

        simpleReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {

            obj = Class.forName(SimpleReq.class.getName()).cast(simpleReq);
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "user_ratings", true);
    }


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_submit_rating.setOnClickListener(this);
        txt_viewcredentials.setOnClickListener(this);
    }

    public void opeDialog() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_viewpager_layout, null);
        ViewPager viewPager = view.findViewById(R.id.vp_dialog);
        credentialBannerAdapter = new CredentialBannerAdapter(context, res);
        viewPager.setAdapter(credentialBannerAdapter);
        b.setCancelable(true);
        b.setView(view);
        alert = b.create();
        alert.getWindow().setLayout(200, 300);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(alert.getWindow().getAttributes());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;
        int dialogWindowWidth = (int) (displayWidth * 0.7f);
        int dialogWindowHeight = (int) (displayHeight * 0.7f);
        layoutParams.width = dialogWindowWidth;
        layoutParams.height = dialogWindowHeight;
        // Apply the newly created layout parameters to the alert dialog window
        alert.getWindow().setAttributes(layoutParams);
        alert.show();

    }

}
