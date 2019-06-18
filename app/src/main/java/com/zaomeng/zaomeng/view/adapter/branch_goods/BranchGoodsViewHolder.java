package com.zaomeng.zaomeng.view.adapter.branch_goods;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class BranchGoodsViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private ImageView add;
    private ImageView goodsIcon;
    private TextView price;
    private TextView specifications;

    private BranchGoodsViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        add = itemView.findViewById(R.id.add);
        price = itemView.findViewById(R.id.price);
        specifications = itemView.findViewById(R.id.specifications);
        goodsIcon = itemView.findViewById(R.id.icon_goods);
    }

    public static BranchGoodsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods, parent, false);
        return new BranchGoodsViewHolder(view);
    }

    void bind(BranchGoodsBean goods, OnItemClick<BranchGoodsBean> onItemClick, OnItemClick<BranchGoodsBean> onAddClick) {
        goodsName.setText(goods.getObjectName());
        price.setText(FormatUtils.numberFormatMoney(goods.getShowPrice()));
        String unitDescription = goods.getUnitDescription();
        if (!FormatUtils.isStringNull(unitDescription))
            specifications.setText(String.format("规格：%s", unitDescription));
//        specifications.setText(String.format("规格：%s", goods.getUnitDescription()));
        Glide.with(goodsIcon).load(goods.getListImage()).into(goodsIcon);
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, goods, getLayoutPosition());
        });
        add.setOnClickListener(v -> {
            if (onAddClick != null)
                onAddClick.onClick(v, goods, getLayoutPosition());
        });
    }

}
