package com.zaomeng.zaomeng.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.Status;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public abstract class BasePagedListAdapter<T> extends PagedListAdapter<T, RecyclerView.ViewHolder> {

    private Function0 retryCallback;
    private NetWorkState netWorkState;

    private boolean isEmpty;
    public BasePagedListAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback, Function0 retryCallback) {
        super(diffCallback);
        this.retryCallback = retryCallback;
    }

    @Override
    public void submitList(@Nullable PagedList<T> pagedList) {
        super.submitList(pagedList);
        if (pagedList != null && !hasExtraRow()) {
            setEmptyView(pagedList.isEmpty());
        }
    }

    private void setEmptyView(boolean isEmpty) {
        boolean hadEmpty = this.isEmpty;
        this.isEmpty = isEmpty;
        if (hadEmpty != isEmpty) {
            if (hadEmpty) {
                notifyItemRemoved(super.getItemCount());
            } else {
                notifyItemInserted(super.getItemCount());
            }
        } else if (isEmpty) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

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
    @NonNull
    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.network_state_item) {
            return NetworkStateItemViewHolder.create(parent, retryCallback);
        } else if (viewType == R.layout.item_empty) {
            return getEmptyViewHolder(parent);
        } else {
            return setViewHolder(parent, viewType);
        }

    }

    protected RecyclerView.ViewHolder getEmptyViewHolder(ViewGroup parent) {
        return EmptyViewHolder.create(parent);
    }

    @NonNull
    protected abstract RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType);


    @Override
    public final void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.layout.network_state_item) {
            ((NetworkStateItemViewHolder) holder).bindTo(netWorkState);
        } else if (getItemViewType(position) == R.layout.item_empty) {
            emptyViewHolderBind(holder);
        } else {
            viewHolderBind(holder, position);
        }

    }

    private void emptyViewHolderBind(RecyclerView.ViewHolder holder) {
        ((EmptyViewHolder) holder).bind();
    }

    protected abstract void viewHolderBind(RecyclerView.ViewHolder holder, int position);


    protected Boolean hasExtraRow() {
        if (isEmpty) return false;//消除干扰
        return netWorkState != null && netWorkState.getStatus() == Status.FAILED;
    }

    @Override
    public int getItemCount() {
        int itemCount = super.getItemCount();
        if (hasExtraRow()) {
            return itemCount + 1;
        } else if (isEmpty && itemCount == 0) {
            return itemCount + 1;
        } else {
            return itemCount;
        }
    }

    @Override
    public final int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else if (isEmpty && position == getItemCount() - 1) {
            return R.layout.item_empty;
        } else {
            return giveItemViewType(position);
        }
    }

    protected abstract int giveItemViewType(int position);


}
