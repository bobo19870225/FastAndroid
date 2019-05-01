package com.zaomeng.zaomeng.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.dataBase.Address;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class LocationViewHolder extends RecyclerView.ViewHolder {
    private TextView location;

    private LocationViewHolder(@NonNull View itemView) {
        super(itemView);
        location = itemView.findViewById(R.id.location);
    }

    public static LocationViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location, parent, false);
        return new LocationViewHolder(view);
    }

    void bind(Address address, OnItemClick<Address> onItemClick) {
        location.setText(address.NAME);
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, address, getLayoutPosition());
        });

    }

}
