package com.jinkan.www.fastandroid.view;

import android.view.ViewGroup;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.Subjects;
import com.jinkan.www.fastandroid.model.repository.NetWorkState;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class MovieAdapter extends PagedListAdapter<Subjects, RecyclerView.ViewHolder> {

    private Function0 retryCallback;
    private NetWorkState netWorkState;


    public MovieAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK);
        this.retryCallback = retryCallback;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case R.layout.movie_item:
                return MovieViewHolder.create(parent);
            case R.layout.network_state_item:
                return NetworkStateItemViewHolder.create(parent, retryCallback);
            default:
                throw new IllegalArgumentException("unknown view type $viewType");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.movie_item:
                ((MovieViewHolder) holder).bind(getItem(position));
                break;
            case R.layout.network_state_item:
                ((NetworkStateItemViewHolder) holder).bindTo(netWorkState);
        }

    }


    private Boolean hasExtraRow() {
        return netWorkState != null && !netWorkState.equals(netWorkState.loaded());
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
            return R.layout.movie_item;
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
    public static final DiffUtil.ItemCallback<Subjects> DIFF_CALLBACK = new DiffUtil.ItemCallback<Subjects>() {
        @Override
        public boolean areItemsTheSame(@NonNull Subjects oldItem, @NonNull Subjects newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Subjects oldItem, @NonNull Subjects newItem) {
            return oldItem.equals(newItem);
        }
    };

}
