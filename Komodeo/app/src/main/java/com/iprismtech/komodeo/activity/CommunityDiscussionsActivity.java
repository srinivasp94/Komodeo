package com.iprismtech.komodeo.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.AutomatchDialog;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.adapters.DiscussionsAdapter;
import com.iprismtech.komodeo.base.BaseAbstractActivity;
import com.iprismtech.komodeo.pojo.DiscussionsPojo;
import com.iprismtech.komodeo.request.DiscussionsReq;
import com.iprismtech.komodeo.request.SubmitCommentReq;
import com.iprismtech.komodeo.request.SubmitLikeReq;
import com.iprismtech.komodeo.request.SubmotPostReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityDiscussionsActivity extends BaseAbstractActivity implements View.OnClickListener, RetrofitResponseListener {

    private RecyclerView rview_dicussions;
    private LinearLayout ll_automatch, ll_currenttab_events;
    private ImageView iv_discussion_back, tv_add_study_events, iv_add_tutor_events, iv_community;
    private LinearLayout ll_communitymembers;
    private Boolean position0 = false, position1 = false, position2 = false;
    private LinearLayout ll_automatchpics, ll_eventtab_community, ll_discussiontab;
    private RelativeLayout rl_profile_details, rl_post_layout;
    private String course_name, course_ID;
    private Object obj;
    private LinearLayoutManager manager;
    private DiscussionsAdapter discussionsAdapter;
    private DiscussionsPojo discussionsPojo;
    private TextView tv_selected_course, tv_load_more;
    private int discussions_count = 0;
    private View adaptet_item_view;
    private List<DiscussionsPojo.ResponseBean> responseBeans;
    private String post_Status;
    private int selected_position;
    private View imageV, imageV1;
    private LinearLayout ll_submit_post;
    private EditText et_write_post;
    private boolean like_clicked_staus = false;
    private Map<Integer, DiscussionsPojo.ResponseBean> userMap = new HashMap<Integer, DiscussionsPojo.ResponseBean>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        responseBeans = new ArrayList<>();

        Intent intent = getIntent();
        course_name = intent.getStringExtra("Key_course_name");
        course_ID = intent.getStringExtra("Key_course_ID");


        iv_discussion_back = findViewById(R.id.iv_discussion_back);
        tv_add_study_events = findViewById(R.id.tv_add_study_events);
        iv_add_tutor_events = findViewById(R.id.iv_add_tutor_events);
        iv_community = findViewById(R.id.iv_community);
        tv_load_more = findViewById(R.id.tv_load_more);

        rview_dicussions = findViewById(R.id.rview_dicussions);
        ll_automatchpics = findViewById(R.id.ll_automatchpics);
        rl_post_layout = findViewById(R.id.rl_post_layout);
        ll_eventtab_community = findViewById(R.id.ll_eventtab_community);
        ll_discussiontab = findViewById(R.id.ll_discussiontab);
        ll_communitymembers = findViewById(R.id.ll_communitymembers);
        ll_automatch = findViewById(R.id.ll_automatch);
        ll_currenttab_events = findViewById(R.id.ll_currenttab_events);
        tv_selected_course = findViewById(R.id.tv_selected_course);
        ll_submit_post = findViewById(R.id.ll_submit_post);
        et_write_post = findViewById(R.id.et_write_post);
        tv_selected_course.setText(course_name);


        DiscussionsReq discussionsReq = new DiscussionsReq();
        discussionsReq.classId = course_ID;
        discussionsReq.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
        discussionsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        discussionsReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        discussionsReq.count = String.valueOf(discussions_count);
        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(DiscussionsReq.class.getName()).cast(discussionsReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "discussions", true);
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_discussiontab.setOnClickListener(this);
        iv_discussion_back.setOnClickListener(this);
        ll_currenttab_events.setOnClickListener(this);
        ll_eventtab_community.setOnClickListener(this);
        tv_add_study_events.setOnClickListener(this);
        iv_add_tutor_events.setOnClickListener(this);
        iv_community.setOnClickListener(this);
        tv_load_more.setOnClickListener(this);
        ll_submit_post.setOnClickListener(this);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.discussion_tab_search_subject, null);
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_discussiontab:

//                ll_automatchpics.setVisibility(View.VISIBLE);
//                rl_post_layout.setVisibility(View.VISIBLE);
//                rl_profile_details.setVisibility(View.GONE);

                if (position1 == false) {
                    ll_currenttab_events.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));
                    ll_discussiontab.setBackgroundColor(getResources().getColor(R.color.orange_tab_select, null));
                    ll_eventtab_community.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));

                } else {
                    ll_discussiontab.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));
                }
                break;
            case R.id.iv_discussion_back:
                onBackPressed();
                break;
            case R.id.ll_currenttab_events:

                if (position0 == false) {
                    ll_currenttab_events.setBackgroundColor(getResources().getColor(R.color.orange_tab_select, null));
                    ll_discussiontab.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));
                    ll_eventtab_community.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));
                } else {
                    ll_currenttab_events.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));

                }
//                Intent intent = new Intent(CommunityDiscussionsActivity.this, EventsTabAct.class);
//                startActivity(intent);
                break;
            case R.id.ll_eventtab_community:
//                ll_automatchpics.setVisibility(View.GONE);
//                rl_post_layout.setVisibility(View.GONE);
//                rl_profile_details.setVisibility(View.VISIBLE);



                ll_eventtab_community.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));
                Intent intent = new Intent(CommunityDiscussionsActivity.this, CommunityActivity.class);
                intent.putExtra("Key_CourseID", course_ID);
                intent.putExtra("Key_CurseName", course_name);
                startActivity(intent);


//                if (position2 == false) {
//
//                    ll_currenttab_events.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));
//                    ll_discussiontab.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));
//                    ll_eventtab_community.setBackgroundColor(getResources().getColor(R.color.orange_tab_select, null));
//
//                } else {
//                    ll_eventtab_community.setBackgroundColor(getResources().getColor(R.color.orange_tab_unselected, null));
//                    Intent intent = new Intent(CommunityDiscussionsActivity.this, CommunityActivity.class);
//                    intent.putExtra("Key_CourseID", course_ID);
//                    intent.putExtra("Key_CurseName", course_name);
//                    startActivity(intent);
//
//                }

                break;
            case R.id.tv_load_more:
                discussions_count = discussions_count + 10;
                DiscussionsReq discussionsReq = new DiscussionsReq();
                discussionsReq.classId = course_ID;
                discussionsReq.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
                discussionsReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                discussionsReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
                discussionsReq.count = String.valueOf(discussions_count);
                //flatListRequest.building_id="4";

                try {
                    obj = Class.forName(DiscussionsReq.class.getName()).cast(discussionsReq);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(this).callPostServices(obj, 1, "discussions", true);
                break;
            case R.id.ll_submit_post:
                if (et_write_post.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Write Something", Toast.LENGTH_SHORT).show();
                } else {
                    SubmotPostReq submotPostReq = new SubmotPostReq();
                    submotPostReq.classId = course_ID;
                    submotPostReq.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
                    submotPostReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
                    submotPostReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
                    submotPostReq.image = "base 64";
                    submotPostReq.description = et_write_post.getText().toString();

                    //flatListRequest.building_id="4";

                    try {
                        obj = Class.forName(SubmotPostReq.class.getName()).cast(submotPostReq);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 3, "submit_discussion_posts", true);

                }
                break;
            case R.id.tv_add_study_events:
                Intent createIntent = new Intent(CommunityDiscussionsActivity.this,CreateTutorRequestAct.class);
                createIntent.putExtra("Key_Event","studyEvent");
                createIntent.putExtra("Key_ClassId","1");
                startActivity(createIntent);
                break;
        }

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, final int requestId) {
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(CommunityDiscussionsActivity.this, "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);
                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:

                            discussionsPojo = gson.fromJson(jsonString, DiscussionsPojo.class);
                            manager = new LinearLayoutManager(CommunityDiscussionsActivity.this);
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_dicussions.setLayoutManager(manager);
                            responseBeans.addAll(discussionsPojo.getResponse());
                            discussionsAdapter = new DiscussionsAdapter(CommunityDiscussionsActivity.this, responseBeans);
                            rview_dicussions.setAdapter(discussionsAdapter);
                            discussionsAdapter.setOnItemClickListener(new DiscussionsAdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    selected_position = position;
                                    switch (view.getId()) {

                                        case R.id.iv_post_like:
                                            like_clicked_staus = true;
                                            adaptet_item_view = view;
                                            imageV = adaptet_item_view.getRootView().findViewById(R.id.iv_likes);
                                            imageV1 = adaptet_item_view.getRootView().findViewById(R.id.iv_post_like);
                                            callSubmitLikeWs(position);
                                            break;
                                        case R.id.ll_comments_posted:
                                            Intent intent = new Intent(CommunityDiscussionsActivity.this, UserPostCommentActivity.class);
                                            intent.putExtra("Key_DiscussionId", responseBeans.get(position).getId());
                                            intent.putExtra("Key_post", responseBeans.get(position).getDescription());
                                            intent.putExtra("Key_classID", course_ID);
                                            intent.putExtra("Key_image", responseBeans.get(position).getImage());
                                            intent.putExtra("Key_name", responseBeans.get(position).getFirst_name() + " " + responseBeans.get(position).getLast_name());

                                            startActivityForResult(intent, 101);
                                            break;
                                        case R.id.ll_comment:
                                            Intent intent1 = new Intent(CommunityDiscussionsActivity.this, UserPostCommentActivity.class);
                                            intent1.putExtra("Key_DiscussionId", responseBeans.get(position).getId());
                                            intent1.putExtra("Key_post", responseBeans.get(position).getDescription());
                                            intent1.putExtra("Key_classID", course_ID);
                                            intent1.putExtra("Key_image", responseBeans.get(position).getImage());
                                            intent1.putExtra("Key_name", responseBeans.get(position).getFirst_name() + " " + responseBeans.get(position).getLast_name());

                                            startActivityForResult(intent1, 101);
                                            break;
                                    }
                                }
                            });
                            break;
                        case 2:


                            if (responseBeans.get(selected_position).getLiked().equalsIgnoreCase("yes")) {
                                //      if (like_clicked_staus) {
                                Toast.makeText(this, "You are already liked this post", Toast.LENGTH_SHORT).show();
                            } else {
                                Picasso.with(CommunityDiscussionsActivity.this).load(R.mipmap.ic_liked).into((ImageView) imageV);
                                //responseBeans.set(responseBeans.get(selected_position).setLiked("yes"))
                                responseBeans.get(selected_position).setLiked("yes");

//                                ImageView imageV = adaptet_item_view.getRootView().findViewById(R.id.iv_likes);
//                                imageV.setImageResource(R.mipmap.ic_liked);

//                               imageV1 = adaptet_item_view.getRootView().findViewById(R.id.iv_post_like);
//                                imageV1.setImageResource(R.drawable.ic_like_blue);

                                Picasso.with(CommunityDiscussionsActivity.this).load(R.drawable.ic_like_blue).into((ImageView) imageV1);


                                // TextView count_likes = adaptet_item_view.findViewById(R.id.tv_count_likes);
                                // String str_count = count_likes.getText().toString();
                                // String str_count = responseBeans.get(selected_position).getLikes();
                                String str_count = object.optString("likes");
                                float int_count = Float.valueOf(str_count);


                                String str_comment_count = object.optString("comments");
                                float int_comment_count = Float.valueOf(str_comment_count);
                                //   int_count = int_count + 1;
                                responseBeans.get(selected_position).setLikes(Math.round(int_count) + "");
                                responseBeans.get(selected_position).setComments(Math.round(int_comment_count) + "");
                                discussionsAdapter.notifyItemChanged(selected_position);
                                //  count_likes.setText(int_count + "");
                            }
                            break;
                        case 3:
                            Toast.makeText(this, "Posted Successfully", Toast.LENGTH_SHORT).show();
                            recreate();
                            break;
                    }
                } else {
                    Common.showToast(CommunityDiscussionsActivity.this, object.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void callSubmitLikeWs(int position) {
        SubmitLikeReq submitLikeReq = new SubmitLikeReq();
        submitLikeReq.classId = course_ID;
        submitLikeReq.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
        submitLikeReq.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        submitLikeReq.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        submitLikeReq.discussionId = responseBeans.get(position).getId();

        //flatListRequest.building_id="4";

        try {
            obj = Class.forName(SubmitLikeReq.class.getName()).cast(submitLikeReq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "submit_like", false);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == Activity.RESULT_OK) {
                post_Status = data.getStringExtra("post_Status");
                if (post_Status.equalsIgnoreCase("ok")) {
//                    TextView count_commnets = adaptet_item_view.getRootView().findViewById(R.id.tv_comments_count);
//                    int str_count_comments = Integer.parseInt(count_commnets.getText().toString());
//                    str_count_comments = str_count_comments + 1;
//                    count_commnets.setText(str_count_comments + "");
                }
                //Toast.makeText(getActivity(), building_id + "and" + name, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
}