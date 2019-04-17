package com.jinkan.www.fastandroid.view;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinkan.www.fastandroid.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsParentViewHolder extends RecyclerView.ViewHolder {
    private TextView goodsSort;

    private GoodsParentViewHolder(@NonNull View itemView) {
        super(itemView);
        goodsSort = itemView.findViewById(R.id.goods_sort);
    }

    public static GoodsParentViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods_parent, parent, false);
        return new GoodsParentViewHolder(view);
    }

    void bind(String goods, int position, Function0 function0, List<Boolean> isClicks) {
        goodsSort.setText(goods);
        if (isClicks.get(position)) {
            goodsSort.setTextColor(Color.parseColor("#FF4081"));
        } else {
            goodsSort.setTextColor(Color.parseColor("#666666"));
        }
        goodsSort.setOnClickListener(v -> {
            function0.invoke();
        });
    }

}
