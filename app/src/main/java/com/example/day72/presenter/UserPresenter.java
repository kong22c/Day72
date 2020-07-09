package com.example.day72.presenter;

import com.example.day72.Model.UserModel;
import com.example.day72.UserCallBack;
import com.example.day72.base.BasePresenter;
import com.example.day72.bean.User;
import com.example.day72.view.UserView;

import java.util.List;

public class UserPresenter extends BasePresenter<UserView> {

    private UserModel userModel;

    @Override
    protected void initMode1() {
        userModel = new UserModel();
        addModel(userModel);
    }
    public void getData(){
        userModel.getData(new UserCallBack<User>() {
            @Override
            public void onSucess(List<User> users) {
                mview.setData(users);
            }

            @Override
            public void onFain(String str) {
                mview.showToast(str);
            }
        });
    }
}
