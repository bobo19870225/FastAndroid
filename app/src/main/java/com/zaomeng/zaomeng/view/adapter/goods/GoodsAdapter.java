package com.zaomeng.zaomeng.view.adapter.goods;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsAdapter extends BasePagedListAdapter<GoodsListRowsBean> {


    private OnItemClick<GoodsListRowsBean> onItemClick;
    private OnItemClick<GoodsListRowsBean> onAddClick;

    public void setOnAddClick(OnItemClick<GoodsListRowsBean> onAddClick) {
        this.onAddClick = onAddClick;
    }

    public void setOnItemClick(OnItemClick<GoodsListRowsBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public GoodsAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }



    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return GoodsViewHolder.create(parent);
    }


    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((GoodsViewHolder) holder).bind(getItem(position), onItemClick, onAddClick);
    }


    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_goods;
    }


    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    private static final DiffUtil.ItemCallback<GoodsListRowsBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<GoodsListRowsBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull GoodsListRowsBean oldItem, @NonNull GoodsListRowsBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull GoodsListRowsBean oldItem, @NonNull GoodsListRowsBean newItem) {
            return oldItem.getId().equals(newItem.getId()) &&
                    oldItem.getName().equals(newItem.getName());
        }
    };

}
