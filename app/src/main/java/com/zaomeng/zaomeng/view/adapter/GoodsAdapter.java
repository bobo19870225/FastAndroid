package com.zaomeng.zaomeng.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsAdapter extends PagedListAdapter<GoodsListRowsBean, RecyclerView.ViewHolder> {

    private Function0 retryCallback;
    private NetWorkState netWorkState;

    private OnItemClick<GoodsListRowsBean> onItemClick;
    private OnItemClick<GoodsListRowsBean> onAddClick;

    public void setOnAddClick(OnItemClick<GoodsListRowsBean> onAddClick) {
        this.onAddClick = onAddClick;
    }

    public void setOnItemClick(OnItemClick<GoodsListRowsBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public GoodsAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK);
        this.retryCallback = retryCallback;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case R.layout.item_goods:
                return GoodsViewHolder.create(parent);
            case R.layout.network_state_item:
                return NetworkStateItemViewHolder.create(parent, retryCallback);
            default:
                throw new IllegalArgumentException("unknown view type $viewType");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.item_goods:
                ((GoodsViewHolder) holder).bind(getItem(position), onItemClick, onAddClick);
                break;
            case R.layout.network_state_item:
                ((NetworkStateItemViewHolder) holder).bindTo(netWorkState);
        }

    }


    private Boolean hasExtraRow() {
        return netWorkState != null && !netWorkState.equals(NetWorkState.loaded());
    }

    @Override
    public int getItemCount() {
        if (hasExtraRow()) {
            return super.getItemCount() + 1;
        } else {
            return super.getItemCount();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            return R.layout.item_goods;
        }

    }

    public void setNetworkState(NetWorkState newNetWorkState) {
        NetWorkState previousState = this.netWorkState;
        Boolean hadExtraRow = hasExtraRow();
        this.netWorkState = newNetWorkState;
        Boolean hasExtraRow = hasExtraRow();
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount());
            } else {
                notifyItemInserted(super.getItemCount());
            }
        } else if (hasExtraRow && previousState != newNetWorkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }
    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    public static final DiffUtil.ItemCallback<GoodsListRowsBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<GoodsListRowsBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull GoodsListRowsBean oldItem, @NonNull GoodsListRowsBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull GoodsListRowsBean oldItem, @NonNull GoodsListRowsBean newItem) {
            return oldItem.equals(newItem);
        }
    };

}
