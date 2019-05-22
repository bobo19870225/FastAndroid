package com.zaomeng.zaomeng.view.adapter.message;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.MessageBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class MessageAdapter extends BasePagedListAdapter<MessageBean> {


    private OnItemClick<MessageBean> onItemClick;
    private OnItemClick<MessageBean> onAddClick;

    public void setOnAddClick(OnItemClick<MessageBean> onAddClick) {
        this.onAddClick = onAddClick;
    }

    public void setOnItemClick(OnItemClick<MessageBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public MessageAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }


    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return MessageViewHolder.create(parent);
    }


    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((MessageViewHolder) holder).bind(getItem(position), onItemClick, onAddClick);
    }


    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_message_detail;
    }


    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    private static final DiffUtil.ItemCallback<MessageBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<MessageBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull MessageBean oldItem, @NonNull MessageBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MessageBean oldItem, @NonNull MessageBean newItem) {
            return oldItem.getId().equals(newItem.getId()) &&
                    oldItem.getName().equals(newItem.getName());
        }
    };

}
