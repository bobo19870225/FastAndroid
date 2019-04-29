package com.zaomeng.zaomeng.view.adapter.shop_cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class ShopCartViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView price;
    private TextView number;
    private View add;
    private View reduce;
    private ImageView goodsIcon;

    private ShopCartViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        price = itemView.findViewById(R.id.price);
        number = itemView.findViewById(R.id.number);
        add = itemView.findViewById(R.id.add);
        reduce = itemView.findViewById(R.id.reduce);
        goodsIcon = itemView.findViewById(R.id.icon_goods);
    }

    public static ShopCartViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop_cart, parent, false);
        return new ShopCartViewHolder(view);
    }

    void bind(ShopCartBean goods, OnItemClick<ShopCartBean> onItemClick, OnItemClick<ShopCartBean> onAddClick) {
        goodsName.setText(goods.getObjectFeatureItemName1());
        Glide.with(goodsIcon).load(goods.getLittleImage()).into(goodsIcon);
        price.setText(FormatUtils.numberFormatMoney(goods.getPriceNow()));
        number.setText(String.valueOf(goods.getQty()));
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
