package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;

import retrofit2.Call;

/**
 * Created by Sampson on 2019-05-27.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.ChoseAddressActivity}
 */
public class ChoseAddressVM extends ListViewModel<Integer, MemberShopBean> {
    private ApiService apiService;
    private String sessionID;

    public ChoseAddressVM(Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }


    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());
    }

    @Override
    public Call<PageBean<MemberShopBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getMemberShopList(sessionID, 1);
    }

    @Override
    public void setLoadInitialCallback(PageBean<MemberShopBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, MemberShopBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }

    @Override
    public Call<PageBean<MemberShopBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return null;
    }

    @Override
    public boolean setLoadCallback(PageBean<MemberShopBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, MemberShopBean> callback, Listing<MemberShopBean> listing) {
        return false;
    }
}
