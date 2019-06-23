package com.zaomeng.zaomeng.view.adapter.branch_goods;

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
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import java.util.Locale;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class BranchGoodsViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;
    private TextView stock;
    private ImageView add;
    private ImageView goodsIcon;
    private TextView price;
    private TextView specifications;

    private BranchGoodsViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
        stock = itemView.findViewById(R.id.stock);
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

        int stockNumber = goods.getStockNumber();
        if (stockNumber > 0) {
            stock.setText(String.format(Locale.CHINA, "库存：%d", stockNumber));
        } else {
            stock.setText("售罄");
        }

        String unitDescription = goods.getUnitDescription();
        if (!FormatUtils.isStringNull(unitDescription))
            specifications.setText(String.format("规格：%s", unitDescription));
//        specifications.setText(String.format("规格：%s", goods.getUnitDescription()));
        Context context = itemView.getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        ViewGroup.LayoutParams layoutParams = goodsIcon.getLayoutParams();
        layoutParams.height = point.x / 3;
        layoutParams.width = point.x / 3;
        goodsIcon.setLayoutParams(layoutParams);
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
