package com.example.day72.adaoter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.day72.R;
import com.example.day72.bean.User;


import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    private ArrayList<User>list;
    private OnItemLongClickLister onItemLongClickLister;

    public void setOnItemLongClickLister(OnItemLongClickLister onItemLongClickLister) {
        this.onItemLongClickLister = onItemLongClickLister;
    }

    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.itme_home, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg()).into(holder.iv_img);
        holder.tv_name.setText(list.get(position).getName());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickLister.onItmLongClick(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img=itemView.findViewById(R.id.iv_img);
            tv_name=itemView.findViewById(R.id.tv_name);
        }
    }
    public interface OnItemLongClickLister{
        void onItmLongClick(int position);
    }
}
