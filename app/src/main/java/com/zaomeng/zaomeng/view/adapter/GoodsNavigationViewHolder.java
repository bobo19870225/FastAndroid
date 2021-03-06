package com.zaomeng.zaomeng.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;

/**
 * Created by Sampson on 2019-04-23.
 * FastAndroid
 */
class GoodsNavigationViewHolder extends RecyclerView.ViewHolder {
    private TextView navigationName;
    private TextView more;

    private GoodsNavigationViewHolder(@NonNull View itemView) {
        super(itemView);
        navigationName = itemView.findViewById(R.id.navigation_name);
        more = itemView.findViewById(R.id.more);
    }

    public static GoodsNavigationViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods_navigation, parent, false);
        return new GoodsNavigationViewHolder(view);
    }

    void bind(Item<String> item, OnItemClick onItemClick) {
        String data = item.getData();
        navigationName.setText(data);
        if (data.equals("推荐")) {
            more.setVisibility(View.GONE);
        } else {
            more.setVisibility(View.VISIBLE);
        }
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, data, getLayoutPosition());
        });

    }
}
