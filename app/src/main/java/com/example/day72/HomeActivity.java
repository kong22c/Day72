package com.example.day72;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.day72.adaoter.UserAdapter;
import com.example.day72.base.BaseActivity;
import com.example.day72.base.BaseApp;
import com.example.day72.bean.User;
import com.example.day72.db.UserDao;
import com.example.day72.presenter.UserPresenter;
import com.example.day72.view.UserView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity<UserPresenter> implements UserView, UserAdapter.OnItemLongClickLister {


    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<User> list;
    private UserAdapter adapter;
    private int posi;

    @Override
    protected void initLister() {

    }

    @Override
    protected void initData() {
mPresenter.getData();
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new UserAdapter(this, list);
        rv.setAdapter(adapter);
        adapter.setOnItemLongClickLister(this);
        registerForContextMenu(rv);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new UserPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void setData(List<User> users) {
        list.clear();
            list.addAll(users);
            adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {

    }



    @Override
    public void onItmLongClick(int position) {
        posi=position;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"删除");
        menu.add(0,1,0,"修改");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 0:
                UserDao userDao = BaseApp.getInstance().getDaoSession().getUserDao();
                userDao.delete(list.get(posi));
                initData();
                break;
            case 1:
                pop();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void pop() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_pop, null);
        PopupWindow window = new PopupWindow(inflate, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.WRAP_CONTENT);
        EditText ed_pop_name = inflate.findViewById(R.id.ed_pop_name);
        ed_pop_name.setText(list.get(posi).getName());
        Button ok = inflate.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_pop_name.getText().toString();
                list.get(posi).setName(name);
                UserDao userDao = BaseApp.getInstance().getDaoSession().getUserDao();
                userDao.update(list.get(posi));
                initData();
                window.dismiss();
            }
        });

        window.setFocusable(true);
        window.setBackgroundDrawable(new ColorDrawable());
        window.setOutsideTouchable(true);
        window.showAtLocation(rv, Gravity.CENTER,0,0);

    }
}
