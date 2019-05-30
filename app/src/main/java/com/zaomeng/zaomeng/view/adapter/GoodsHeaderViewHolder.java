package com.zaomeng.zaomeng.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;

import java.util.List;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsHeaderViewHolder extends RecyclerView.ViewHolder {
    private ImageView commonlyUsed;
    private ImageView myOrder;
    private ImageView myBonus;
    private ImageView signIn;
    private ImageView yunShu;
    private ImageView youHui;
    private ImageView tuiHuan;
    private TextView textYunShu;
    private TextView textYouHui;
    private TextView textTuiHuan;

    private GoodsHeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        commonlyUsed = itemView.findViewById(R.id.commonly_used);
        myOrder = itemView.findViewById(R.id.my_order);
        myBonus = itemView.findViewById(R.id.my_bonus);
        signIn = itemView.findViewById(R.id.sign_in);
        yunShu = itemView.findViewById(R.id.yunshu);
        youHui = itemView.findViewById(R.id.you_hui);
        tuiHuan = itemView.findViewById(R.id.tuihuan);
        textYunShu = itemView.findViewById(R.id.text_yunshu);
        textYouHui = itemView.findViewById(R.id.text_youhui);
        textTuiHuan = itemView.findViewById(R.id.text_tuihuan);
    }

    public static GoodsHeaderViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header, parent, false);
        return new GoodsHeaderViewHolder(view);
    }

    void bind(Item<List<NavigatorBean>> item, OnItemClick<String> onItemClick) {
        List<NavigatorBean> navigatorBeanList = item.getData();
        for (int i = 0; i < navigatorBeanList.size(); i++) {
            if (i == 3) {
                break;
            }
            NavigatorBean navigatorBean = navigatorBeanList.get(i);
            setImage(navigatorBean, i);
        }

        commonlyUsed.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, "commonlyUsed", getLayoutPosition());
        });
        myOrder.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, "myOrder", getLayoutPosition());
        });
        myBonus.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, "myBonus", getLayoutPosition());
        });
        signIn.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, "signIn", getLayoutPosition());
        });
    }

    private void setImage(NavigatorBean navigatorBean, int i) {
        switch (i) {
            case 0:
                textYunShu.setText(navigatorBean.getName());
                Glide.with(yunShu).load(navigatorBean.getSamllIcon()).into(yunShu);
                break;
            case 1:
                textYouHui.setText(navigatorBean.getName());
                Glide.with(youHui).load(navigatorBean.getSamllIcon()).into(youHui);
                break;
            case 2:
                textTuiHuan.setText(navigatorBean.getName());
                Glide.with(tuiHuan).load(navigatorBean.getSamllIcon()).into(tuiHuan);
                break;
        }

    }

}
