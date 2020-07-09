package com.example.day72.view;

import com.example.day72.base.BaseView;
import com.example.day72.bean.User;

import java.util.List;

public interface UserView extends BaseView {
    void setData(List<User>users);
}
