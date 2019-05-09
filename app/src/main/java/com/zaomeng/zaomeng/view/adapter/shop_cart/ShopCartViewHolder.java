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
    private TextView specifications;
    private TextView price;
    private TextView number;
    private View add;
    private View reduce;
    private ImageView goodsIcon;
    private ImageView select;

    private ShopCartViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        specifications = itemView.findViewById(R.id.specifications);
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

    void bind(ShopCartBean shopCartBean,
              OnItemClick<ShopCartBean> onSelectClick,
              OnItemClick<ShopCartBean> onAddClick,
              OnItemClick<ShopCartBean> onReduceClick) {
        goodsName.setText(shopCartBean.getName());
        specifications.setText(shopCartBean.getObjectFeatureItemName1());
        Glide.with(goodsIcon).load(shopCartBean.getLittleImage()).into(goodsIcon);
        price.setText(FormatUtils.numberFormatMoney(shopCartBean.getPriceNow()));
        number.setText(String.valueOf(shopCartBean.getQty()));
        if (shopCartBean.getIsSelected() == 1) {
            select.setImageResource(R.mipmap.selected);
        } else {
            select.setImageResource(R.mipmap.un_select);
        }
        select.setOnClickListener(v -> {
//            if (actionSelect != null) {
//                actionSelect.invoke();
//            }
            if (onSelectClick != null)
                onSelectClick.onClick(v, shopCartBean, getAdapterPosition());
        });
        add.setOnClickListener(v -> {

            int n = Integer.valueOf(number.getText().toString());
            n += 1;
            number.setText(String.valueOf(n));
            shopCartBean.setQty(n);
            if (onAddClick != null) {
                onAddClick.onClick(v, shopCartBean, getLayoutPosition());
            }
        });
        reduce.setOnClickListener(v -> {

            int n = Integer.parseInt(number.getText().toString());
            n -= 1;
            if (n < 0) {
                n = 0;
            }
            number.setText(String.valueOf(n));
            shopCartBean.setQty(n);
            if (onReduceClick != null) {
                onReduceClick.onClick(v, shopCartBean, getLayoutPosition());
            }
        });
    }

}
