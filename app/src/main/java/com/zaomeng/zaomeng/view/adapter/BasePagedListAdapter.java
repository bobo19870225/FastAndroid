package com.zaomeng.zaomeng.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.NetWorkState;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public abstract class BasePagedListAdapter<T> extends PagedListAdapter<T, RecyclerView.ViewHolder> {

    private Function0 retryCallback;
    private NetWorkState netWorkState;

    public BasePagedListAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback, Function0 retryCallback) {
        super(diffCallback);
        this.retryCallback = retryCallback;
    }


    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == R.layout.network_state_item) {
            return NetworkStateItemViewHolder.create(parent, retryCallback);
        }
        return setViewHolder(parent, viewType);
    }

    @NonNull
    protected abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType);


    @Override
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.layout.network_state_item) {
            ((NetworkStateItemViewHolder) holder).bindTo(netWorkState);
        } else {
            viewHolderBind(holder, position);
        }

    }

    protected abstract void viewHolderBind(RecyclerView.ViewHolder holder, int position);


    protected Boolean hasExtraRow() {
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
    public final int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            return giveItemViewType(position);
        }

    }

    protected abstract int giveItemViewType(int position);

    public final void setNetworkState(NetWorkState newNetWorkState) {
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


}
