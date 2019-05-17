package com.zaomeng.zaomeng.view.adapter.order;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.OrderBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class OrderAdapter extends BasePagedListAdapter<OrderBean> {
    public OrderAdapter(Function0 retryCallback) {

        super(DIFF_CALLBACK, retryCallback);
    }

    private OnItemClick<OrderBean> onItemClick;
    private OnItemClick<OrderBean> onItemPayClick;
    private OnItemClick<OrderBean> onItemCancelClick;

    public void setOnItemClick(OnItemClick<OrderBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setOnItemPayClick(OnItemClick<OrderBean> onItemPayClick) {
        this.onItemPayClick = onItemPayClick;
    }

    public void setOnItemCancelClick(OnItemClick<OrderBean> onItemCancelClick) {
        this.onItemCancelClick = onItemCancelClick;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return OrderViewHolder.create(parent);
    }

    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((OrderViewHolder) holder).bind(getItem(position), onItemClick, onItemPayClick, onItemCancelClick);
    }

    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_order;
    }

    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    private static final DiffUtil.ItemCallback<OrderBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<OrderBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull OrderBean oldItem, @NonNull OrderBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull OrderBean oldItem, @NonNull OrderBean newItem) {
            return oldItem.getId().equals(newItem.getId())
                    && oldItem.getApplyTime() == newItem.getApplyTime()
                    && oldItem.getStatus() == newItem.getStatus();

        }
    };

}
