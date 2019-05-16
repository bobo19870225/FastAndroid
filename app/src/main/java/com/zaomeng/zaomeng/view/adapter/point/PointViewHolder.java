package com.zaomeng.zaomeng.view.adapter.point;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.PointBean;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class PointViewHolder extends RecyclerView.ViewHolder {
    private TextView action;
    private TextView time;
    private TextView point;

    private PointViewHolder(@NonNull View itemView) {
        super(itemView);
        action = itemView.findViewById(R.id.action);
        time = itemView.findViewById(R.id.time);
        point = itemView.findViewById(R.id.point);
    }

    public static PointViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_point, parent, false);
        return new PointViewHolder(view);
    }

    void bind(PointBean orderBean) {
        action.setText(orderBean.getAction());
        time.setText(orderBean.getOperateTimeStr());
        point.setText(String.valueOf(orderBean.getNumber()));
    }


}
