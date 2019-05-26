package com.zaomeng.zaomeng.view.adapter.collect;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class CollectAdapter extends BasePagedListAdapter<CollectInfoBean> {



    private OnItemClick<CollectInfoBean> onItemClick;
//    private OnItemClick<CollectInfoBean> onAddClick;
//    private OnItemClick<CollectInfoBean> onDeleteClick;
//    public void setOnAddClick(OnItemClick<CollectInfoBean> onAddClick) {
//        this.onAddClick = onAddClick;
//    }

    public void setOnItemClick(OnItemClick<CollectInfoBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

//    public void setOnDeleteClick(OnItemClick<CollectInfoBean> onDeleteClick) {
//        this.onDeleteClick = onDeleteClick;
//    }

    public CollectAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }


    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return CollectViewHolder.create(parent);
    }


    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.layout.item_used_goods) {
            ((CollectViewHolder) holder).bind(getItem(position), onItemClick);
        }
    }


    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_used_goods;

    }


    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    private static final DiffUtil.ItemCallback<CollectInfoBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<CollectInfoBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull CollectInfoBean oldItem, @NonNull CollectInfoBean newItem) {
            return oldItem.getCollectID().equals(newItem.getCollectID());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CollectInfoBean oldItem, @NonNull CollectInfoBean newItem) {
            return oldItem.getCollectID().equals(newItem.getCollectID()) &&
                    oldItem.getObjectID().equals(newItem.getObjectID());
        }
    };

}
