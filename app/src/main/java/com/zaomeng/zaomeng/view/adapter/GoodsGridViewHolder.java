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

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.utils.GlideUtils;

import java.util.Locale;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsGridViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView price;
    private TextView specifications;
    private TextView stock;
    private ImageView add;
    private ImageView icon_goods;

    private GoodsGridViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        price = itemView.findViewById(R.id.price);
        specifications = itemView.findViewById(R.id.specifications);
        stock = itemView.findViewById(R.id.stock);
        add = itemView.findViewById(R.id.add);
        icon_goods = itemView.findViewById(R.id.icon_goods);
    }

    public static GoodsGridViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods_2, parent, false);
        return new GoodsGridViewHolder(view);
    }

    void bind(Item<NavigatorBean.GoodsListBean> goods, OnItemClick<NavigatorBean.GoodsListBean> onItemClick, OnItemClick<NavigatorBean.GoodsListBean> onAddClick, GlideUtils glideUtils) {
        NavigatorBean.GoodsListBean data = goods.getData();
        goodsName.setText(data.getObjectName());
        price.setText(FormatUtils.numberFormatMoney(data.getShowPrice()));
        int stockNumber = data.getStockNumber();
        if (stockNumber > 0) {
            stock.setText(String.format(Locale.CHINA, "库存：%d", stockNumber));
        } else {
            stock.setText("售罄");
        }
        String unitDescription = data.getUnitDescription();
        Context context = itemView.getContext();
        if (!FormatUtils.isStringNull(unitDescription)) {
            specifications.setBackground(context.getResources().getDrawable(R.drawable.text_corner_bc));
            specifications.setText(String.format("规格：%s", unitDescription));
        } else {
            specifications.setBackground(null);
        }
//        specifications.setText(String.format("规格：%s", data.getUnitDescription()));

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        ViewGroup.LayoutParams layoutParams = icon_goods.getLayoutParams();
        layoutParams.height = point.x / 2 - 50;
        layoutParams.width = point.x / 2 - 50;
//        layoutParams.height = layoutParams.width;
        icon_goods.setLayoutParams(layoutParams);
        glideUtils.into(icon_goods, data.getListImage());
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
