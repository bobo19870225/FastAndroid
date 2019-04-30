package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class MessageVM extends ListViewModel {
    public MessageVM(@NonNull Application application) {
        super(application);
    }

    @Override
    protected Integer setPageSize() {
        return null;
    }

    @Override
    public void init(Object data) {

    }


    @NonNull
    @Override
    public Call<PageBean> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams params) {
        return null;
    }

    @Override
    public void setLoadInitialCallback(PageBean body, PageKeyedDataSource.LoadInitialCallback callback) {

    }

    @NonNull
    @Override
    public Call<PageBean> setLoadAfterCall(PageKeyedDataSource.LoadParams params) {
        return null;
    }

    @Override
    public boolean setLoadCallback(PageBean body, PageKeyedDataSource.LoadParams params, PageKeyedDataSource.LoadCallback callback, Listing listing) {
        return false;
    }
}
