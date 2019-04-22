package com.jinkan.www.fastandroid.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.repository.http.bean.NavigatorBean;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsTitleViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private ImageView add;

    private GoodsTitleViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        add = itemView.findViewById(R.id.add);
    }

    public static GoodsTitleViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods, parent, false);
        return new GoodsTitleViewHolder(view);
    }

    void bind(Item<NavigatorBean.GoodsListBean> goods, OnItemClick onItemClick, OnItemClick onAddClick) {
        goodsName.setText(goods.getData().getObjectName());
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
