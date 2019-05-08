package com.zaomeng.zaomeng.view.adapter.feedback;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class FeedbackViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;

    private FeedbackViewHolder(@NonNull View itemView) {
        super(itemView);

        image = itemView.findViewById(R.id.image);
    }

    public static FeedbackViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image_small, parent, false);
        return new FeedbackViewHolder(view);
    }

    void bind(String url, OnItemClick<String> onItemClick) {
        Glide.with(image).load(url).into(image);
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, url, getLayoutPosition());
        });
    }

}
