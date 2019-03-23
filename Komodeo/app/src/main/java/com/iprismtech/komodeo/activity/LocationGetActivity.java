package com.iprismtech.komodeo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
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
import com.iprismtech.komodeo.utils.GPSTracker;
import com.iprismtech.komodeo.utils.Variables;

import java.util.List;
import java.util.Locale;

import android.os.Handler;


public class LocationGetActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener, PlaceSelectionListener, LocationListener {
    private EditText et_location_search;
    int AUTO_COMP_REQ_CODE = 1;
    private double lati, longi, current_lati, current_lng;
    private LinearLayout ll_get_current_loc;
    private ImageView iv_back_arrow;
    private RecyclerView locationRecycleView;
    private LinearLayoutManager llm;
    private LatLng latLng;
    private Handler handler;

    private static final LatLngBounds BOUNDS_INDIA = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));

    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));
    private static final int REQUEST_SELECT_PLACE_DEST = 1000;
    private String ddress;

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
                Intent returnIntent = new Intent();
                returnIntent.putExtra("location_Status", "ok");
                returnIntent.putExtra("user_lat", String.valueOf(lati));
                returnIntent.putExtra("user_lang", String.valueOf(longi));
                returnIntent.putExtra("user_address", ddress);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

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

                Variables.location_status = 1;
                Variables.cust_lat = String.valueOf(lati);
                Variables.cust_lng = String.valueOf(longi);
                Variables.cust_address = ddress;


                Intent returnIntent = new Intent();
                returnIntent.putExtra("location_Status", "ok");
                returnIntent.putExtra("user_lat", String.valueOf(lati));
                returnIntent.putExtra("user_lang", String.valueOf(longi));
                returnIntent.putExtra("user_address", ddress);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

//
//                Intent intent = new Intent(LocationGetActivity.this, MainActivity.class);
//                startActivity(intent);
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


        //   handler = new Handler();

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                GPSTracker gpsTracker = new GPSTracker(LocationGetActivity.this);
                lati = gpsTracker.getLatitude();
                longi = gpsTracker.getLongitude();
                // prefsUtils.storelatlangcode(String.valueOf(lat), String.valueOf(lng));
                ddress = Common.getAddressString(LocationGetActivity.this, lati, longi);
                Variables.current_address = ddress;
                // tv_loc_city.setText(ddress);
            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {
        try {
            current_lati = location.getLatitude();
            current_lng = location.getLongitude();

//        date.setText("location  " + lat + " " + lng);
            Log.d("MAinActivity", "" + current_lati + " " + current_lati);
            if (location != null) {
//                String s = Common.getAddressString(getApplicationContext(), location.getLatitude(), location.getLongitude());

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());

                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
//                if (city != null)
//                    edt_landmark.setText(city);
//                if (postalCode != null)
//                    edt_pin.setText(postalCode);
//                if (address != null)
//                    edt_house.setText(address);
//                if (state != null)
//                    edt_local.setText(state);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
