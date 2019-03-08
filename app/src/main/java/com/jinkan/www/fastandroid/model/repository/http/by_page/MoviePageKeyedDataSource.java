package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.Movie;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */
@Singleton
public class MoviePageKeyedDataSource extends PageKeyedDataSource<String, Movie> {
    @Inject
    ApiService apiService;

    @Inject
    public MoviePageKeyedDataSource() {
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Movie> callback) {
        apiService.getTopMovie(0, params.requestedLoadSize);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Movie> callback) {
        apiService.getTopMovie(Integer.parseInt(params.key), params.requestedLoadSize);
    }
}
