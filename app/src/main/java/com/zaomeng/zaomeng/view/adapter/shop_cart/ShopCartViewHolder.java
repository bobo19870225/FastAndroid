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

import kotlin.jvm.functions.Function0;

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
    private ImageView select;

    private ShopCartViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        price = itemView.findViewById(R.id.price);
        number = itemView.findViewById(R.id.number);
        add = itemView.findViewById(R.id.add);
        reduce = itemView.findViewById(R.id.reduce);
        goodsIcon = itemView.findViewById(R.id.icon_goods);
        select = itemView.findViewById(R.id.select);
    }

    public static ShopCartViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop_cart, parent, false);
        return new ShopCartViewHolder(view);
    }

    void bind(ShopCartBean goods,
              OnItemClick<ShopCartBean> onSelectClick,
              Function0 actionSelect,
              OnItemClick<ShopCartBean> onAddClick,
              Function0 actionAdd,
              OnItemClick<ShopCartBean> onReduceClick,
              Function0 actionReduce,
              Boolean isCheckedHasMap) {
        goodsName.setText(goods.getObjectFeatureItemName1());
        Glide.with(goodsIcon).load(goods.getLittleImage()).into(goodsIcon);
        price.setText(FormatUtils.numberFormatMoney(goods.getPriceNow()));
        number.setText(String.valueOf(goods.getQty()));
        if (isCheckedHasMap != null && isCheckedHasMap) {
            select.setImageResource(R.mipmap.selected);
        } else {
            select.setImageResource(R.mipmap.un_select);
        }
        select.setOnClickListener(v -> {
            if (actionSelect != null)
                actionSelect.invoke();
            if (onSelectClick != null)
                onSelectClick.onClick(v, goods, getAdapterPosition());
        });
        add.setOnClickListener(v -> {
            if (actionAdd != null) {
                actionAdd.invoke();
            }
            int n = Integer.valueOf(number.getText().toString());
            n += 1;
            number.setText(String.valueOf(n));
            goods.setQty(n);
            if (onAddClick != null) {
                onAddClick.onClick(v, goods, getLayoutPosition());
            }
        });
        reduce.setOnClickListener(v -> {
            if (actionReduce != null) {
                actionReduce.invoke();
            }
            int n = Integer.parseInt(number.getText().toString());
            n -= 1;
            if (n < 0) {
                n = 0;
            }
            number.setText(String.valueOf(n));
            goods.setQty(n);
            if (onReduceClick != null) {
                onReduceClick.onClick(v, goods, getLayoutPosition());
            }
        });
    }

}
