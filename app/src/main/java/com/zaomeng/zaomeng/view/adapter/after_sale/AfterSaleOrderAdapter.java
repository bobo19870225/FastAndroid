package com.zaomeng.zaomeng.view.adapter.after_sale;

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
public class AfterSaleOrderAdapter extends BasePagedListAdapter<OrderBean> {
    public AfterSaleOrderAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }

    private OnItemClick<OrderBean.GoodsListBean> onItemReturnClick;//退货


    public void setOnItemReturnClick(OnItemClick<OrderBean.GoodsListBean> onItemReturnClick) {
        this.onItemReturnClick = onItemReturnClick;
    }


    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return AfterSaleOrderViewHolder.create(parent);
    }

    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((AfterSaleOrderViewHolder) holder).bind(getItem(position), onItemReturnClick);
    }

    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_order_title;
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
