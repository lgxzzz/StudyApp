package com.study.app.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.study.app.MyPingJiaActivity;
import com.study.app.R;
import com.study.app.bean.Course;
import com.study.app.bean.Evalute;
import com.study.app.bean.User;
import com.study.app.data.DBManger;
import com.study.app.util.CommonAdapter;
import com.study.app.util.DensityUtils;
import com.study.app.util.ViewHolder;

import org.xutils.DbManager;
import org.xutils.db.table.DbBase;

import java.util.List;



/**
 * Created
 */
public class EvaluteFragment extends Fragment {

    ListView listView;
    FloatingActionButton fab;

    private CommonAdapter<Evalute> adapter;
    private Course course;
    private String user;
    private List<Evalute> mlist;
    User userBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_evalute, null);
        initView(view);
        Bundle bundle = getArguments();
        course = (Course) bundle.getSerializable("DATA");
        userBean = DBManger.getInstance(getContext()).mUser;
        String[] args = new String[]{user};
        initData();

        return view;
    }

    private void initData() {
        mlist = DBManger.getInstance(getActivity()).getEvalutesByCourseId(course.getCOURSE_ID());

        adapter = new CommonAdapter<Evalute>(getActivity(), mlist, R.layout.item_mypingjia_layout) {
            @Override
            public void convert(ViewHolder holder, Evalute m) {
                User user = DBManger.getInstance(getContext()).getUserByID(m.getUSER_ID());
                holder.setText(R.id.tv_name_myPingJia, user.getUserName())

//                        .setRatingBar(R.id.ratingBar_myPingJia, Float.parseFloat(m.getRatbar()))
                        .setText(R.id.conetent, m.getEVA_CONTENT())
                        .setText(R.id.tv_time_myPingJia, m.getEVA_TIME());

//                if (TextUtils.isEmpty(userBean.getUrl())||userBean.getUrl().equals("null")) {
//                    holder.setImageResource(R.id.iv_icon_myPingJia,R.drawable.mine_image1);
//
//                } else {
//                    holder.setImgUrl(R.id.iv_icon_myPingJia,userBean.getUrl());
//                }


            }
        };
        listView.setAdapter(adapter);
        DensityUtils.setListViewHeightBasedOnChildren(listView);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
       getActivity().runOnUiThread(new Runnable() {
           @Override
           public void run() {
               User user = DBManger.getInstance(getContext()).mUser;
               mlist = DBManger.getInstance(getActivity()).getEvalutesByCourseId(course.getCOURSE_ID());
               adapter.updateAll(mlist);
           }
       });
    }

    public void initView(View view){

        listView = view.findViewById(R.id.lv_myPingJia);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyPingJiaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", course);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
