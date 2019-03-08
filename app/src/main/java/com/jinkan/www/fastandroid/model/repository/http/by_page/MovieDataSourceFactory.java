package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.Movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */
public class MovieDataSourceFactory extends DataSource.Factory<String, Movie> {
    public final MutableLiveData<MoviePageKeyedDataSource> sourceLiveData = new MutableLiveData<>();


    @NonNull
    @Override
    public DataSource<String, Movie> create() {
        MoviePageKeyedDataSource moviePageKeyedDataSource = new MoviePageKeyedDataSource();
        sourceLiveData.setValue(moviePageKeyedDataSource);
        return moviePageKeyedDataSource;
    }
}
