package com.zaomeng.zaomeng.view.adapter.bonus;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.BonusBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-16.
 * FastAndroid
 */
public class BonusAdapter extends BasePagedListAdapter<BonusBean> {
    private OnItemClick<BonusBean> onItemClick;

    public BonusAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }

    public void setOnItemClick(OnItemClick<BonusBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return BonusViewHolder.create(parent);
    }

    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((BonusViewHolder) holder).bind(getItem(position), onItemClick);
    }

    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_bonus;
    }

    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    private static final DiffUtil.ItemCallback<BonusBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<BonusBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull BonusBean oldItem, @NonNull BonusBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull BonusBean oldItem, @NonNull BonusBean newItem) {
            return oldItem.getId().equals(newItem.getId())
                    && oldItem.getStatus() == newItem.getStatus();

        }
    };
}
