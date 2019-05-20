package com.zaomeng.zaomeng.view.adapter.shop_cart;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class ShopCartAdapter extends BasePagedListAdapter<ShopCartBean> {


    //    private HashMap<Integer, Boolean> isCheckedHasMap;
    private OnItemClick<ShopCartBean> onSelectClick;
    private OnItemClick<ShopCartBean> onAddClick;
    private OnItemClick<ShopCartBean> onReduceClick;
    public final MutableLiveData<Boolean> isSelectedAll = new MutableLiveData<>();

    @SuppressLint("UseSparseArrays")
    public ShopCartAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);

    }
    public void setOnAddClick(OnItemClick<ShopCartBean> onAddClick) {
        this.onAddClick = onAddClick;
    }


    public void setOnSelectClick(OnItemClick<ShopCartBean> onSelectClick) {
        this.onSelectClick = onSelectClick;
    }

    public void setOnReduceClick(OnItemClick<ShopCartBean> onReduceClick) {
        this.onReduceClick = onReduceClick;
    }


    @SuppressLint("UseSparseArrays")
    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        return ShopCartViewHolder.create(parent);
    }

    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.layout.item_goods) {
            ((ShopCartViewHolder) holder).bind(getItem(position),
                    onSelectClick,
                    onAddClick,
                    onReduceClick);
        }
        boolean isAll = true;
        for (int i = 0; i < getItemCount(); i++) {
            ShopCartBean item1 = getItem(i);
            if (item1 != null && item1.getIsSelected() == 0) {
                isAll = false;
                break;
            }

        }
        isSelectedAll.setValue(isAll);
    }


    public List<List<ShopCartBean>> getListGoodsItem() {
        List<List<ShopCartBean>> listList = new ArrayList<>();
        List<ShopCartBean> allList = new ArrayList<>();
        List<ShopCartBean> selectList = new ArrayList<>();
        List<ShopCartBean> unList = new ArrayList<>();

        for (int i = 0; i < getItemCount(); i++) {
            ShopCartBean shopCartBean = getItem(i);
            if (shopCartBean != null) {
                boolean aBoolean = shopCartBean.getIsSelected() == 1;
                //全部的
                allList.add(getItem(i));
                if (aBoolean) {//选中的
                    selectList.add(shopCartBean);
                } else {//未选中的
                    unList.add(shopCartBean);
                }
            }
        }


        listList.add(allList);
        listList.add(selectList);
        listList.add(unList);
        return listList;
    }


    @Override
    protected int giveItemViewType(int position) {
        return R.layout.item_goods;
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
                    oldItem.getGoodsShopID().equals(newItem.getGoodsShopID())
                    && oldItem.getIsSelected() == newItem.getIsSelected()
                    && oldItem.getQty() == newItem.getQty();
        }
    };

}
