package com.zaomeng.zaomeng.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private ImageView add;
    private GoodsViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        add = itemView.findViewById(R.id.add);
    }

    public static GoodsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods, parent, false);
        return new GoodsViewHolder(view);
    }

    void bind(GoodsListRowsBean goods, OnItemClick onItemClick, OnItemClick onAddClick) {
        goodsName.setText(goods.getName());
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, goods, getLayoutPosition());
        });
        add.setOnClickListener(v -> {
            if (onAddClick != null)
                onAddClick.onClick(v, goods, getLayoutPosition());
        });
    }

}
