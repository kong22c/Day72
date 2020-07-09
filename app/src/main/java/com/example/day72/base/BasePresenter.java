package com.example.day72.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    public V mview;
    public ArrayList<BaseModel>baseModels=new ArrayList<>();
    public BasePresenter(){initMode1();}

    protected abstract void initMode1();
    public void bindView(V view){
        mview=view;
    }
    public void addModel(BaseModel baseModel){
        if (baseModel==null){
            baseModels=new ArrayList<>();
        }
        baseModels.add(baseModel);
    }

}
