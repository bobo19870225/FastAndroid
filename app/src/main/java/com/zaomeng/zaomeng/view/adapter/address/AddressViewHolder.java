package com.zaomeng.zaomeng.view.adapter.address;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class AddressViewHolder extends RecyclerView.ViewHolder {
    private TextView address;
    private TextView name;
    private TextView phone;

    private AddressViewHolder(@NonNull View itemView) {
        super(itemView);
        address = itemView.findViewById(R.id.address);
        name = itemView.findViewById(R.id.name);
        phone = itemView.findViewById(R.id.phone);

    }

    public static AddressViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_address, parent, false);
        return new AddressViewHolder(view);
    }

    void bind(MemberShopBean memberShopBean, OnItemClick<MemberShopBean> onSelectClick) {
        address.setText(memberShopBean.getAddress());
        name.setText(memberShopBean.getContact());
        phone.setText(memberShopBean.getContactPhone());
    }

}
