package com.zaomeng.zaomeng.model.repository.http.by_page.base;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */

public abstract class BasePageKeyedDataSource<Key, Value> extends PageKeyedDataSource<Key, Value> {


    private Executor NETWORK_IO = Executors.newFixedThreadPool(5);
    protected Listing<Value> listing;
    private ApiService apiService;
    private Function0 function;


    public BasePageKeyedDataSource(Listing<Value> listing, ApiService apiService) {
        this.listing = listing;
        this.apiService = apiService;
    }


    public void reTry() {
        Function0 function0 = function;
        function = null;
        if (function0 != null) {
            NETWORK_IO.execute(function0::invoke);

        }

    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Key> params, @NonNull LoadInitialCallback<Key, Value> callback) {
        listing.networkState.postValue(NetWorkState.loading());
        Call<PageBean<Value>> call = setLoadInitialCall(apiService, params);
        try {
            Response<PageBean<Value>> response = call.execute();
            PageBean<Value> body = response.body();
            if (body != null) {
                function = null;

                if (body.getHeader().getCode() == 0) {
                    listing.networkState.postValue(NetWorkState.loaded());
                    setLoadInitialCallback(body, callback);
                } else {
                    listing.networkState.postValue(NetWorkState.error(body.getHeader().getMsg()));
                }
//
//                callback.onResult(listResponse.body().getSubjects(), "0", "10");

            }
        } catch (IOException e) {
            function = () -> {
                loadInitial(params, callback);
                return Unit.INSTANCE;
            };
            listing.networkState.postValue(NetWorkState.error(e.toString()));
        }
    }

    @NonNull
    protected abstract Call<PageBean<Value>> setLoadInitialCall(ApiService apiService, LoadInitialParams<Key> params);

    protected abstract void setLoadInitialCallback(PageBean<Value> body, LoadInitialCallback<Key, Value> callback);

    @Override
    public void loadBefore(@NonNull LoadParams<Key> params, @NonNull LoadCallback<Key, Value> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Key> params, @NonNull LoadCallback<Key, Value> callback) {
        Call<PageBean<Value>> call = setLoadAfterCall(apiService, params);
//                apiService.getTopMovie(Integer.parseInt(params.key), params.requestedLoadSize);
        try {
            Response<PageBean<Value>> response = call.execute();
            if (response.isSuccessful()) {
                PageBean<Value> body = response.body();
                if (body != null) {
                    function = null;
                    if (setLoadCallback(body, params, callback))
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

    @NonNull
    protected abstract Call<PageBean<Value>> setLoadAfterCall(ApiService apiService, LoadParams<Key> params);

    /**
     * @param body     ..
     * @param params   ..
     * @param callback ..
     * @return 是否成功获取数据
     */
    protected abstract boolean setLoadCallback(PageBean<Value> body, LoadParams<Key> params, LoadCallback<Key, Value> callback);
}
