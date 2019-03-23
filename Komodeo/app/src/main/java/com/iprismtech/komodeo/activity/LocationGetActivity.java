package com.iprismtech.komodeo.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.iprismtech.komodeo.MainActivity;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;


public class LocationGetActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener, PlaceSelectionListener {
    private EditText et_location_search;
    int AUTO_COMP_REQ_CODE = 1;
    private double lati, longi;
    private LinearLayout ll_get_current_loc;
    private ImageView iv_back_arrow;
    private RecyclerView locationRecycleView;
    private LinearLayoutManager llm;
    private LatLng latLng;

    private static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));

    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));
    private static final int REQUEST_SELECT_PLACE_DEST = 1000;

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_location_search:
                try {
                    AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(Place.TYPE_COUNTRY)
                            .setCountry("IN")
                            .build();
                    Intent intent = new PlaceAutocomplete.IntentBuilder
                            (PlaceAutocomplete.MODE_OVERLAY)
                            .setBoundsBias(BOUNDS_MOUNTAIN_VIEW)
                            .setFilter(autocompleteFilter)
                            .build(LocationGetActivity.this);
                    startActivityForResult(intent, REQUEST_SELECT_PLACE_DEST);
//                    Intent intent =
//                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
//                                    .setTypeFilter(Place.TYPE_COUNTRY)
//                                    .setCountry("IN")
//                                    .build(this);
//                    startActivityForResult(intent, REQUEST_SELECT_PLACE_DEST);
                } catch (GooglePlayServicesRepairableException |
                        GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ll_get_current_loc:
                Intent intent = new Intent(LocationGetActivity.this, MainActivity.class);
                startActivity(intent);
                //  Variables.location_status = 0;
                //   ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
//                fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().add(R.id.fm_container, new HomeFragment(), "").commit();

                finish();
                break;
            case R.id.iv_back_arrow:
                onBackPressed();
                finish();
                break;
        }

    }


    @SuppressLint("WrongConstant")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SELECT_PLACE_DEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(LocationGetActivity.this, data);
                this.onPlaceSelected(place);
                et_location_search.setText(place.getAddress());
                latLng = place.getLatLng();
                lati = latLng.latitude;
                longi = latLng.longitude;
                String ddress = Common.getAddressString(LocationGetActivity.this, lati, longi);
//
//                Variables.location_status = 1;
//                Variables.cust_lat = String.valueOf(lati);
//                Variables.cust_lng = String.valueOf(longi);
//                Variables.cust_address = ddress;
                Intent intent = new Intent(LocationGetActivity.this, MainActivity.class);
                startActivity(intent);
                // ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(LocationGetActivity
                        .this, data);
                this.onError(status);
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        et_location_search.setOnClickListener(this);
        ll_get_current_loc.setOnClickListener(this);
        iv_back_arrow.setOnClickListener(this);
    }
    @Override
    protected void initializeViews() {
        super.initializeViews();
        et_location_search = findViewById(R.id.et_location_search);
        ll_get_current_loc = findViewById(R.id.ll_get_current_loc);
        iv_back_arrow = findViewById(R.id.iv_back_arrow);

        locationRecycleView = (RecyclerView) findViewById(R.id.list_search);
        locationRecycleView.setHasFixedSize(true);
        llm = new LinearLayoutManager(this);
        locationRecycleView.setLayoutManager(llm);


    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.current_loaction_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }

    @Override
    public void onPlaceSelected(Place place) {

    }

    @Override
    public void onError(Status status) {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LocationGetActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
