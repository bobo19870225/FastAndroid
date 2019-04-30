package com.zaomeng.zaomeng.view.adapter.goods_details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsImageBean;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsDetailsViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    private GoodsDetailsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);

    }

    public static GoodsDetailsViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new GoodsDetailsViewHolder(view);
    }

    void bind(GoodsDetailsImageBean goods) {
        Glide.with(imageView).load(goods.getUrl()).into(imageView);

    }

}
