package com.jinkan.www.fastandroid.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.Subjects;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
@Singleton
public class MovieAdapter extends PagedListAdapter<Subjects, MovieViewHolder> {
    @Inject
    public MovieAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    /**
     * 后台线程DiffUtil类回调： 计算新的List和原来的List的差距
     */
    public static final DiffUtil.ItemCallback<Subjects> DIFF_CALLBACK = new DiffUtil.ItemCallback<Subjects>() {
        @Override
        public boolean areItemsTheSame(@NonNull Subjects oldItem, @NonNull Subjects newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Subjects oldItem, @NonNull Subjects newItem) {
            return oldItem.equals(newItem);
        }
    };

}
