package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.NetWorkState;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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

public abstract class BasePageKeyedDataSource<Key, Value, T> extends PageKeyedDataSource<Key, Value> {


    private Executor NETWORK_IO = Executors.newFixedThreadPool(5);
    private Listing<Value> listing;
    private ApiService apiService;
    private Function0 function;


    public BasePageKeyedDataSource(Listing<Value> listing, ApiService apiService) {
        this.listing = listing;
        this.apiService = apiService;
    }


    void reTry() {
        Function0 function0 = function;
        function = null;
        if (function0 != null) {
            NETWORK_IO.execute(function0::invoke);

        }

    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Key> params, @NonNull LoadInitialCallback<Key, Value> callback) {
        listing.networkState.postValue(NetWorkState.loading());
        Call<T> call = setLoadInitialCall(apiService, params);
        try {
            Response<T> response = call.execute();
            T body = response.body();
            if (body != null) {
                function = null;
                setLoadInitialCallback(body, callback);
//                callback.onResult(listResponse.body().getSubjects(), "0", "10");
                listing.networkState.postValue(NetWorkState.loaded());
            }
        } catch (IOException e) {
            function = () -> {
                loadInitial(params, callback);
                return Unit.INSTANCE;
            };
            listing.networkState.postValue(NetWorkState.error(e.toString()));
        }
    }


    protected abstract Call<T> setLoadInitialCall(ApiService apiService, LoadInitialParams<Key> params);

    protected abstract void setLoadInitialCallback(T body, LoadInitialCallback<Key, Value> callback);

    @Override
    public void loadBefore(@NonNull LoadParams<Key> params, @NonNull LoadCallback<Key, Value> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Key> params, @NonNull LoadCallback<Key, Value> callback) {
        Call<T> call = setLoadAfterCall(apiService, params);
//                apiService.getTopMovie(Integer.parseInt(params.key), params.requestedLoadSize);
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                T body = response.body();
                if (body != null) {
                    function = null;
                    setLoadCallback(body, params, callback);
                    listing.networkState.postValue(NetWorkState.loaded());
                }
            }

        } catch (IOException e) {
            function = () -> {
                loadAfter(params, callback);
                return Unit.INSTANCE;
            };
            listing.networkState.postValue(NetWorkState.error(e.toString()));
        }
    }

    protected abstract Call<T> setLoadAfterCall(ApiService apiService, LoadParams<Key> params);

    protected abstract void setLoadCallback(T body, LoadParams<Key> params, LoadCallback<Key, Value> callback);
}
