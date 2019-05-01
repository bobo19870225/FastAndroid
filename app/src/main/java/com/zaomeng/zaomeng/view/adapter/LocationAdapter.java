package com.zaomeng.zaomeng.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.model.repository.dataBase.Address;

import java.util.List;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class LocationAdapter extends RecyclerView.Adapter<LocationViewHolder> {
    private List<Address> list;
    private OnItemClick<Address> onItemClick;

    public void setList(List<Address> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick<Address> onItemClick) {
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
        return list == null ? 0 : list.size();
    }
}
