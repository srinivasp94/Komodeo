package com.iprismtech.komodeo.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.CommentsAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.CommentsPostPojo;
import com.iprismtech.komodeo.request.CommentsReq;
import com.iprismtech.komodeo.request.SubmitCommentReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserPostCommentActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {
    private EditText et_post_comment;
    private TextView tv_submit, tv_load_more, tv_posted, tv_name_posted_name;
    private RecyclerView rview_post_comment;
    private LinearLayoutManager manager;
    int discussions_count = 0;
    private Object obj;
    private CommentsPostPojo commentsPostPojo;
    private List<CommentsPostPojo.ResponseBean> responseBeans;
    private CommentsAdapter commentsAdapter;
    private String discussionsId, str_Post;
    private String str_class_ID, str_Img, str_Name;
    private ImageView iv_main_posted_person;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                if (et_post_comment.getText().toString().isEmpty()) {
                    Toast.makeText(UserPostCommentActivity.this, "Please write comment", Toast.LENGTH_SHORT).show();
                } else {
                    callPostCommetWS();
                }

                break;
            case R.id.tv_load_more:
                discussions_count = discussions_count + 10;

                CommentsReq commentsReq = new CommentsReq();
                commentsReq.discussionId = "1";
                commentsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                commentsReq.count = String.valueOf(discussions_count);
                //flatListRequest.building_id="4";

                try {
                    obj = Class.forName(CommentsReq.class.getName()).cast(commentsReq);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(this).callPostServices(obj, 1, "discussion_comments", true);

                break;
        }
    }

    private void callPostCommetWS() {
        SubmitCommentReq submitCommentReq = new SubmitCommentReq();
        submitCommentReq.classId = str_class_ID;
        submitCommentReq.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
        submitCommentReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        submitCommentReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        submitCommentReq.discussionId = discussionsId;
        submitCommentReq.comment = et_post_comment.getText().toString();

        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(SubmitCommentReq.class.getName()).cast(submitCommentReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "submit_comment", true);
    }


    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_user_comment_post, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_submit.setOnClickListener(this);
        tv_load_more.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        responseBeans = new ArrayList<>();


        Intent intent = getIntent();
        discussionsId = intent.getStringExtra("Key_DiscussionId");
        str_Post = intent.getStringExtra("Key_post");
        str_class_ID = intent.getStringExtra("Key_classID");
        str_Img = intent.getStringExtra("Key_image");
        str_Name = intent.getStringExtra("Key_name");


        et_post_comment = findViewById(R.id.et_post_comment);
        rview_post_comment = findViewById(R.id.rview_post_comment);
        tv_submit = findViewById(R.id.tv_submit);
        tv_load_more = findViewById(R.id.tv_load_more);
        tv_posted = findViewById(R.id.tv_posted);
        tv_name_posted_name = findViewById(R.id.tv_name_posted_name);
        iv_main_posted_person = findViewById(R.id.iv_main_posted_person);

        tv_posted.setText(str_Post);
        tv_name_posted_name.setText(str_Name);

        Picasso.with(UserPostCommentActivity.this).load(str_Img).into(iv_main_posted_person);

        CommentsReq commentsReq = new CommentsReq();
        commentsReq.discussionId = discussionsId;
        commentsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        commentsReq.count = String.valueOf(discussions_count);
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(CommentsReq.class.getName()).cast(commentsReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "discussion_comments", true);


    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(UserPostCommentActivity.this, "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:

                            commentsPostPojo = gson.fromJson(jsonString, CommentsPostPojo.class);
                            if (commentsPostPojo.getResponse().size() > 10) {
                                tv_load_more.setVisibility(View.VISIBLE);
                            }
                            manager = new LinearLayoutManager(UserPostCommentActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_post_comment.setLayoutManager(manager);
                            responseBeans.addAll(commentsPostPojo.getResponse());
                            commentsAdapter = new CommentsAdapter(UserPostCommentActivity.this, responseBeans);
                            rview_post_comment.setAdapter(commentsAdapter);
                            commentsAdapter.setOnItemClickListener(new CommentsAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {

                                        case R.id.iv_post_like:
                                            //  adaptet_item_view = view;
//                                            ImageView imageV = view.findViewById(R.id.iv_likes);
//                                            imageV.setImageResource(R.mipmap.ic_liked);
//
//
//                                            TextView count_likes = view.findViewById(R.id.tv_count_likes);
//                                            String str_count = count_likes.getText().toString();
//
//                                            int int_count = Integer.parseInt(str_count);
//                                            int_count = int_count + 1;
//                                            count_likes.setText(int_count + "");
                                            callSubmitCommentWs(position);
                                            break;
                                    }
                                }
                            });
                            break;
                        case 2:
                            Toast.makeText(UserPostCommentActivity.this, "Comment Posted Successfully", Toast.LENGTH_SHORT).show();

                            Intent returnIntent = new Intent();
                            returnIntent.putExtra("post_Status", "ok");
                            setResult(Activity.RESULT_OK, returnIntent);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(UserPostCommentActivity.this, object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void callSubmitCommentWs(int position) {
    }
}
