package com.study.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.adpater.ChapterAdapter;
import com.study.app.bean.Chapter;
import com.study.app.bean.Course;
import com.study.app.data.DBManger;

import java.util.ArrayList;
import java.util.List;


public class ChapterActivity extends Activity{

    ImageView mBackImg;

    List<Chapter> mChapters = new ArrayList<>();

    ListView mChapterListView;

    Course course;

    ChapterAdapter mChapterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        init();
    }

    public void init(){
        course = (Course)getIntent().getSerializableExtra("DATA");
        mBackImg = findViewById(R.id.iv_back_collect);
        mBackImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mChapterListView = findViewById(R.id.chapter_listview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mChapters = DBManger.getInstance(this).getChaptersById(course.getCOURSE_ID());

        mChapterAdapter = new ChapterAdapter(this, mChapters);
        mChapterListView.setAdapter(mChapterAdapter);
    }
}
