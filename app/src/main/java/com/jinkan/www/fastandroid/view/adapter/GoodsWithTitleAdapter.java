package com.jinkan.www.fastandroid.view.adapter;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.repository.NetWorkState;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.NavigatorBean;

import java.util.List;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsWithTitleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Function0 retryCallback;
    private NetWorkState netWorkState;

    private OnItemClick onItemClick;
    private OnItemClick onAddClick;
    private List<Item> objects;

    public void setOnAddClick(OnItemClick onAddClick) {
        this.onAddClick = onAddClick;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public GoodsWithTitleAdapter(Function0 retryCallback) {
//        super(DIFF_CALLBACK);
        this.retryCallback = retryCallback;
    }

    /**
     * 传入数据
     *
     * @param objects 数据集
     */
    public void setDate(List<Item> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case R.layout.item_goods:
                return GoodsTitleViewHolder.create(parent);
            case R.layout.network_state_item:
                return NetworkStateItemViewHolder.create(parent, retryCallback);
            case R.layout.item_goods_navigation:
                return GoodsNavigationViewHolder.create(parent);
            case R.layout.item_goods_banner:
                return GoodsBannerViewHolder.create(parent);
            case R.layout.item_goods_2:
                return GoodsGridViewHolder.create(parent);
            default:
                throw new IllegalArgumentException("unknown view type $viewType");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.item_goods:
                ((GoodsTitleViewHolder) holder).bind((Item<NavigatorBean.GoodsListBean>) getItem(position), onItemClick, onAddClick);
                break;
            case R.layout.network_state_item:
                ((NetworkStateItemViewHolder) holder).bindTo(netWorkState);
                break;
            case R.layout.item_goods_navigation:
                ((GoodsNavigationViewHolder) holder).bind((Item<String>) getItem(position), onItemClick);
                break;
            case R.layout.item_goods_banner:
                ((GoodsBannerViewHolder) holder).bind((Item<String>) getItem(position), onItemClick);
                break;
            case R.layout.item_goods_2:
                ((GoodsGridViewHolder) holder).bind((Item<NavigatorBean.GoodsListBean>) getItem(position), onItemClick, onAddClick);
                break;
        }

    }

    private Object getItem(int position) {
        return objects.get(position);
    }


    private Boolean hasExtraRow() {
        return netWorkState != null && !netWorkState.equals(NetWorkState.loaded());
    }

    @Override
    public int getItemCount() {
        return objects == null ? 0 : hasExtraRow() ? objects.size() + 1 : objects.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return R.layout.network_state_item;
        } else {
            switch (objects.get(position).getType()) {
                case 0:
                    return R.layout.item_goods_navigation;
                case 1:
                    return R.layout.item_goods;
                case 2:
                    return R.layout.item_goods_banner;
                case 3:
                    return R.layout.item_goods_2;
                default:
                    return R.layout.item_goods;
            }

        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);

            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case R.layout.item_goods_navigation:
                        case R.layout.item_goods: //这两种方式都是一列的，所以返回6
                            return 6;
                        case R.layout.item_goods_2: //两列，返回3
                            return 3;
                        default:
                            return 6;
                    }
                }
            });
        }
    }

    public void setNetworkState(NetWorkState newNetWorkState) {
        NetWorkState previousState = this.netWorkState;
        Boolean hadExtraRow = hasExtraRow();
        this.netWorkState = newNetWorkState;
        Boolean hasExtraRow = hasExtraRow();
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (hasExtraRow && previousState != newNetWorkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    public static final DiffUtil.ItemCallback<GoodsListRowsBean> DIFF_CALLBACK = new DiffUtil.ItemCallback<GoodsListRowsBean>() {
        @Override
        public boolean areItemsTheSame(@NonNull GoodsListRowsBean oldItem, @NonNull GoodsListRowsBean newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull GoodsListRowsBean oldItem, @NonNull GoodsListRowsBean newItem) {
            return oldItem.equals(newItem);
        }
    };

}
