package com.zaomeng.zaomeng.view.adapter.after_sale;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.AfterSaleBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class AfterSaleRecordAdapter extends BasePagedListAdapter<AfterSaleBean> {
    public AfterSaleRecordAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }

    private OnItemClick<AfterSaleBean> onItemClick;//退货

    public void setOnItemClick(OnItemClick<AfterSaleBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return AfterSaleRecordViewHolder.create(parent);
    }

    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((AfterSaleRecordViewHolder) holder).bind(getItem(position), onItemClick);
    }

    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_after_sale;
    }

    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    private static final DiffUtil.ItemCallback<AfterSaleBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<AfterSaleBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull AfterSaleBean oldItem, @NonNull AfterSaleBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull AfterSaleBean oldItem, @NonNull AfterSaleBean newItem) {
            return oldItem.getId().equals(newItem.getId())
                    && oldItem.getApplyTime() == newItem.getApplyTime()
                    && oldItem.getStatus() == newItem.getStatus();

        }
    };

}
