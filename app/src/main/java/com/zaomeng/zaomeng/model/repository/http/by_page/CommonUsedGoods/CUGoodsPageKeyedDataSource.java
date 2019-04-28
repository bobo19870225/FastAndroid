package com.zaomeng.zaomeng.model.repository.http.by_page.CommonUsedGoods;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyedDataSource;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */

public class CUGoodsPageKeyedDataSource extends BasePageKeyedDataSource<Integer, CollectInfoBean> {

    private String sessionID;
    private String objectDefineID;


    CUGoodsPageKeyedDataSource(Listing<CollectInfoBean> listing,
                               ApiService apiService,
                               String sessionID,
                               String objectDefineID) {
        super(listing, apiService);
        this.sessionID = sessionID;
        this.objectDefineID = objectDefineID;

    }


    @NonNull
    @Override
    protected Call<PageBean<CollectInfoBean>> setLoadInitialCall(ApiService apiService, LoadInitialParams<Integer> params) {
        return apiService.getCollectList(sessionID, objectDefineID, 1, params.requestedLoadSize);
    }

    @Override
    protected void setLoadInitialCallback(PageBean<CollectInfoBean> body, LoadInitialCallback<Integer, CollectInfoBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 0, 2);
    }

    @NonNull
    @Override
    protected Call<PageBean<CollectInfoBean>> setLoadAfterCall(ApiService apiService, LoadParams<Integer> params) {
        return apiService.getCollectList(sessionID, objectDefineID, params.key, params.requestedLoadSize);
    }


    @Override
    protected boolean setLoadCallback(PageBean<CollectInfoBean> body, LoadParams<Integer> params, LoadCallback<Integer, CollectInfoBean> callback) {
        if (body.getHeader().getCode() == 0) {
            int currentPage = body.getBody().getData().getCurrentPage();
            int total = body.getBody().getData().getTotal();
            if (total > params.key) {
                callback.onResult(body.getBody().getData().getRows(), params.key + 1);
            }
        } else {
            listing.networkState.postValue(NetWorkState.error(body.getHeader().getMsg()));
        }
        return body.getHeader().getCode() == 0;
    }


}
