package com.zaomeng.zaomeng.view.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class GoodsParentAdapter extends RecyclerView.Adapter<GoodsParentViewHolder> {
    private List<GoodsSuperBean> list;
    private List<Boolean> isClicks;
    private Function0 function0;
    private OnItemClick<GoodsSuperBean> onItemClick;
    private Context context;
    public void setOnItemClick(OnItemClick<GoodsSuperBean> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public GoodsParentAdapter(Context context, List<GoodsSuperBean> list) {
        this.context = context;
        this.list = list;
        isClicks = new ArrayList<>();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                isClicks.add(false);
            }
        }
    }

    public void setSelect(int position) {
        isClicks.set(position, true);
        notifyDataSetChanged();
    }

    public void setList(List<GoodsSuperBean> list) {
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            isClicks.add(false);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GoodsParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return GoodsParentViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodsParentViewHolder holder, int position) {
        function0 = () -> {
            isClicks = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                isClicks.add(false);
            }
            isClicks.set(position, true);
            notifyDataSetChanged();
            if (onItemClick != null)
                onItemClick.onClick(holder.itemView, list.get(position), position);
            return Unit.INSTANCE;
        };
        holder.bind(context, list.get(position), position, function0, isClicks);

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
