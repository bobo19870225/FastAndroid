package com.zaomeng.zaomeng.view.adapter.member_shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class MemberShopViewHolder extends RecyclerView.ViewHolder {
    private TextView shopName;
    private TextView shopAddress;
    private TextView shopConsignee;
    private TextView phone;
    private ImageView edit;
    private ImageView delete;
    private MemberShopViewHolder(@NonNull View itemView) {
        super(itemView);
        shopName = itemView.findViewById(R.id.shop_name);
        shopAddress = itemView.findViewById(R.id.shop_address);
        shopConsignee = itemView.findViewById(R.id.shop_consignee);
        phone = itemView.findViewById(R.id.phone);
        edit = itemView.findViewById(R.id.edit);
        delete = itemView.findViewById(R.id.delete);
    }

    public static MemberShopViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_member_shop, parent, false);
        return new MemberShopViewHolder(view);
    }

    void bind(MemberShopBean memberShopBean,
              OnItemClick<MemberShopBean> onItemClick,
              OnItemClick<MemberShopBean> onEditClick,
              OnItemClick<MemberShopBean> onDeleteClick) {
        shopName.setText(memberShopBean.getName());
        shopAddress.setText(memberShopBean.getAddress());
        shopConsignee.setText(memberShopBean.getContact());
        phone.setText(memberShopBean.getContactPhone());
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, memberShopBean, getLayoutPosition());
        });
        edit.setOnClickListener(v -> {
            if (onEditClick != null)
                onEditClick.onClick(v, memberShopBean, getLayoutPosition());
        });
        delete.setOnClickListener(v -> {
            if (onDeleteClick != null)
                onDeleteClick.onClick(v, memberShopBean, getLayoutPosition());

        });
    }

}
