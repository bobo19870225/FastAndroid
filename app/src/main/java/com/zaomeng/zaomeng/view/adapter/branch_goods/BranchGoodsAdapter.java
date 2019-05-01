package com.zaomeng.zaomeng.view.adapter.branch_goods;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class BranchGoodsAdapter extends BasePagedListAdapter<BranchGoodsBean> {



    private OnItemClick<BranchGoodsBean> onItemClick;
    private OnItemClick<BranchGoodsBean> onAddClick;

    public void setOnAddClick(OnItemClick<BranchGoodsBean> onAddClick) {
        this.onAddClick = onAddClick;
    }

    public void setOnItemClick(OnItemClick<BranchGoodsBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public BranchGoodsAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);

    }


    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return BranchGoodsViewHolder.create(parent);
    }


    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((BranchGoodsViewHolder) holder).bind(getItem(position), onItemClick, onAddClick);
    }


    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_goods;
    }


    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    private static final DiffUtil.ItemCallback<BranchGoodsBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<BranchGoodsBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull BranchGoodsBean oldItem, @NonNull BranchGoodsBean newItem) {
            return oldItem.getObjectID().equals(newItem.getObjectID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull BranchGoodsBean oldItem, @NonNull BranchGoodsBean newItem) {
            return oldItem.getObjectID().equals(newItem.getObjectID()) &&
                    oldItem.getObjectName().equals(newItem.getObjectName());
        }
    };

}
