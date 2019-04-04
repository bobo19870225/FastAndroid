package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.NetWorkState;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.Message;

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

public abstract class BasePageKeyedDataSource<Key, Value> extends PageKeyedDataSource<Key, Value> {


    private Executor NETWORK_IO = Executors.newFixedThreadPool(5);
    protected Listing<Value> listing;
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
        Call<Message<Value>> call = setLoadInitialCall(apiService, params);
        try {
            Response<Message<Value>> response = call.execute();
            Message<Value> body = response.body();
            if (body != null) {
                function = null;

                if (body.getCode() == 0) {
                    listing.networkState.postValue(NetWorkState.loaded());
                    setLoadInitialCallback(body, callback);
                } else {
                    listing.networkState.postValue(NetWorkState.error(body.getMsg()));
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
    protected abstract Call<Message<Value>> setLoadInitialCall(ApiService apiService, LoadInitialParams<Key> params);

    protected abstract void setLoadInitialCallback(Message<Value> body, LoadInitialCallback<Key, Value> callback);

    @Override
    public void loadBefore(@NonNull LoadParams<Key> params, @NonNull LoadCallback<Key, Value> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Key> params, @NonNull LoadCallback<Key, Value> callback) {
        Call<Message<Value>> call = setLoadAfterCall(apiService, params);
//                apiService.getTopMovie(Integer.parseInt(params.key), params.requestedLoadSize);
        try {
            Response<Message<Value>> response = call.execute();
            if (response.isSuccessful()) {
                Message<Value> body = response.body();
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
    protected abstract Call<Message<Value>> setLoadAfterCall(ApiService apiService, LoadParams<Key> params);

    /**
     * @param body     ..
     * @param params   ..
     * @param callback ..
     * @return 是否成功获取数据
     */
    protected abstract boolean setLoadCallback(Message<Value> body, LoadParams<Key> params, LoadCallback<Key, Value> callback);
}
