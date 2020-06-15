package com.study.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.bean.Chapter;
import com.study.app.bean.Course;
import com.study.app.data.DBManger;


public class ChapterDetailActivity extends Activity{

    private WebView mWebView;
    private Button mFinishBtn;

    Chapter chapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_detail);
        init();
    }

    public void init(){

        chapter = (Chapter) getIntent().getSerializableExtra("DATA");

        mWebView = findViewById(R.id.msg_web);
        mWebView.loadUrl(chapter.getCHAPTER_URL());
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mFinishBtn = findViewById(R.id.chapter_finish_btn);
        mFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chapter.setCHAPTER_FINISH("已完成");
                DBManger.getInstance(getBaseContext()).updateChapter(chapter);
                Toast.makeText(getApplicationContext(), "本章已学习！",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }


}
