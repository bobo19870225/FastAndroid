package com.zaomeng.zaomeng.view.adapter.goods_details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.Banner;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsHeaderBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.utils.GlideImageLoader;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsDetailsHeaderViewHolder extends RecyclerView.ViewHolder {

    private Banner banner;
    private TextView goodsName;
    private TextView price;
    private TextView describe;
    private TextView salesVolume;

    private GoodsDetailsHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        banner = itemView.findViewById(R.id.banner);
        goodsName = itemView.findViewById(R.id.goods_name);
        price = itemView.findViewById(R.id.price);
        describe = itemView.findViewById(R.id.describe_text);
        salesVolume = itemView.findViewById(R.id.salesVolume);
    }

    public static GoodsDetailsHeaderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_goods_details_header, parent, false);
        return new GoodsDetailsHeaderViewHolder(view);
    }

    void bind(GoodsDetailsHeaderBean goodsDetailsHeaderBean) {
        if (goodsDetailsHeaderBean != null) {
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(goodsDetailsHeaderBean.getListBannerURL());
            banner.start();
            goodsName.setText(goodsDetailsHeaderBean.getGoodsName());
            price.setText(FormatUtils.numberFormatMoney(goodsDetailsHeaderBean.getPrice()));
            describe.setText(goodsDetailsHeaderBean.getDescribe());
            salesVolume.setText(String.valueOf(goodsDetailsHeaderBean.getStockOut()));
        }

    }

}
