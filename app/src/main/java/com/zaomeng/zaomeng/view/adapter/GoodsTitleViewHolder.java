package com.zaomeng.zaomeng.view.adapter;

import android.content.Context;
import android.graphics.Point;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.utils.GlideUtils;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsTitleViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView specifications;
    private TextView price;

    private ImageView add;
    private ImageView goodsIcon;

    private GoodsTitleViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        specifications = itemView.findViewById(R.id.specifications);
        price = itemView.findViewById(R.id.price);
        add = itemView.findViewById(R.id.add);
        goodsIcon = itemView.findViewById(R.id.icon_goods);
    }

    public static GoodsTitleViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods, parent, false);
        return new GoodsTitleViewHolder(view);
    }

    void bind(Item<NavigatorBean.GoodsListBean> goods, OnItemClick<NavigatorBean.GoodsListBean> onItemClick, OnItemClick<NavigatorBean.GoodsListBean> onAddClick, GlideUtils glideUtils) {
        goodsName.setText(goods.getData().getObjectName());
        Context context = itemView.getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        ViewGroup.LayoutParams layoutParams = goodsIcon.getLayoutParams();
        layoutParams.height = point.x / 3;
        layoutParams.width = point.x / 3;
        goodsIcon.setLayoutParams(layoutParams);
        Glide.with(goodsIcon).load(goods.getData().getListImage()).into(goodsIcon);
        String unitDescription = goods.getData().getUnitDescription();
        if (!FormatUtils.isStringNull(unitDescription))
            specifications.setText(String.format("规格：%s", unitDescription));
//        specifications.setText(String.format("规格：%s", goods.getData().getUnitDescription()));
        price.setText(FormatUtils.numberFormatMoney(goods.getData().getShowPrice()));
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, goods.getData(), getLayoutPosition());
        });
        add.setOnClickListener(v -> {
            if (onAddClick != null)
                onAddClick.onClick(v, goods.getData(), getLayoutPosition());
        });
    }

}
