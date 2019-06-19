package com.zaomeng.zaomeng.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.view.custom_view.C2RoundAngleImageView;

/**
 * Created by Sampson on 2019-04-23.
 * FastAndroid
 */
class GoodsBannerViewHolder extends RecyclerView.ViewHolder {
    private C2RoundAngleImageView bannerView;


    private GoodsBannerViewHolder(@NonNull View itemView) {
        super(itemView);
        bannerView = itemView.findViewById(R.id.banner);
    }

    public static GoodsBannerViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods_banner, parent, false);
        return new GoodsBannerViewHolder(view);
    }

    void bind(Item<String> item, OnItemClick onItemClick) {
        Glide.with(bannerView).load(item.getData()).into(bannerView);
//        bannerView.setImageLoader(new GlideImageLoader());
//        ArrayList<String> list_path = new ArrayList<>();
//        list_path.add(item.getData());
//        //设置图片集合
//        bannerView.setImages(list_path);
//        //banner设置方法全部调用完毕时最后调用
//        bannerView.start();

//        itemView.setOnClickListener(v -> {
//            if (onItemClick != null)
//                onItemClick.onClick(v, item);
//        });

    }
}
