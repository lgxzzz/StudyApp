package com.study.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.study.app.bean.Course;
import com.study.app.fragement.CourseDetailFragment;
import com.study.app.fragement.EvaluteFragment;

import java.util.ArrayList;
import java.util.List;



public class CourseDetailActivity extends FragmentActivity implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    ImageView ivBackCollect;
    LinearLayout llTitle;
    RadioButton rbAllOrderOrder;
    RadioButton rbPingJiaOrder;
    RadioGroup radioGroupOrder;
    ViewPager viewpagerOrder;

    private CourseDetailFragment tab1 = new CourseDetailFragment();

    private EvaluteFragment tab2 = new EvaluteFragment();
    private List<Fragment> fragments = new ArrayList<Fragment>();

    private FragmentPagerAdapter adapter;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initView();
        course = (Course) getIntent().getSerializableExtra("DATA");
        Bundle bundle = new Bundle();
        bundle.putSerializable("DATA", course);
        tab2.setArguments(bundle);
        fragments.add(tab1);
        fragments.add(tab2);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewpagerOrder.setAdapter(adapter);
        viewpagerOrder.setOnPageChangeListener(this);
        radioGroupOrder.setOnCheckedChangeListener(this);
        viewpagerOrder.setOffscreenPageLimit(1);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroupOrder.check(R.id.rb_allOrder_order);
                break;

            case 1:
                radioGroupOrder.check(R.id.rb_pingJia_order);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_allOrder_order:
                viewpagerOrder.setCurrentItem(0);
                break;

            case R.id.rb_pingJia_order:
                viewpagerOrder.setCurrentItem(1);
                break;
        }
    }

    public void initView(){
        ivBackCollect = findViewById(R.id.iv_back_collect);
        llTitle = findViewById(R.id.ll_title);
        rbAllOrderOrder = findViewById(R.id.rb_allOrder_order);
        rbPingJiaOrder =findViewById(R.id.rb_pingJia_order);
        radioGroupOrder = findViewById(R.id.radioGroup_order);
        viewpagerOrder = findViewById(R.id.viewpager_order);

        ivBackCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
