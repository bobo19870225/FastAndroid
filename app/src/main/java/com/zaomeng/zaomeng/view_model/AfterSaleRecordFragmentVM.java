package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.AfterSaleBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;

import retrofit2.Call;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.AfterSaleRecordFragment}
 */
public class AfterSaleRecordFragmentVM extends ListViewModel<Integer, AfterSaleBean> {
    private ApiService apiService;
    private String sessionID;

    public AfterSaleRecordFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @Override
    public Call<PageBean<AfterSaleBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getMemberOrderGoodsReturnList(sessionID);
    }

    @Override
    public void setLoadInitialCallback(PageBean<AfterSaleBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, AfterSaleBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);

    }

    @Override
    public Call<PageBean<AfterSaleBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
//        return apiService.getMemberOrderList(sessionID, 8, params.key, params.requestedLoadSize);
        return null;
    }

    @Override
    public boolean setLoadCallback(PageBean<AfterSaleBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, AfterSaleBean> callback, Listing<AfterSaleBean> listing) {
//        if (body.getHeader().getCode() == 0) {
//            int total = body.getBody().getData().getTotalPage();
//            if (total > params.key) {
//                callback.onResult(body.getBody().getData().getRows(), params.key + 1);
//            }
//        } else {
//            listing.networkState.postValue(NetWorkState.error(body.getHeader().getMsg()));
//        }
//        return body.getHeader().getCode() == 0;
        return true;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());
    }


}
