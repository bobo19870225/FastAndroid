package com.zaomeng.zaomeng.view.adapter.member_shop;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class MemberShopAdapter extends BasePagedListAdapter<MemberShopBean> {


    private OnItemClick<MemberShopBean> onItemClick;
    private OnItemClick<MemberShopBean> onAddClick;

    public void setOnAddClick(OnItemClick<MemberShopBean> onAddClick) {
        this.onAddClick = onAddClick;
    }

    public void setOnItemClick(OnItemClick<MemberShopBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public MemberShopAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }


    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return MemberShopViewHolder.create(parent);
    }


    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((MemberShopViewHolder) holder).bind(getItem(position), onItemClick, onAddClick);
    }


    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_goods;
    }


    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    private static final DiffUtil.ItemCallback<MemberShopBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<MemberShopBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull MemberShopBean oldItem, @NonNull MemberShopBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull MemberShopBean oldItem, @NonNull MemberShopBean newItem) {
            return oldItem.getId().equals(newItem.getId()) &&
                    oldItem.getName().equals(newItem.getName());
        }
    };

}
