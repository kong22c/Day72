package com.example.day72.Model;

import com.example.day72.UserCallBack;
import com.example.day72.base.BaseApp;
import com.example.day72.base.BaseModel;
import com.example.day72.bean.User;
import com.example.day72.db.UserDao;

import java.util.List;

public class UserModel extends BaseModel {
    public void getData(UserCallBack<User> callBack){
        try {
            UserDao userDao = BaseApp.getInstance().getDaoSession().getUserDao();
            List<User> users = userDao.loadAll();
            callBack.onSucess(users);
        } catch (Exception e) {
            e.printStackTrace();
            callBack.onFain("数据库为空");
        }

    }
}
