package com.zaomeng.zaomeng.view.adapter.member_shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class MemberShopViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView price;
    private ImageView add;
    private ImageView goodsIcon;

    private MemberShopViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        price = itemView.findViewById(R.id.price);
        add = itemView.findViewById(R.id.add);
        goodsIcon = itemView.findViewById(R.id.icon_goods);
    }

    public static MemberShopViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods, parent, false);
        return new MemberShopViewHolder(view);
    }

    void bind(MemberShopBean memberShopBean, OnItemClick<MemberShopBean> onItemClick, OnItemClick<MemberShopBean> onAddClick) {
        goodsName.setText(memberShopBean.getName());
        Glide.with(goodsIcon).load(memberShopBean.getContactIdCardFaceImage()).into(goodsIcon);
        price.setText(memberShopBean.getName());
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, memberShopBean, getLayoutPosition());
        });
        add.setOnClickListener(v -> {
            if (onAddClick != null)
                onAddClick.onClick(v, memberShopBean, getLayoutPosition());
        });
    }

}
