package com.zaomeng.zaomeng.view.adapter.shop_cart;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
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
public class ShopCartAdapter extends BasePagedListAdapter<ShopCartBean> {


    private HashMap<Integer, Boolean> isCheckedHasMap;
    //    private OnItemClick<ShopCartBean> onItemClick;
    private OnItemClick<ShopCartBean> onSelectClick;
    private OnItemClick<ShopCartBean> onAddClick;
    private OnItemClick<ShopCartBean> onReduceClick;
    public final MutableLiveData<Boolean> isSelectedAll = new MediatorLiveData<>();

    @SuppressLint("UseSparseArrays")
    public ShopCartAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);

    }
    public void setOnAddClick(OnItemClick<ShopCartBean> onAddClick) {
        this.onAddClick = onAddClick;
    }

//    public void setOnItemClick(OnItemClick<ShopCartBean> onItemClick) {
//        this.onItemClick = onItemClick;
//    }

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
        if (isCheckedHasMap == null) {
            isCheckedHasMap = new HashMap<>();
            int itemCount = getItemCount();
            if (hasExtraRow()) {
                itemCount -= 1;
            }
            boolean isSelectAll = true;
            for (int i = 0; i < itemCount; i++) {
                ShopCartBean item = getItem(i);
                if (item != null) {
                    isCheckedHasMap.put(i, item.getIsSelected() == 1);
                    if (isSelectAll) {
                        isSelectAll = item.getIsSelected() == 1;
                    }

                }
            }
            isSelectedAll.setValue(isSelectAll);
        }
        return ShopCartViewHolder.create(parent);
    }


    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.layout.item_goods) {
            ((ShopCartViewHolder) holder).bind(getItem(position),
                    onSelectClick,
                    () -> {
                        Boolean aBoolean = isCheckedHasMap.get(position);
                        if (aBoolean != null) {
//                            isSelectedAll.setValue(false);
                            isCheckedHasMap.put(position, !aBoolean);
                            for (int i = 0; i < isCheckedHasMap.size(); i++) {
                                Boolean aBoolean1 = isCheckedHasMap.get(i);
                                if (aBoolean1 != null) {
                                    if (!aBoolean1) {
                                        isSelectedAll.setValue(false);
                                        break;
                                    }
                                }
                                isSelectedAll.setValue(true);
                            }
                            notifyDataSetChanged();
                        }
                        return Unit.INSTANCE;
                    },
                    onAddClick,
                    null,
                    onReduceClick,
                    null,
                    isCheckedHasMap.get(position));
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
        isSelectedAll.setValue(shouldSelectedAll);
        for (Map.Entry<Integer, Boolean> entryset : entries) {
            entryset.setValue(shouldSelectedAll);
        }
        notifyDataSetChanged();
    }


    public List<List<ShopCartBean>> getListGoodsItem() {
        List<List<ShopCartBean>> listList = new ArrayList<>();
//        List<ShopCartBean> allList = new ArrayList<>();
        List<ShopCartBean> selectList = new ArrayList<>();
        List<ShopCartBean> unList = new ArrayList<>();
        if (isCheckedHasMap != null) {
            for (int i = 0; i < isCheckedHasMap.size(); i++) {
                Boolean aBoolean = isCheckedHasMap.get(i);
                if (aBoolean != null) {
                    //全部的
//                allList.add(getItem(i));
                    if (aBoolean) {//选中的
                        selectList.add(getItem(i));
                    } else {//未选中的
                        unList.add(getItem(i));
                    }
                }

            }
        }

//        listList.add(allList);
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
                    oldItem.getGoodsShopID().equals(newItem.getGoodsShopID());
        }
    };

}
