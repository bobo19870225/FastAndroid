package com.zaomeng.zaomeng.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {
    private List<String> list;
    private OnItemClick onItemClick;

    public LocationAdapter(List<String> listLocation) {
        list = listLocation;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LocationViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.bind(list.get(position), onItemClick);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
