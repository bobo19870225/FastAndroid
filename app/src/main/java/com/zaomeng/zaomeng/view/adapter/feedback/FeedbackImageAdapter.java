package com.zaomeng.zaomeng.view.adapter.feedback;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.view.adapter.OnItemClick;

import java.util.List;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class FeedbackImageAdapter extends RecyclerView.Adapter {
    private List<String> list;
    private OnItemClick<String> onItemClick;
    private OnItemClick<String> onAddItemClick;

    public void setOnAddItemClick(OnItemClick<String> onAddItemClick) {
        this.onAddItemClick = onAddItemClick;
    }

    public void setOnItemClick(OnItemClick<String> onItemClick) {
        this.onItemClick = onItemClick;
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.item_image_small) {
            return FeedbackViewHolder.create(parent);
        } else {
            return AddViewHolder.create(parent);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.layout.item_image_small) {
            ((FeedbackViewHolder) holder).bind(list.get(position), onItemClick);
        } else {
            ((AddViewHolder) holder).bind(onAddItemClick);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null || list.size() == 0) {
            return 1;
        } else if (list.size() < 3) {
            return list.size() + 1;
        } else {
            return 3;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list == null || list.size() == 0) {
            return R.layout.item_add;
        } else if (list.size() < 3) {
            if (position == list.size()) {
                return R.layout.item_add;
            } else {
                return R.layout.item_image_small;
            }
        } else {
            return R.layout.item_image_small;
        }
//        return super.getItemViewType(position);

    }
}
