package com.zaomeng.zaomeng.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsHeaderViewHolder extends RecyclerView.ViewHolder {
    private ImageView commonlyUsed;
    private ImageView myOrder;
    private ImageView myBonus;
    private ImageView signIn;


    private GoodsHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        commonlyUsed = itemView.findViewById(R.id.commonly_used);
        myOrder = itemView.findViewById(R.id.my_order);
        myBonus = itemView.findViewById(R.id.my_bonus);
        signIn = itemView.findViewById(R.id.sign_in);
    }

    public static GoodsHeaderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header, parent, false);
        return new GoodsHeaderViewHolder(view);
    }

    void bind(OnItemClick<String> onItemClick) {
        commonlyUsed.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, "commonlyUsed", getLayoutPosition());
        });
        myOrder.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, "myOrder", getLayoutPosition());
        });
        myBonus.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, "myBonus", getLayoutPosition());
        });
        signIn.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, "signIn", getLayoutPosition());
        });
    }

}
