package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.Movie;
import com.jinkan.www.fastandroid.model.Subjects;
import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.NetWorkState;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */

public class MoviePageKeyedDataSource extends PageKeyedDataSource<String, Subjects> {


    private Executor NETWORK_IO = Executors.newFixedThreadPool(5);
    private Listing<Subjects> listing;
    private ApiService apiService;
    private Function0 function;
    @Inject
    public MoviePageKeyedDataSource(Listing<Subjects> listing, ApiService apiService) {
        this.listing = listing;
        this.apiService = apiService;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<String, Subjects> callback) {

        listing.networkState.postValue(NetWorkState.loading());
        Call<Movie> topMovie = apiService.getTopMovie(0, params.requestedLoadSize);
        try {
            Response<Movie> listResponse = topMovie.execute();
            if (listResponse.body() != null) {
                function = null;
                callback.onResult(listResponse.body().getSubjects(), "0", "10");
                listing.networkState.postValue(NetWorkState.loaded());
            }
        } catch (IOException e) {
            function = () -> {
                loadInitial(params, callback);
                return Unit.INSTANCE;
            };
            listing.networkState.postValue(NetWorkState.error(e.toString()));
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Subjects> callback) {

    }

    private Integer integer = 10;
    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, Subjects> callback) {
        Call<Movie> topMovie = apiService.getTopMovie(Integer.parseInt(params.key), params.requestedLoadSize);
        try {
            Response<Movie> response = topMovie.execute();
            if (response.isSuccessful()) {
                Movie body = response.body();
                if (body != null) {
                    integer += 10;
                    if (integer == 30) {
                        callback.onResult(body.getSubjects(), null);
                    } else {
                        callback.onResult(body.getSubjects(), String.valueOf(integer));
                    }

                }
            }

        } catch (IOException e) {
            function = () -> {
                loadAfter(params, callback);
                return Unit.INSTANCE;
            };
            e.printStackTrace();
        }
    }


    void reTry() {
        Function0 function0 = function;
        function = null;
        if (function0 != null) {
            NETWORK_IO.execute(function0::invoke);

        }

    }


}
