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
    private TextView cancel;

    private TextView orderState;
    private TextView orderNo;

    private ImageView goodsIcon;

    private OrderViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        time = itemView.findViewById(R.id.order_time);
        price = itemView.findViewById(R.id.price);
        totalPrice = itemView.findViewById(R.id.total_price);
        qty = itemView.findViewById(R.id.qty);
        pay = itemView.findViewById(R.id.pay);
        cancel = itemView.findViewById(R.id.cancel);
        goodsIcon = itemView.findViewById(R.id.image);
        orderState = itemView.findViewById(R.id.order_state);
        orderNo = itemView.findViewById(R.id.order_no);
    }

    public static OrderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    void bind(OrderBean orderBean, OnItemClick<OrderBean> onItemClick, OnItemClick<OrderBean> onItemCancelClick) {
        List<OrderBean.GoodsListBean> goodsList = orderBean.getGoodsList();
        if (goodsList != null) {
            OrderBean.GoodsListBean goodsListBean = goodsList.get(0);
            goodsName.setText(goodsListBean.getGoodsName());
            Glide.with(goodsIcon).load(goodsListBean.getListImage()).into(goodsIcon);
            price.setText(FormatUtils.numberFormatMoney(goodsListBean.getPriceNow()));
            totalPrice.setText(FormatUtils.numberFormatMoney(goodsListBean.getPriceTotal()));
            time.setText(String.valueOf(orderBean.getApplyTime()));
            String strStatus = "";
            int status = orderBean.getStatus();
            switch (status) {
                case 1:
                    strStatus = "待付款";
                    pay.setText("去支付");
                    cancel.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    strStatus = "已取消";
                    pay.setText("再次购买");
                    cancel.setVisibility(View.GONE);
                    break;
                case 4:
                    strStatus = "已付款";
                    pay.setText("再次购买");
                    cancel.setVisibility(View.GONE);
                    break;
                case 6:
                    strStatus = "待签收";
                    pay.setText("再次购买");
                    cancel.setVisibility(View.GONE);
                    break;
                case 8:
                    strStatus = "已签收";
                    pay.setText("再次购买");
                    cancel.setVisibility(View.GONE);
                    break;
                case 9:
                    strStatus = "已完成";
                    pay.setText("再次购买");
                    cancel.setVisibility(View.GONE);
                    break;
                case 10:
                    strStatus = "待发货";
                    pay.setText("再次购买");
                    cancel.setVisibility(View.GONE);
                    break;
            }
            orderState.setText(strStatus);
            orderNo.setText(orderBean.getOrderCode());
            pay.setOnClickListener(v -> {
                if (onItemClick != null)
                    onItemClick.onClick(v, orderBean, getLayoutPosition());
            });
            cancel.setOnClickListener(v -> {
                if (onItemCancelClick != null)
                    onItemCancelClick.onClick(v, orderBean, getLayoutPosition());
            });
            qty.setText(String.valueOf(goodsListBean.getQty()));
        }

    }

}
