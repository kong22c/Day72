package com.example.day72;


import java.util.List;

public interface UserCallBack<T> {
    void onSucess(List<T>users);
    void onFain(String str);
}
