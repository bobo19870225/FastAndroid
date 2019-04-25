package com.jinkan.www.fastandroid.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.repository.http.bean.NavigatorBean;
import com.jinkan.www.fastandroid.utils.FormatUtils;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsGridViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView price;
    private TextView specifications;
    private ImageView add;
    private ImageView icon_goods;

    private GoodsGridViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        price = itemView.findViewById(R.id.price);
        specifications = itemView.findViewById(R.id.specifications);
        add = itemView.findViewById(R.id.add);
        icon_goods = itemView.findViewById(R.id.icon_goods);
    }

    public static GoodsGridViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods_2, parent, false);
        return new GoodsGridViewHolder(view);
    }

    void bind(Item<NavigatorBean.GoodsListBean> goods, OnItemClick onItemClick, OnItemClick<NavigatorBean.GoodsListBean> onAddClick, int position) {
        NavigatorBean.GoodsListBean data = goods.getData();
        goodsName.setText(data.getObjectName());
        price.setText(FormatUtils.numberFormatMoney(data.getShowPrice()));
//        specifications.setText(String.valueOf(data.getStockNumber()));
        Glide.with(icon_goods).load(data.getListImage()).into(icon_goods);
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, goods.getData(), position);
        });
        add.setOnClickListener(v -> {
            if (onAddClick != null)
                onAddClick.onClick(v, goods.getData(), position);
        });
    }

}
