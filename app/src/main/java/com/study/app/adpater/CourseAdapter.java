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
import com.study.app.CourseDetailActivity;
import com.study.app.R;
import com.study.app.WebViewActivity;
import com.study.app.bean.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends BaseAdapter {

    Context mContext;
    List<Course> mMsgInfos = new ArrayList<>();

    public CourseAdapter(Context mContext, List<Course> mMsgInfos){
        this.mContext = mContext;
        this.mMsgInfos = mMsgInfos;
    }

    public void setData(List<Course> mMsgInfos){
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
        final Course msgInfo = mMsgInfos.get(i);
        CourseAdapter.ViewHoler holer = null;
        if (view == null){
            holer = new CourseAdapter.ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.msg_item,null);
            holer.mPic = (ImageView) view.findViewById(R.id.msg_pic);
            holer.mTitle = (TextView) view.findViewById(R.id.msg_title);
            holer.mContent = (TextView) view.findViewById(R.id.msg_content);
            view.setTag(holer);
        }else{
            holer = (CourseAdapter.ViewHoler) view.getTag();
        }
        holer.mPic.setBackgroundResource(Integer.parseInt(msgInfo.getCOURSE_PIC_ID()));
        holer.mTitle.setText(msgInfo.getCOURSE_TYPE());
        holer.mContent.setText(msgInfo.getCOURSE_CONTEX());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CourseDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", msgInfo);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    class ViewHoler{
        ImageView mPic;
        TextView mTitle;
        TextView mContent;
    }
}
