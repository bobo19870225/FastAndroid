package com.zaomeng.zaomeng.model.repository.http.by_page.shop_cart;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyedDataSource;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */

public class ShopCartPageKeyedDataSource extends BasePageKeyedDataSource<Integer, ShopCartBean> {

    private String sessionID;


    ShopCartPageKeyedDataSource(Listing<ShopCartBean> listing,
                                ApiService apiService,
                                String sessionID) {
        super(listing, apiService);
        this.sessionID = sessionID;


    }


    @NonNull
    @Override
    protected Call<PageBean<ShopCartBean>> setLoadInitialCall(ApiService apiService, LoadInitialParams<Integer> params) {
        return apiService.getCartGoodsList(sessionID, 1, params.requestedLoadSize);
    }

    @Override
    protected void setLoadInitialCallback(PageBean<ShopCartBean> body, LoadInitialCallback<Integer, ShopCartBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }

    @NonNull
    @Override
    protected Call<PageBean<ShopCartBean>> setLoadAfterCall(ApiService apiService, LoadParams<Integer> params) {
        return apiService.getCartGoodsList(sessionID, params.key, params.requestedLoadSize);
    }


    @Override
    protected boolean setLoadCallback(PageBean<ShopCartBean> body, LoadParams<Integer> params, LoadCallback<Integer, ShopCartBean> callback) {
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
