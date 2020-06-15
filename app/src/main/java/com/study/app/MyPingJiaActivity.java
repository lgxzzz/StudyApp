package com.study.app;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.bean.Course;
import com.study.app.bean.Evalute;
import com.study.app.bean.User;
import com.study.app.data.DBManger;

import org.xutils.db.table.DbBase;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MyPingJiaActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivBackMyPingJia;
    TextView tvTitleMyPingJia;
    LinearLayout llTitleMyPingJia;
    EditText evPingyu;
    RatingBar ratbar;
    Button save;
    LinearLayout activityMyPingJia;
    private Course course;
    private User user;
    private float mRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ping_jia);
        initView();
        user= DBManger.getInstance(this).mUser;
        course = (Course) getIntent().getSerializableExtra("DATA");
        ratbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRating=rating;
            }
        });
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_myPingJia:
                finish();
                break;
            case R.id.save:
                if (TextUtils.isEmpty(evPingyu.getText().toString())) {
                    Toast.makeText(MyPingJiaActivity.this, "评语不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Evalute evalute = new Evalute();
                evalute.setEVA_ID(getRandom_ID());
                evalute.setCOURSE_ID(course.getCOURSE_ID());
                evalute.setEVA_CONTENT(evPingyu.getText().toString());
                evalute.setEVA_TIME(longToDate());
                evalute.setUSER_ID(user.getUserId());
                DBManger.getInstance(getApplicationContext()).insertEvalute(evalute);
                Toast.makeText(MyPingJiaActivity.this, "添加评论成功!", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    //生成评价id
    public static String getRandom_ID(){
        String strRand="E" ;
        for(int i=0;i<10;i++){
            strRand += String.valueOf((int)(Math.random() * 10)) ;
        }
        return strRand;
    }


    public static String longToDate() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    public void initView(){
        ivBackMyPingJia = findViewById(R.id.iv_back_myPingJia);
        tvTitleMyPingJia = findViewById(R.id.tv_title_myPingJia);
        llTitleMyPingJia = findViewById(R.id.ll_title_myPingJia);
        evPingyu = findViewById(R.id.ev_pingyu);
        ratbar = findViewById(R.id.ratbar);
        save = findViewById(R.id.save);
        activityMyPingJia = findViewById(R.id.activity_my_ping_jia);

        ivBackMyPingJia.setOnClickListener(this);
        save.setOnClickListener(this);
    }
}
