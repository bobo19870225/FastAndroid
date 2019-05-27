package com.zaomeng.zaomeng.view.adapter.address;

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
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 */
public class AddressAdapter extends BasePagedListAdapter<MemberShopBean> {
    //    private List<MemberShopBean> list;
    private OnItemClick<MemberShopBean> onItemClick;

    public AddressAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }


    public void setOnItemClick(OnItemClick<MemberShopBean> onItemClick) {
        this.onItemClick = onItemClick;
    }


    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return AddressViewHolder.create(parent);
    }


    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        ((AddressViewHolder) holder).bind(getItem(position), onItemClick);
    }


    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_address;
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
            return oldItem.getId().equals(newItem.getId())
                    && oldItem.getVerifyStatus() == newItem.getVerifyStatus();

        }
    };
}
