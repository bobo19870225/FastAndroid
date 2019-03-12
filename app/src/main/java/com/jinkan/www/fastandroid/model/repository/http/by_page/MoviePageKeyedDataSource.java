package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.Movie;
import com.jinkan.www.fastandroid.model.Subjects;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */
@Singleton
public class MoviePageKeyedDataSource extends PageKeyedDataSource<String, Subjects> {
    @Inject
    ApiService apiService;

    @Inject
    public MoviePageKeyedDataSource() {
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Subjects> callback) {
        Call<Movie> topMovie = apiService.getTopMovie(0, params.requestedLoadSize);
        try {
            Response<Movie> listResponse = topMovie.execute();
            callback.onResult(listResponse.body().getSubjects(), "0", "10");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Subjects> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Subjects> callback) {
        Call<Movie> topMovie = apiService.getTopMovie(Integer.parseInt(params.key), params.requestedLoadSize);
        try {
            Response<Movie> response = topMovie.execute();
            if (response.isSuccessful()) {
                Movie body = response.body();
                if (body != null) {
                    callback.onResult(body.getSubjects(), "10");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
