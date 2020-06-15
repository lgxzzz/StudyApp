package com.study.app.fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


import com.study.app.R;
import com.study.app.adpater.CourseAdapter;
import com.study.app.adpater.StudyAdapter;
import com.study.app.bean.Course;
import com.study.app.bean.Study;
import com.study.app.bean.User;
import com.study.app.data.DBManger;

import java.util.ArrayList;
import java.util.List;

public class StudyFragment extends Fragment {
    List<Study> mStudys = new ArrayList<>();

    ListView mMsgListview;

    StudyAdapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragement_study, container, false);
        initView(view);

        return view;
    }

    public static StudyFragment getInstance() {
        return new StudyFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initView(View view){
        mMsgListview = view.findViewById(R.id.search_info_list);
    };

    public void initData() {
        User user = DBManger.getInstance(getContext()).mUser;
        mStudys = DBManger.getInstance(getContext()).getStudysByUserId(user.getUserId());
        mAdapter = new StudyAdapter(getContext(),mStudys);
        mMsgListview.setAdapter(mAdapter);
    }

}
