package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;

import retrofit2.Call;

/**
 * Created by Sampson on 2019-05-04.
 * FastAndroid
 */
public class AddressManageVM extends ListViewModel<Integer, MemberShopBean> {
    public AddressManageVM(@NonNull Application application) {
        super(application);
    }

    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @Override
    public Call<PageBean<MemberShopBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return null;
    }

    @Override
    public void setLoadInitialCallback(PageBean<MemberShopBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, MemberShopBean> callback) {

    }

    @Override
    public Call<PageBean<MemberShopBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return null;
    }

    @Override
    public boolean setLoadCallback(PageBean<MemberShopBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, MemberShopBean> callback, Listing<MemberShopBean> listing) {
        return false;
    }

    @Override
    public void init(Object data) {

    }
}
