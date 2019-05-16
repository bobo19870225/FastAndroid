package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.BonusBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;

import retrofit2.Call;

/**
 * Created by Sampson on 2019-05-16.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.ChoseBonusActivity}
 */
public class ChoseBonusVM extends ListViewModel<Integer, BonusBean> {
    private ApiService apiService;
    private String sessionID;

    public ChoseBonusVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @Override
    public Call<PageBean<BonusBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getMyMemberBonusList(sessionID, 1);
    }

    @Override
    public void setLoadInitialCallback(PageBean<BonusBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, BonusBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }

    @Override
    public Call<PageBean<BonusBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return null;
    }

    @Override
    public boolean setLoadCallback(PageBean<BonusBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, BonusBean> callback, Listing<BonusBean> listing) {
        return true;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());
    }
}
