package com.zaomeng.zaomeng.view.adapter.shop_type;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class ShopTypeViewHolder extends RecyclerView.ViewHolder {
    private TextView name;

    private ShopTypeViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.location);

    }

    public static ShopTypeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location, parent, false);
        return new ShopTypeViewHolder(view);
    }

    void bind(GoodsSuperBean goodsSuperBean, OnItemClick<GoodsSuperBean> onSelectClick) {
        name.setText(goodsSuperBean.getName());
        itemView.setOnClickListener(v -> {
            if (onSelectClick != null) {
                onSelectClick.onClick(v, goodsSuperBean, getLayoutPosition());
            }
        });
    }

}
