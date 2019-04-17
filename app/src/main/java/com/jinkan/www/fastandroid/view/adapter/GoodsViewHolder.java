package com.jinkan.www.fastandroid.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    void bind(Goods goods, OnItemClick onItemClick, OnItemClick onAddClick) {
        goodsName.setText(goods.getGoodsName());
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, goods);
        });
        add.setOnClickListener(v -> {
            if (onAddClick != null)
                onAddClick.onClick(v, goods);
        });
    }

}
