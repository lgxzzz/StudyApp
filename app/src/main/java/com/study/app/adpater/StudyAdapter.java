package com.study.app.adpater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.app.ChapterActivity;
import com.study.app.R;
import com.study.app.WebViewActivity;
import com.study.app.bean.Course;
import com.study.app.bean.Study;
import com.study.app.data.DBManger;

import java.util.ArrayList;
import java.util.List;

public class StudyAdapter extends BaseAdapter {

    Context mContext;
    List<Study> mMsgInfos = new ArrayList<>();

    public StudyAdapter(Context mContext, List<Study> mMsgInfos){
        this.mContext = mContext;
        this.mMsgInfos = mMsgInfos;
    }

    public void setData(List<Study> mMsgInfos){
        this.mMsgInfos = mMsgInfos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mMsgInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return mMsgInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Study msgInfo = mMsgInfos.get(i);
        StudyAdapter.ViewHoler holer = null;
        if (view == null){
            holer = new StudyAdapter.ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.study_item,null);
            holer.mPic = (ImageView) view.findViewById(R.id.course_pic);
            holer.mCourseName = (TextView) view.findViewById(R.id.course_name_tv);
            holer.mProgress = (TextView) view.findViewById(R.id.course_progress_tv);
            holer.mTime = (TextView) view.findViewById(R.id.study_time_tv);
            view.setTag(holer);
        }else{
            holer = (StudyAdapter.ViewHoler) view.getTag();
        }
        final Course course = msgInfo.getCourse();
        holer.mPic.setBackgroundResource(Integer.parseInt(course.getCOURSE_PIC_ID()));
        holer.mCourseName.setText(course.getCOURSE_TYPE());
        holer.mProgress.setText(course.getProgress());
        holer.mTime.setText(msgInfo.getSTUDY_TIME());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChapterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", course);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHoler{
        ImageView mPic;
        TextView mCourseName;
        TextView mProgress;
        TextView mTime;
    }
}
