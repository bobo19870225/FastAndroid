package com.zaomeng.zaomeng.view.adapter.shop_cart;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.view.adapter.NetworkStateItemViewHolder;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class ShopCartAdapter extends PagedListAdapter<ShopCartBean, RecyclerView.ViewHolder> {

    private Function0 retryCallback;
    private NetWorkState netWorkState;
    private HashMap<Integer, Boolean> isCheckedHasMap;
    private OnItemClick<ShopCartBean> onItemClick;
    private OnItemClick<ShopCartBean> onAddClick;


    @SuppressLint("UseSparseArrays")
    public ShopCartAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK);
        this.retryCallback = retryCallback;
    }
    public void setOnAddClick(OnItemClick<ShopCartBean> onAddClick) {
        this.onAddClick = onAddClick;
    }

    public void setOnItemClick(OnItemClick<ShopCartBean> onItemClick) {
        this.onItemClick = onItemClick;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (isCheckedHasMap == null) {
            isCheckedHasMap = new HashMap<>();
            for (int i = 0; i < getItemCount(); i++) {
                isCheckedHasMap.put(i, false);
            }
        }
        switch (viewType) {
            case R.layout.item_goods:
                return ShopCartViewHolder.create(parent);
            case R.layout.network_state_item:
                return NetworkStateItemViewHolder.create(parent, retryCallback);
            default:
                throw new IllegalArgumentException("unknown view type $viewType");
        }
    }

    /**
     * 先于构造函数调用
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.item_goods:
                ((ShopCartViewHolder) holder).bind(getItem(position), onItemClick, () -> {
                    if (onAddClick != null)
                        onAddClick.onClick(null, getItem(position), position);
                    Boolean aBoolean = isCheckedHasMap.get(position);
                    if (aBoolean != null) {
                        isCheckedHasMap.put(position, !aBoolean);
                        notifyDataSetChanged();
                    }
                    return Unit.INSTANCE;
                }, isCheckedHasMap.get(position));
                break;
            case R.layout.network_state_item:
                ((NetworkStateItemViewHolder) holder).bindTo(netWorkState);
        }

    }

    public void selectedAll() {
        Set<Map.Entry<Integer, Boolean>> entries = isCheckedHasMap.entrySet();
        boolean shouldSelectedAll = false;
        for (Map.Entry<Integer, Boolean> entryset : entries) {
            Boolean aBoolean = entryset.getValue();
            if (!aBoolean) {
                shouldSelectedAll = true;
                break;
            }
        }
        for (Map.Entry<Integer, Boolean> entryset : entries) {
            entryset.setValue(shouldSelectedAll);
        }
        notifyDataSetChanged();
    }
    private Boolean hasExtraRow() {
        return netWorkState != null && !netWorkState.equals(NetWorkState.loaded());
    }

    public List<ShopCartBean> getListSelectedItem() {
        List<ShopCartBean> list = new ArrayList<>();
        for (int i = 0; i < isCheckedHasMap.size(); i++) {
            Boolean aBoolean = isCheckedHasMap.get(i);
            if (aBoolean != null && aBoolean) {
                list.add(getItem(i));
            }
        }
        return list;
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
    private static final DiffUtil.ItemCallback<ShopCartBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<ShopCartBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull ShopCartBean oldItem, @NonNull ShopCartBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShopCartBean oldItem, @NonNull ShopCartBean newItem) {
            return oldItem.getId().equals(newItem.getId()) &&
                    oldItem.getGoodsShopID().equals(newItem.getGoodsShopID());
        }
    };

}
