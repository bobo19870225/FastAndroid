package com.zaomeng.zaomeng.view.adapter.goods_details;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsHeaderBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsImageBean;
import com.zaomeng.zaomeng.view.adapter.NetworkStateItemViewHolder;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsDetailsAdapter extends PagedListAdapter<GoodsDetailsImageBean, RecyclerView.ViewHolder> {

    private Function0 retryCallback;
    private NetWorkState netWorkState;


    public GoodsDetailsAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK);
        this.retryCallback = retryCallback;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case R.layout.item_image:
                return GoodsDetailsViewHolder.create(parent);
            case R.layout.item_goods_details_header:
                return GoodsDetailsHeaderViewHolder.create(parent);
            case R.layout.network_state_item:
                return NetworkStateItemViewHolder.create(parent, retryCallback);
            default:
                throw new IllegalArgumentException("unknown view type $viewType");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.item_image:
                ((GoodsDetailsViewHolder) holder).bind(getItem(position - 1));
                break;
            case R.layout.item_goods_details_header:
                ((GoodsDetailsHeaderViewHolder) holder).bind(goodsDetailsHeaderBean);
                break;
            case R.layout.network_state_item:
                ((NetworkStateItemViewHolder) holder).bindTo(netWorkState);
                break;

        }

    }


    private Boolean hasExtraRow() {
        return netWorkState != null && !netWorkState.equals(NetWorkState.loaded());
    }

    @Override
    public int getItemCount() {
        if (hasExtraRow()) {
            return super.getItemCount() + 2;
        } else {
            return super.getItemCount() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount()) {
            return R.layout.network_state_item;
        } else if (position == 0) {
            return R.layout.item_goods_details_header;
        } else {
            return R.layout.item_image;
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
    private static final DiffUtil.ItemCallback<GoodsDetailsImageBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<GoodsDetailsImageBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull GoodsDetailsImageBean oldItem, @NonNull GoodsDetailsImageBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull GoodsDetailsImageBean oldItem, @NonNull GoodsDetailsImageBean newItem) {
            return oldItem.getId().equals(newItem.getId()) &&
                    oldItem.getName().equals(newItem.getName());
        }
    };
    private GoodsDetailsHeaderBean goodsDetailsHeaderBean;

    public void setHeaderData(GoodsDetailsHeaderBean goodsDetailsHeaderBean) {
        this.goodsDetailsHeaderBean = goodsDetailsHeaderBean;
//        notifyItemChanged(0);
        notifyDataSetChanged();
    }

}
