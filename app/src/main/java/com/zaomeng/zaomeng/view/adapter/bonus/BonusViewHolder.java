package com.zaomeng.zaomeng.view.adapter.bonus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.BonusBean;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class BonusViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView title;
    private TextView price;
    private TextView date;
    private TextView use;

    private BonusViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        title = itemView.findViewById(R.id.title);
        price = itemView.findViewById(R.id.price);
        date = itemView.findViewById(R.id.date);
        use = itemView.findViewById(R.id.use);
    }

    public static BonusViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bonus, parent, false);
        return new BonusViewHolder(view);
    }

    void bind(BonusBean bonusBean, OnItemClick<BonusBean> onItemClick) {
        title.setText(bonusBean.getPrintCode());
        name.setText(bonusBean.getName());
        price.setText(String.valueOf(bonusBean.getAmount()));
        date.setText(String.format("有效期：%s--%s", bonusBean.getStartDateStr(), bonusBean.getEndDateStr()));
        if (bonusBean.getStatus() == 1) {
            use.setVisibility(View.VISIBLE);
        } else {
            use.setVisibility(View.GONE);
        }
        use.setOnClickListener(v -> {
            if (onItemClick != null) {
                onItemClick.onClick(v, bonusBean, getLayoutPosition());
            }

        });
    }


}
