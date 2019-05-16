package com.zaomeng.zaomeng.view.adapter.point;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.PointBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-16.
 * FastAndroid
 */
public class PointAdapter extends BasePagedListAdapter<PointBean> {
    public PointAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return PointViewHolder.create(parent);
    }

    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((PointViewHolder) holder).bind(getItem(position));
    }

    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_point;
    }

    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    private static final DiffUtil.ItemCallback<PointBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<PointBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull PointBean oldItem, @NonNull PointBean newItem) {
            return oldItem.getOperateTime() == newItem.getOperateTime();
        }

        @Override
        public boolean areContentsTheSame(@NonNull PointBean oldItem, @NonNull PointBean newItem) {
            return oldItem.getOperateTime() == newItem.getOperateTime()
                    && oldItem.getActionType() == newItem.getActionType();

        }
    };
}
