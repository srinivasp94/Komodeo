package com.iprismtech.komodeo.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.ClassHorizontalAdapter;
import com.iprismtech.komodeo.adapters.ClassesSearchApdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.SearchClassesPojo;
import com.iprismtech.komodeo.request.SearchClassesReq;
import com.iprismtech.komodeo.request.SimpleReq;
import com.iprismtech.komodeo.request.SubmitContactReq;
import com.iprismtech.komodeo.request.uploadCredentialsReq;
import com.iprismtech.komodeo.responses.ClassList;
import com.iprismtech.komodeo.responses.ClassResponse;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class UploadCredentialsActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private AppCompatSpinner spinnerClass;
    private ImageView iv_backbtn, image1, image2, image3;
    private int GALLERY_DOC = 101, CAMERA_DOC = 102;
    private String base64profile1, base64profile2, base64profile3;
    private int imageViewId;
    private String result;
    private Object obj;
    private EditText et_search;
    private SearchClassesPojo searchClassesPojo;
    private RecyclerView rview_search_classes;
    private LinearLayoutManager manager;
    private ClassesSearchApdapter adapter;

    private TextView txtSendCredentials;
    private ArrayList<ClassList> classLists = new ArrayList<>();
    private String classid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload_credentials);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_upload_credentials, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        iv_backbtn.setOnClickListener(this);
        image1.setOnClickListener(this);
        image2.setOnClickListener(this);
        image3.setOnClickListener(this);
        txtSendCredentials.setOnClickListener(this);
    }


    @Override
    protected void initializeViews() {
        super.initializeViews();
//        spinnerClass = findViewById(R.id.spinnerClass);

        iv_backbtn = findViewById(R.id.iv_backbtn_contact);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        txtSendCredentials = findViewById(R.id.txtSendCredentials);

        et_search = findViewById(R.id.et_search);
        rview_search_classes = findViewById(R.id.rview_search_classes);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1) {

                    callSearchWS(s.toString());
                } else if (s.length() == 0) {
                    adapter = null;
                }
                // return false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


       /* SimpleReq req = new SimpleReq();
        req.userId = SharedPrefsUtils.getInstance(UploadCredentialsActivity.this).getId();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        try {
            obj = Class.forName(SimpleReq.class.getName()).cast(req);
        } catch (Exception e) {

        }
        new RetrofitRequester(this).callPostServices(obj, 1, "user_classes", true);*/


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_backbtn_contact:
                onBackPressed();
                break;
            case R.id.image1:
                imageViewId = image1.getId();
                showPictureDialog("", image1);
                break;
            case R.id.image2:
                imageViewId = image2.getId();
                showPictureDialog("", image2);
                break;
            case R.id.image3:
                imageViewId = image3.getId();
                showPictureDialog("", image3);
                break;
            case R.id.txtSendCredentials:
                if (classid == null || classid.equalsIgnoreCase("")) {
                    Common.showToast(UploadCredentialsActivity.this, "Please Select class");
                } else if (base64profile1 == null || base64profile2 == null || base64profile3 == null) {
                    Common.showToast(UploadCredentialsActivity.this, "Please Select Images");
                } else {
                    uploadCredentialsReq req = new uploadCredentialsReq();
                    req.userId = SharedPrefsUtils.getInstance(UploadCredentialsActivity.this).getId();
                    req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                    req.classId = classid;
                    if (base64profile1 != null)
                        req.images.get(0).image = base64profile1;
                    if (base64profile2 != null)
                        req.images.get(1).image = base64profile1;
                    if (base64profile1 != null)
                        req.images.get(2).image = base64profile1;
                    try {
                        obj = Class.forName(uploadCredentialsReq.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 2, "upload_credentials", true);
                }
                break;

        }
    }

    private void callSearchWS(String s) {

        SearchClassesReq searchClassesReq = new SearchClassesReq();
        searchClassesReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        searchClassesReq.keyword = s;
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(SearchClassesReq.class.getName()).cast(searchClassesReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "search_classes", false);
    }


    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(UploadCredentialsActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            searchClassesPojo = gson.fromJson(jsonString, SearchClassesPojo.class);
                            manager = new LinearLayoutManager(UploadCredentialsActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_search_classes.setLayoutManager(manager);
                            adapter = new ClassesSearchApdapter(UploadCredentialsActivity.this, searchClassesPojo);
                            rview_search_classes.setAdapter(adapter);
                            adapter.setOnItemClickListener(new ClassesSearchApdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    classid = searchClassesPojo.getResponse().get(position).getId();
                                }
                            });
                            break;
                        case 2:
                            Common.showToast(UploadCredentialsActivity.this, jsonObject.optString("message"));
                            break;
                    }
                } else {
                    Common.showToast(UploadCredentialsActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void showPictureDialog(final String base64, final ImageView imageView) {
        android.app.AlertDialog.Builder pictureDialog = new android.app.AlertDialog.Builder(UploadCredentialsActivity.this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary(base64, imageView);
                                break;
                            case 1:
                                takePhotoFromCamera(base64, imageView);
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary(String base64, ImageView imageView) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(UploadCredentialsActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(UploadCredentialsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(UploadCredentialsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(UploadCredentialsActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(galleryIntent, "Select Picture"), GALLERY_DOC);


    }

    private void takePhotoFromCamera(String base64, ImageView imageView) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(UploadCredentialsActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(UploadCredentialsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(UploadCredentialsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Log.d("hhhh", "Permissions not granted");
                // ask for permission
                ActivityCompat.requestPermissions(UploadCredentialsActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
            }
        }
        try {
            //   FileName = System.currentTimeMillis() + ".jpg";
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(intent, CAMERA_DOC);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        } else if (requestCode == GALLERY_DOC) {
            if (data != null) {
                Uri choosenImage = data.getData();
                if (choosenImage != null) {

                    Bitmap bp = decodeUri(choosenImage, 200);
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bp.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
//                    image1.setImageBitmap(bp);

                    byte[] byte_arr = bytes.toByteArray();
                    result = Base64.encodeToString(byte_arr, Base64.DEFAULT);

//                    imgUpdateWsCall(base64profile);
                    switch (imageViewId) {
                        case R.id.image1:
                            image1.setImageBitmap(bp);
                            base64profile1 = result;
                            break;
                        case R.id.image2:
                            image2.setImageBitmap(bp);
                            base64profile2 = result;
                            break;
                        case R.id.image3:
                            image3.setImageBitmap(bp);
                            base64profile3 = result;
                            break;
                    }

                }
            }
        } else if (requestCode == CAMERA_DOC) {

            // Variable.img_banner = profile;
            Bitmap profilebit = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            profilebit.compress(Bitmap.CompressFormat.JPEG, 70, stream);
//            image1.setImageBitmap(profilebit);
            byte[] byte_arr = stream.toByteArray();
            result = Base64.encodeToString(byte_arr, Base64.DEFAULT);
//            imgUpdateWsCall(base64profile);

            switch (imageViewId) {
                case R.id.image1:
                    image1.setImageBitmap(profilebit);
                    base64profile1 = result;
                    break;
                case R.id.image2:
                    image2.setImageBitmap(profilebit);
                    base64profile2 = result;
                    break;
                case R.id.image3:
                    image3.setImageBitmap(profilebit);
                    base64profile3 = result;
                    break;
            }
        }
    }

    protected Bitmap decodeUri(Uri selectedImage, int REQUIRED_SIZE) {
        try {
            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(UploadCredentialsActivity.this.getContentResolver().openInputStream(selectedImage), null, o);
            // The new size we want to scale to
            // final int REQUIRED_SIZE =  size;
            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE
                        || height_tmp / 2 < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(UploadCredentialsActivity.this.getContentResolver().openInputStream(selectedImage), null, o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
