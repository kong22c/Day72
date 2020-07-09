package com.example.day72.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    public P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initPresenter();
        if (mPresenter!=null){
            mPresenter.bindView(this);
        }
        initView();
        initData();
        initLister();
    }

    protected abstract void initLister();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract int getLayout();


}
