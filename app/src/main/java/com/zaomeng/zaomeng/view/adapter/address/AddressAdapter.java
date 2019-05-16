package com.zaomeng.zaomeng.view.adapter.address;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import java.util.List;

import kotlin.Unit;

/**
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 */
public class AddressAdapter extends RecyclerView.Adapter {
    private List<MemberShopBean> list;
    private OnItemClick<MemberShopBean> onItemClick;

    public void setList(List<MemberShopBean> list) {
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            this.list.get(i).setSelect(false);
        }
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick<MemberShopBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return AddressViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((AddressViewHolder) holder).bind(list.get(position), onItemClick,
                () -> {
                    for (int i = 0; i < list.size(); i++) {
                        if (i == position) {
                            list.get(i).setSelect(true);
                        } else {
                            list.get(i).setSelect(false);
                        }

                    }
                    notifyDataSetChanged();
                    return Unit.INSTANCE;
                });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
