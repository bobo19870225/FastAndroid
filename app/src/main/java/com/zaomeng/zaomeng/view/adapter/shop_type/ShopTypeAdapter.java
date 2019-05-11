package com.zaomeng.zaomeng.view.adapter.shop_type;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import java.util.List;

/**
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 */
public class ShopTypeAdapter extends RecyclerView.Adapter {
    private List<String> list;
    private OnItemClick<String> onItemClick;

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick<String> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ShopTypeViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ShopTypeViewHolder) holder).bind(list.get(position), onItemClick);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
