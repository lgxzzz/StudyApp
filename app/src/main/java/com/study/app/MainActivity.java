package com.study.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.study.app.bean.User;
import com.study.app.data.DBManger;
import com.study.app.fragement.AboutFragment;
import com.study.app.fragement.StudyFragment;
import com.study.app.fragement.CourseFragment;
import com.study.app.util.FragmentUtils;


public class MainActivity extends BaseActivtiy {

    private BottomNavigationView mBottomMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window win = getWindow ();
        WindowManager.LayoutParams params = win.getAttributes ();
        win.setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        setContentView(R.layout.activity_main);
        init();

    }

    public void init(){
        User mUser = DBManger.getInstance(this).mUser;
        mBottomMenu = findViewById(R.id.bottom_menu);

        mBottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showFragment(item.getItemId());
                return true;
            }
        });
        showFragment(R.id.bottom_menu_info);
    }


    /**
     * 根据id显示相应的页面
     * @param menu_id
     */
    private void showFragment(int menu_id) {
        switch (menu_id){
            case R.id.bottom_menu_info:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, CourseFragment.getInstance(),R.id.main_frame);
                break;
            case R.id.bottom_menu_study:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, StudyFragment.getInstance(),R.id.main_frame);
                break;
            case R.id.bottom_menu_about:
                FragmentUtils.replaceFragmentToActivity(fragmentManager, AboutFragment.getInstance(),R.id.main_frame);
                break;
        }
    }


}
