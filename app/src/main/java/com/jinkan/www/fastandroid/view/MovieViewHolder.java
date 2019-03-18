package com.jinkan.www.fastandroid.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.Subjects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {
    private TextView title;

    private MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
    }

    public static MovieViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    void bind(Subjects movie) {
        title.setText(movie.getTitle());
    }

}
