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
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import java.util.List;
import java.util.Locale;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class OrderViewHolder extends RecyclerView.ViewHolder {
    //    private TextView goodsName;
    //    private TextView time;
//    private TextView price;
//    private TextView totalPrice;
    private TextView qty;
    private TextView pay;
    private TextView cancel;

    private TextView orderState;
    private TextView orderNo;

    private ImageView goodsIcon;
    private ImageView goodsIcon1;
    private ImageView goodsIcon2;
    private ImageView more;
    private OrderViewHolder(@NonNull View itemView) {
        super(itemView);
//        goodsName = itemView.findViewById(R.id.goods_name);
//        time = itemView.findViewById(R.id.order_time);
//        price = itemView.findViewById(R.id.price);
//        totalPrice = itemView.findViewById(R.id.total_price);
        qty = itemView.findViewById(R.id.qty);
        pay = itemView.findViewById(R.id.pay);
        cancel = itemView.findViewById(R.id.cancel);
        goodsIcon = itemView.findViewById(R.id.image);
        goodsIcon1 = itemView.findViewById(R.id.image1);
        goodsIcon2 = itemView.findViewById(R.id.image2);
        more = itemView.findViewById(R.id.more);
        orderState = itemView.findViewById(R.id.order_state);
        orderNo = itemView.findViewById(R.id.order_no);
    }

    public static OrderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    void bind(OrderBean orderBean,
              OnItemClick<OrderBean> onItemClick,//详情
              OnItemClick<OrderBean> onItemPayClick,//付款
              OnItemClick<OrderBean> onItemConfirmClick,//确认
              OnItemClick<OrderBean> onItemReturnClick,//退货
              OnItemClick<OrderBean> onItemCancelClick,//取消
              OnItemClick<OrderBean> onItemAfterSaleClick//取消
    ) {
        List<OrderBean.GoodsListBean> goodsList = orderBean.getGoodsList();
        if (goodsList != null) {
            if (goodsList.size() < 3) {
                more.setVisibility(View.GONE);
            } else {
                more.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < goodsList.size(); i++) {
                OrderBean.GoodsListBean goodsListBean = goodsList.get(i);
                if (i == 0) {
                    Glide.with(goodsIcon).load(goodsListBean.getListImage()).into(goodsIcon);
                } else if (i == 1) {
                    Glide.with(goodsIcon1).load(goodsListBean.getListImage()).into(goodsIcon1);
                } else if (i == 2) {
                    Glide.with(goodsIcon2).load(goodsListBean.getListImage()).into(goodsIcon2);
                } else {
                    break;
                }
            }
            qty.setText(String.format(Locale.CHINA, "共%d件商品", orderBean.getGoodsNumbers()));
//            goodsName.setText(goodsListBean.getGoodsName());

//            price.setText(FormatUtils.numberFormatMoney(goodsListBean.getPriceNow()));
//            totalPrice.setText(String.format("合计：%s", FormatUtils.numberFormatMoney(goodsListBean.getPriceTotal())));
//            time.setText(String.valueOf(orderBean.getApplyTime()));
            String strStatus = "";
            int status = orderBean.getStatus();
            switch (status) {
                case 1:
                    strStatus = "待付款";
                    pay.setText("去付款");
                    cancel.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    strStatus = "已取消";
                    pay.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    break;
                case 4:
                    strStatus = "已付款";
                    pay.setVisibility(View.VISIBLE);
                    pay.setText("退单");
                    cancel.setVisibility(View.GONE);
                    break;
                case 5:
                    strStatus = "已退款";
                    pay.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    break;
                case 6:
                    strStatus = "待签收";
                    pay.setVisibility(View.VISIBLE);
                    pay.setText("确认收货");
                    cancel.setVisibility(View.GONE);
                    break;
                case 7:
                    strStatus = "已退货";
                    pay.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    break;
                case 8:
                    strStatus = "已签收";
                    pay.setVisibility(View.VISIBLE);
                    pay.setText("退换商品");
                    cancel.setVisibility(View.GONE);
                    break;
                case 9:
                    strStatus = "已完成";
                    pay.setVisibility(View.VISIBLE);
                    pay.setText("售后");
                    cancel.setVisibility(View.GONE);
                    break;
                case 10:
                    strStatus = "待发货";
                    pay.setVisibility(View.VISIBLE);
                    pay.setText("退单");
                    cancel.setVisibility(View.GONE);
                    break;
                case 11:
                    strStatus = "已拒收";
                    pay.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    break;
                case 12:
                    strStatus = "已送达";
                    pay.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    break;
                case 14:
                    strStatus = "退款中";
                    pay.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);
                    break;
            }
            orderState.setText(strStatus);
            orderNo.setText(orderBean.getOrderCode());
            pay.setOnClickListener(v -> {
                switch (status) {
                    case 1:
                        if (onItemPayClick != null)
                            onItemPayClick.onClick(v, orderBean, getLayoutPosition());
                        break;
                    case 4:
                        if (onItemReturnClick != null)
                            onItemReturnClick.onClick(v, orderBean, getLayoutPosition());
                        break;
                    case 6:
                    case 10:
                        if (onItemConfirmClick != null)
                            onItemConfirmClick.onClick(v, orderBean, getLayoutPosition());
                        break;
                    case 8:
                        break;
                    case 9:
                        if (onItemAfterSaleClick != null) {
                            onItemAfterSaleClick.onClick(v, orderBean, getLayoutPosition());
                        }
                        break;
                }

            });
            cancel.setOnClickListener(v -> {
                if (onItemCancelClick != null)
                    onItemCancelClick.onClick(v, orderBean, getLayoutPosition());
            });

        }
        itemView.setOnClickListener(v -> {
            if (onItemClick != null) {
                onItemClick.onClick(v, orderBean, getLayoutPosition());
            }
        });
    }

}
