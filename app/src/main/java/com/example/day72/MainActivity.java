package com.example.day72;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.day72.base.BaseApp;
import com.example.day72.bean.User;
import com.example.day72.db.UserDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_pass)
    EditText edPass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_res)
    Button btnRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_res})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String name = edName.getText().toString();
                String pass = edPass.getText().toString();
                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(pass)){
                    UserDao userDao = BaseApp.getInstance().getDaoSession().getUserDao();
                    List<User> list = userDao.queryBuilder().where(UserDao.Properties.Name.eq(name), UserDao.Properties.Pass.eq(pass)).list();
                    if (list.size()>0){
                        startActivity(new Intent(this,HomeActivity.class));
                    }else {
                        Toast.makeText(this, "用户名与密码不存在", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "用户名与密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_res:
                Intent intent = new Intent(this, ResActivity.class);
                startActivity(intent);
                break;
        }
    }
}
