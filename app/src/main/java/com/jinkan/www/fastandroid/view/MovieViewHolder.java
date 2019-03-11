package com.jinkan.www.fastandroid.view;

import android.view.View;
import android.widget.TextView;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.model.Movie;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sampson on 2019/3/11.
 * FastAndroid
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {
    private TextView title;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
    }

    public void bind(Movie movie) {
        title.setText(movie.getTitle());
    }

}
