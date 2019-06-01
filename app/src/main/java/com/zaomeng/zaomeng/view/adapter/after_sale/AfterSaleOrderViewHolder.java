package com.zaomeng.zaomeng.view.adapter.after_sale;

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
import com.zaomeng.zaomeng.view.custom_view.RecyclerViewForScrollView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;
import java.util.Locale;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class AfterSaleOrderViewHolder extends RecyclerView.ViewHolder {
    private TextView time;
    private TextView orderNo;
    private RecyclerViewForScrollView recyclerViewForScrollView;

    private AfterSaleOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.order_time);
        orderNo = itemView.findViewById(R.id.order_no);
        recyclerViewForScrollView = itemView.findViewById(R.id.listGoods);
    }

    public static AfterSaleOrderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order_title, parent, false);
        return new AfterSaleOrderViewHolder(view);
    }

    void bind(OrderBean orderBean, OnItemClick<OrderBean.GoodsListBean> onItemReturnClick) {//退货
        List<OrderBean.GoodsListBean> goodsList = orderBean.getGoodsList();
        if (goodsList != null) {
            time.setText(String.valueOf(orderBean.getApplyTimeStr()));
            orderNo.setText(orderBean.getOrderCode());
            recyclerViewForScrollView.setAdapter(new CommonAdapter<OrderBean.GoodsListBean>(recyclerViewForScrollView.getContext(), R.layout.item_order_goods, goodsList) {
                @Override
                protected void convert(ViewHolder holder, OrderBean.GoodsListBean goodsListBean, int position) {
                    View itemView = holder.itemView;
                    ImageView goodsIcon = itemView.findViewById(R.id.icon_goods);
                    Glide.with(goodsIcon).load(goodsListBean.getListImage()).into(goodsIcon);
                    TextView goodsName = itemView.findViewById(R.id.goods_name);
                    goodsName.setText(goodsListBean.getGoodsName());
                    TextView specifications = itemView.findViewById(R.id.specifications);
                    specifications.setText(goodsListBean.getObjectFeatureItemName1());
                    TextView qty = itemView.findViewById(R.id.qty);
                    qty.setText(String.format(Locale.CHINA, "数量：%d", goodsListBean.getQty()));
                    TextView textViewReturn = itemView.findViewById(R.id.textViewReturn);
                    int status = goodsListBean.getStatus();

                    switch (status) {
                        case 2:
                            textViewReturn.setText("申请退货中...");
                            textViewReturn.setBackground(textViewReturn.getContext().getResources().getDrawable(R.drawable.bg_circular_gray));
                            break;
                        case 3:
                            textViewReturn.setText("已退货");
                            textViewReturn.setBackground(textViewReturn.getContext().getResources().getDrawable(R.drawable.bg_circular_gray));
                            break;
                        default:
                            textViewReturn.setBackground(textViewReturn.getContext().getResources().getDrawable(R.drawable.button_them_color_un_select));
                            textViewReturn.setOnClickListener(v -> {
                                if (onItemReturnClick != null) {
                                    onItemReturnClick.onClick(v, goodsListBean, getLayoutPosition());
                                }
                            });
                            break;
                    }

                }

            });
        }
    }

}
