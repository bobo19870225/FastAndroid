package com.zaomeng.zaomeng.view.adapter.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.MessageBean;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class MessageViewHolder extends RecyclerView.ViewHolder {
    private TextView text;
    private TextView time;

    //    private ImageView add;
    private MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.text);
        time = itemView.findViewById(R.id.time);
    }

    public static MessageViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message_detail, parent, false);
        return new MessageViewHolder(view);
    }

    void bind(MessageBean messageBean, OnItemClick<MessageBean> onItemClick, OnItemClick<MessageBean> onAddClick) {
        text.setText(messageBean.getDescription());
        time.setText(messageBean.getSendDateStr());
        itemView.setOnClickListener(v -> {
            if (onItemClick != null)
                onItemClick.onClick(v, messageBean, getLayoutPosition());
        });
    }

}
