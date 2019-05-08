package com.zaomeng.zaomeng.view.adapter.feedback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class AddViewHolder extends RecyclerView.ViewHolder {

    private AddViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public static AddViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add, parent, false);
        return new AddViewHolder(view);
    }

    void bind(OnItemClick<String> onItemClick) {
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, null, getLayoutPosition());
        });
    }

}
