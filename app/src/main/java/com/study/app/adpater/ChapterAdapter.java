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

import com.study.app.ChapterDetailActivity;
import com.study.app.CourseDetailActivity;
import com.study.app.R;
import com.study.app.WebViewActivity;
import com.study.app.bean.Chapter;
import com.study.app.bean.Course;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends BaseAdapter {

    Context mContext;
    List<Chapter> mMsgInfos = new ArrayList<>();

    public ChapterAdapter(Context mContext, List<Chapter> mMsgInfos){
        this.mContext = mContext;
        this.mMsgInfos = mMsgInfos;
    }

    public void setData(List<Chapter> mMsgInfos){
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
        final Chapter msgInfo = mMsgInfos.get(i);
        ChapterAdapter.ViewHoler holer = null;
        if (view == null){
            holer = new ChapterAdapter.ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.msg_item,null);
            holer.mPic = (ImageView) view.findViewById(R.id.msg_pic);
            holer.mTitle = (TextView) view.findViewById(R.id.msg_title);
            holer.mContent = (TextView) view.findViewById(R.id.msg_content);
            view.setTag(holer);
        }else{
            holer = (ChapterAdapter.ViewHoler) view.getTag();
        }
        holer.mPic.setBackgroundResource(R.drawable.chapter_icon);
        holer.mTitle.setText(msgInfo.getCHAPTER_NAME());
        holer.mContent.setText(msgInfo.getCHAPTER_FINISH());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ChapterDetailActivity.class);
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
