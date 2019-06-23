package com.zaomeng.zaomeng.view.adapter.goods;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import java.util.Locale;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView stock;
    private TextView specifications;
    private TextView price;

    private ImageView add;
    private ImageView goodsIcon;

    private GoodsViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        stock = itemView.findViewById(R.id.stock);
        specifications = itemView.findViewById(R.id.specifications);
        price = itemView.findViewById(R.id.price);
        add = itemView.findViewById(R.id.add);
        goodsIcon = itemView.findViewById(R.id.icon_goods);
    }

    public static GoodsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods, parent, false);
        return new GoodsViewHolder(view);
    }

    void bind(GoodsListRowsBean goods, OnItemClick<GoodsListRowsBean> onItemClick, OnItemClick<GoodsListRowsBean> onAddClick) {
        goodsName.setText(goods.getName());
        Glide.with(goodsIcon).load(goods.getLargerImage()).into(goodsIcon);

        int stockNumber = goods.getStockNumber();
        if (stockNumber > 0) {
            stock.setText(String.format(Locale.CHINA, "库存：%d", stockNumber));
        } else {
            stock.setText("售罄");
        }

        String unitDescription = goods.getUnitDescription();
        if (!FormatUtils.isStringNull(unitDescription))
            specifications.setText(String.format("规格：%s", unitDescription));
        price.setText(FormatUtils.numberFormatMoney(goods.getShowPrice()));
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
