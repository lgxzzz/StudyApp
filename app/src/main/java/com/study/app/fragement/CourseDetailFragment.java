package com.study.app.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.study.app.R;
import com.study.app.ChapterActivity;
import com.study.app.bean.Course;
import com.study.app.bean.Study;
import com.study.app.bean.User;
import com.study.app.data.DBManger;


import java.text.SimpleDateFormat;
import java.util.Date;


public class CourseDetailFragment extends Fragment {
    //课程详情页面
    Course course;
    TextView goodname;
    TextView type;
    ImageView goodPicture;
    TextView detail;
    Button btnTakeOrder;
    Button btnTakeDingdan;
    LinearLayout bottomLayout;

    private User user;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_course_detail, container, false);
        course = (Course) getActivity().getIntent().getSerializableExtra("DATA");
        user = DBManger.getInstance(getContext()).mUser;
        initView(view);
        goodname.setText(course.getCOURSE_TYPE());
//        type.setText(store.getName() + store.getType() + store.getBianhao());
        Glide.with(getActivity()).load(course.getCOURSE_PIC_ID()).into(goodPicture);
        detail.setText(course.getCOURSE_CONTEX());

        btnTakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isStudy = DBManger.getInstance(getContext()).isStudy(course.getCOURSE_ID());
                if (!isStudy){
                    //保存学习记录
                    Study study = new Study();
                    study.setSTUDY_ID(getRandom_ID());
                    study.setCOURSE_ID(course.getCOURSE_ID());
                    study.setSTUDY_TIME(longToDate());
                    study.setUSER_ID(user.getUserId());
                    DBManger.getInstance(getContext()).insertStudy(study);
                }
                Intent intent = new Intent(getContext(), ChapterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", course);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().finish();


            }
        });
        return view;
    }

    //生成学习id
    public static String getRandom_ID(){
        String strRand="S" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public static String longToDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    public void initView(View view){

        goodname = view.findViewById(R.id.goodname);
        type = view.findViewById(R.id.type);
        goodPicture = view.findViewById(R.id.good_picture);
        detail = view.findViewById(R.id.detail);
        btnTakeOrder = view.findViewById(R.id.btn_take_order);
        btnTakeDingdan = view.findViewById(R.id.btn_take_dingdan);
        bottomLayout = view.findViewById(R.id.bottom_layout);

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    public void initData(){

    }
}

