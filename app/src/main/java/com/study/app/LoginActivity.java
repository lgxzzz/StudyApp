package com.study.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.app.data.DBManger;


public class LoginActivity extends Activity implements View.OnClickListener{

    private TextView mRegisterView;
    private EditText mNameEd;
    private EditText mPassWordEd;
    private String mName;
    private String mPassWord;
    private Button mLoginBtn;
    private ImageView mPicCodeImg;
    private EditText mPicCodeEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

//        mName = "q";
//        mPassWord = "q";
//        login();
    }

    public void init(){

        mRegisterView = findViewById(R.id.login_to_register_btn);
        mRegisterView.setOnClickListener(this);

        mNameEd = findViewById(R.id.reg_name_ed);
        mPassWordEd = findViewById(R.id.reg_password_ed);

        mLoginBtn = findViewById(R.id.reg_login_btn);
        mLoginBtn.setOnClickListener(this);

        mNameEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mName = editable.toString();
            }
        });

        mPassWordEd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
               mPassWord = editable.toString();
            }
        });

        mPicCodeImg = findViewById(R.id.pic_code_img);

        mPicCodeEd = findViewById(R.id.pic_code_ed);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_to_register_btn:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.reg_login_btn:
                String codeStr = mPicCodeEd.getText().toString().trim();
//                if (null == codeStr || TextUtils.isEmpty(codeStr)) {
//                    Toast.makeText(LoginActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                login();
                break;
        }
    }

    public void login(){
        DBManger.getInstance(LoginActivity.this).login(mName, mPassWord, new DBManger.IListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(LoginActivity.this,"登陆成功", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                LoginActivity.this.finish();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(LoginActivity.this,error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
