package com.zaomeng.zaomeng.view.adapter.after_sale;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.AfterSaleBean;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;
import com.zaomeng.zaomeng.view.custom_view.RoundRectImageView;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class AfterSaleRecordViewHolder extends RecyclerView.ViewHolder {
    //    private TextView time;
    private TextView orderNo;
    private TextView state;
    private TextView goodsName;
    private TextView specifications;
    private TextView qty;
    private RelativeLayout relativeLayout;
    private RoundRectImageView roundRectImageView;

    private AfterSaleRecordViewHolder(@NonNull View itemView) {
        super(itemView);
//        time = itemView.findViewById(R.id.order_time);
        orderNo = itemView.findViewById(R.id.order_no);
        state = itemView.findViewById(R.id.state);
        goodsName = itemView.findViewById(R.id.goods_name);
        specifications = itemView.findViewById(R.id.specifications);
        qty = itemView.findViewById(R.id.qty);
        relativeLayout = itemView.findViewById(R.id.rl_state);
        roundRectImageView = itemView.findViewById(R.id.icon_goods);
    }

    public static AfterSaleRecordViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_after_sale, parent, false);
        return new AfterSaleRecordViewHolder(view);
    }

    void bind(AfterSaleBean afterSaleBean, OnItemClick<AfterSaleBean> onItemClick) {//退货
//        time.setText(String.valueOf(afterSaleBean.getApplyTimeStr()));
        orderNo.setText(afterSaleBean.getReturnCode());
        switch (afterSaleBean.getStatus()) {
            case 1:
                state.setText("申请审核中");
                break;
            case 2:
                state.setText("已退款");
                break;
            case 3:
                state.setText("拒绝退货");
                break;
        }
        goodsName.setText(afterSaleBean.getGoodsShopName());
        specifications.setText(afterSaleBean.getObjectFeatureItemName1());
        qty.setText(String.format("件数：%s", String.valueOf(afterSaleBean.getQty())));
        Glide.with(roundRectImageView).load(afterSaleBean.getLittleImage()).into(roundRectImageView);
        relativeLayout.setOnClickListener(v -> {
            if (onItemClick != null) {
                onItemClick.onClick(v, afterSaleBean, getLayoutPosition());
            }
        });
    }


}
