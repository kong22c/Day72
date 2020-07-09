package com.example.day72;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.day72.base.BaseApp;
import com.example.day72.bean.User;
import com.example.day72.db.UserDao;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResActivity extends AppCompatActivity {

    @BindView(R.id.iv_res_img)
    ImageView ivResImg;
    @BindView(R.id.ed_res_name)
    EditText edResName;
    @BindView(R.id.ed_res_pass)
    EditText edResPass;
    @BindView(R.id.ed_res_rpass)
    EditText edResRpass;
    @BindView(R.id.btn_res_res)
    Button btnResRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
        ButterKnife.bind(this);
        Glide.with(this).load("/storage/emulated/legacy/b.png").into(ivResImg);
    }

    @OnClick(R.id.btn_res_res)
    public void onViewClicked() {
        String name = edResName.getText().toString();
        String rpass = edResRpass.getText().toString();
        String pass = edResPass.getText().toString();
        if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pass)&&!TextUtils.isEmpty(rpass)){
            if (rpass.equals(pass)){
                UserDao userDao = BaseApp.getInstance().getDaoSession().getUserDao();
                userDao.insertOrReplace(new User(null,name,pass,"/storage/emulated/legacy/b.png"));
                finish();
            }else {
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "用户名与密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }
}
