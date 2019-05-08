package com.zaomeng.zaomeng.view.adapter.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.OrderBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import java.util.List;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class OrderViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView time;
    private TextView price;
    private TextView totalPrice;
    private TextView qty;
    private TextView pay;

    private ImageView goodsIcon;

    private OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        time = itemView.findViewById(R.id.order_time);
        price = itemView.findViewById(R.id.price);
        totalPrice = itemView.findViewById(R.id.total_price);
        qty = itemView.findViewById(R.id.qty);
        pay = itemView.findViewById(R.id.pay);
        goodsIcon = itemView.findViewById(R.id.image);
    }

    public static OrderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    void bind(OrderBean orderBean, OnItemClick<OrderBean> onItemClick) {
        List<OrderBean.GoodsListBean> goodsList = orderBean.getGoodsList();
        if (goodsList != null) {
            OrderBean.GoodsListBean goodsListBean = goodsList.get(0);
            goodsName.setText(goodsListBean.getGoodsName());
            Glide.with(goodsIcon).load(goodsListBean.getListImage()).into(goodsIcon);
            price.setText(FormatUtils.numberFormatMoney(goodsListBean.getPriceNow()));
            totalPrice.setText(FormatUtils.numberFormatMoney(goodsListBean.getPriceTotal()));
            time.setText(String.valueOf(orderBean.getApplyTime()));
            pay.setOnClickListener(v -> {
                if (onItemClick != null)
                    onItemClick.onClick(v, orderBean, getLayoutPosition());
            });
            qty.setText(String.valueOf(goodsListBean.getQty()));
        }

    }

}
