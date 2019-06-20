package com.zaomeng.zaomeng.view.adapter.collect;

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
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class CollectViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView price;
    private ImageView add;
    private TextView specifications;
    private ImageView goodsIcon;

    private CollectViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        price = itemView.findViewById(R.id.price);
        add = itemView.findViewById(R.id.add);
        specifications = itemView.findViewById(R.id.specifications);
        goodsIcon = itemView.findViewById(R.id.icon_goods);
    }

    public static CollectViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods, parent, false);
        return new CollectViewHolder(view);
    }

    void bind(CollectInfoBean collectInfoBean, OnItemClick<CollectInfoBean> onItemClick, OnItemClick<CollectInfoBean> onAddClick) {
        goodsName.setText(collectInfoBean.getObjectName());
        price.setText(FormatUtils.numberFormatMoney(collectInfoBean.getShowPrice()));
        String unitDescription = collectInfoBean.getUnitDescription();
        if (!FormatUtils.isStringNull(unitDescription))
            specifications.setText(String.format("规格：%s", unitDescription));
//        specifications.setText(String.format("规格：%s", collectInfoBean.getUnitDescription()));
        Context context = itemView.getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        ViewGroup.LayoutParams layoutParams = goodsIcon.getLayoutParams();
        layoutParams.height = point.x / 3;
        layoutParams.width = point.x / 3;
        goodsIcon.setLayoutParams(layoutParams);
        Glide.with(goodsIcon).load(collectInfoBean.getListImage()).into(goodsIcon);
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, collectInfoBean, getLayoutPosition());
        });
        add.setOnClickListener(v -> {
            if (onAddClick != null)
                onAddClick.onClick(v, collectInfoBean, getLayoutPosition());
        });
    }

}
