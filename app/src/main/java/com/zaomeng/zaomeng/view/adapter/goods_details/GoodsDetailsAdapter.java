package com.zaomeng.zaomeng.view.adapter.goods_details;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsHeaderBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsImageBean;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class GoodsDetailsAdapter extends BasePagedListAdapter<GoodsDetailsImageBean> {

    public GoodsDetailsAdapter(Function0 retryCallback) {
        super(DIFF_CALLBACK, retryCallback);
    }


    @NonNull
    @Override
    protected RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.item_image:
                return GoodsDetailsViewHolder.create(parent);
            case R.layout.item_goods_details_header:
                return GoodsDetailsHeaderViewHolder.create(parent);
            default:
                throw new IllegalArgumentException("unknown view type $viewType");
        }
    }


    @Override
    protected void viewHolderBind(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case R.layout.item_image:
                ((GoodsDetailsViewHolder) holder).bind(getItem(position - 1));
                break;
            case R.layout.item_goods_details_header:
                ((GoodsDetailsHeaderViewHolder) holder).bind(goodsDetailsHeaderBean);
                break;


        }
    }


    @Override
    public int getItemCount() {
        int itemCount = super.getItemCount();
        return itemCount + 1;
    }


    @Override
    protected int giveItemViewType(int position) {
        if (position == 0) {
            return R.layout.item_goods_details_header;
        } else {
            return R.layout.item_image;
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
