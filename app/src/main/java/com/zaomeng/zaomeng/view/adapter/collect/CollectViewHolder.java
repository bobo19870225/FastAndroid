package com.zaomeng.zaomeng.view.adapter.collect;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class CollectViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView price;
    private ImageView add;
    private ImageView goodsIcon;

    private CollectViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        price = itemView.findViewById(R.id.price);
        add = itemView.findViewById(R.id.add);
        goodsIcon = itemView.findViewById(R.id.icon_goods);
    }

    public static CollectViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods, parent, false);
        return new CollectViewHolder(view);
    }

    void bind(CollectInfoBean collectInfoBean, OnItemClick<CollectInfoBean> onItemClick, OnItemClick<CollectInfoBean> onAddClick) {
        goodsName.setText(collectInfoBean.getObjectName());
        price.setText(FormatUtils.numberFormatMoney(collectInfoBean.getShowPrice()));
        Glide.with(goodsIcon).load(collectInfoBean.getListImage()).into(goodsIcon);
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, collectInfoBean, getLayoutPosition());
        });
        add.setOnClickListener(v -> {
            if (onAddClick != null)
                onAddClick.onClick(v, collectInfoBean, getLayoutPosition());
        });
    }

}
