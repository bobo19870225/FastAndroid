package com.zaomeng.zaomeng.model.repository.http.by_page.base;

import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;

import retrofit2.Call;

/**
 * Created by Sampson on 2019-04-30.
 * FastAndroid
 */
public interface InterfacePageRepository<Key, Value> {

    Call<PageBean<Value>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Key> params);

    void setLoadInitialCallback(PageBean<Value> body, PageKeyedDataSource.LoadInitialCallback<Key, Value> callback);


    Call<PageBean<Value>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Key> params);

    /**
     * @param body     ..
     * @param params   ..
     * @param callback ..
     * @return 是否成功获取数据
     */
    boolean setLoadCallback(PageBean<Value> body,
                            PageKeyedDataSource.LoadParams<Key> params,
                            PageKeyedDataSource.LoadCallback<Key, Value> callback, Listing<Value> listing);

}
