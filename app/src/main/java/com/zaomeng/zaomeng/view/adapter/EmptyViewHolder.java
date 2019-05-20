package com.zaomeng.zaomeng.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class EmptyViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView textView;


    private EmptyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image_view);
        textView = itemView.findViewById(R.id.text_view);
    }

    public static EmptyViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_empty, parent, false);
        return new EmptyViewHolder(view);
    }

    void bind() {

    }
}
