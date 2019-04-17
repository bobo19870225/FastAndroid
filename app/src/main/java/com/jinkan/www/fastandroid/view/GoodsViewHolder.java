package com.jinkan.www.fastandroid.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsName;

    private GoodsViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsName = itemView.findViewById(R.id.goods_name);
    }

    public static GoodsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods, parent, false);
        return new GoodsViewHolder(view);
    }

    void bind(Goods goods) {
        goodsName.setText(goods.getGoodsName());
    }

}
