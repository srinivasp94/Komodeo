package com.iprismtech.komodeo.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.komodeo.R;
import com.iprismtech.komodeo.activity.AddclassFormAct;
import com.iprismtech.komodeo.adapters.ClassesSearchApdapter;
import com.iprismtech.komodeo.base.BaseAbstractFragment;
import com.iprismtech.komodeo.factories.Constants.AppConstants;
import com.iprismtech.komodeo.factories.controllers.ApplicationController;
import com.iprismtech.komodeo.pojo.SearchClassesPojo;
import com.iprismtech.komodeo.request.AddClassReq;
import com.iprismtech.komodeo.request.SearchClassesReq;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitRequester;
import com.iprismtech.komodeo.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.komodeo.utils.Common;
import com.iprismtech.komodeo.utils.SharedPrefsUtils;

import org.json.JSONObject;

public class AddClassesFragment extends BaseAbstractFragment implements RetrofitResponseListener {
    private SearchView search_classes;
    private EditText et_search;
    private Object obj;
    private SearchClassesPojo searchClassesPojo;
    private RecyclerView rview_search_classes;
    private LinearLayoutManager manager;
    private RelativeLayout rlnosearchresults;
    private ClassesSearchApdapter adapter;

    @Override
    protected View getFragmentView() {
        View view = getLayoutInflater().inflate(R.layout.add_class_fragment_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        et_search = view.findViewById(R.id.et_search);
        rview_search_classes = view.findViewById(R.id.rview_search_classes);
        rlnosearchresults = view.findViewById(R.id.rlnosearchresults);


        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 1) {
                    rview_search_classes.setVisibility(View.VISIBLE);
                    callSearchWS(s.toString());
                } /*else if (s.length() == 0) {

                    searchClassesPojo = null;
                    adapter.notifyDataSetChanged();
                }*/
                // return false;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    searchClassesPojo = null;
                    adapter.notifyDataSetChanged();
                    rview_search_classes.setVisibility(View.GONE);
                }

            }
        });
        rlnosearchresults.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                //ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ADD_CLASS_FORM_SCREEN);
                startActivity(new Intent(getActivity(), AddclassFormAct.class));

            }
        });
//        search_classes = view.findViewById(R.id.search_classes);
//
//        search_classes.setIconifiedByDefault(true);
//        search_classes.setFocusable(true);
//        search_classes.setIconified(false);
//        search_classes.requestFocusFromTouch();
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
        if (objectResponse == null || objectRequest.equals("")) {
            Common.showToast(getActivity(), "PLease Try again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject object = new JSONObject(jsonString);

                if (object.optBoolean("status") == true) {
                    switch (requestId) {
                        case 1:
                            searchClassesPojo = gson.fromJson(jsonString, SearchClassesPojo.class);
                            manager = new LinearLayoutManager(getActivity());
                            manager.setOrientation(LinearLayoutManager.VERTICAL);
                            rview_search_classes.setLayoutManager(manager);
                            adapter = new ClassesSearchApdapter(getActivity(), searchClassesPojo);
                            rview_search_classes.setAdapter(adapter);
                            adapter.setOnItemClickListener(new ClassesSearchApdapter.OnitemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    callSelectClassWS(position);
                                }
                            });
                            break;
                        case 2:
                            Toast.makeText(getActivity(), "Class Added Successfully", Toast.LENGTH_SHORT).show();

                            break;
                    }
                } else {
                    rlnosearchresults.setVisibility(View.VISIBLE);
                    Common.showToast(getActivity(), object.optString("message"));
                    searchClassesPojo = null;
                    adapter.notifyDataSetChanged();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void callSelectClassWS(int position) {
        AddClassReq req = new AddClassReq();
        req.token = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_TOKEN);
        req.userId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_ID);
        req.courseName = searchClassesPojo.getResponse().get(position).getTitle();
        req.subjectName = searchClassesPojo.getResponse().get(position).getSubject_name();
        req.universityId = SharedPrefsUtils.getString(SharedPrefsUtils.KEY_UNIVERSITY_ID);
        try {
            obj = Class.forName(AddClassReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 2, "add_class", true);

    }
}
