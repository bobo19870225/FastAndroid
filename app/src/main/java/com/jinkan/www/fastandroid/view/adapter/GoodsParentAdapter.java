package com.jinkan.www.fastandroid.view.adapter;

import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class GoodsParentAdapter extends RecyclerView.Adapter<GoodsParentViewHolder> {
    private List list;
    private List<Boolean> isClicks;
    private Function0 function0;
    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public GoodsParentAdapter(List<String> list) {
        this.list = list;
        isClicks = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            isClicks.add(false);
        }

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
                onItemClick.onClick(holder.itemView, position);
            return Unit.INSTANCE;
        };
        holder.bind((String) list.get(position), position, function0, isClicks);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
