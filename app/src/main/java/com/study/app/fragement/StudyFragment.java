package com.study.app.fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.study.app.R;

/***
 * 图片解析界面
 *
 * */
public class StudyFragment extends Fragment {

    public static final int REQUEST_TAKE_PHOTO_CODE = 101;

    ImageView mSelectImg;
    TextView mDecodeTv;
    Button mSelecLocalPicBtn;
    Button mTakePicBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragement_decode, container, false);
        initView(view);
        return view;
    }

    public static StudyFragment getInstance() {
        return new StudyFragment();
    }

    public void initView(View view){
        mSelectImg = view.findViewById(R.id.select_pic_img);
        mDecodeTv = view.findViewById(R.id.decode_res_tv);
        mSelecLocalPicBtn = view.findViewById(R.id.select_pic_local);
        mTakePicBtn = view.findViewById(R.id.select_pic_photo);

        mSelecLocalPicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

    };

    public void initData(){

    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }


}
