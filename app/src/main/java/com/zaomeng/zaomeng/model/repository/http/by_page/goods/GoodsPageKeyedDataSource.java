package com.zaomeng.zaomeng.model.repository.http.by_page.goods;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyedDataSource;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */

public class GoodsPageKeyedDataSource extends BasePageKeyedDataSource<Integer, GoodsListRowsBean> {


    private String goodsCategoryID;
    private String memberID;

    public GoodsPageKeyedDataSource(Listing<GoodsListRowsBean> listing,
                                    ApiService apiService,
                                    String goodsCategoryID,
                                    String memberID) {
        super(listing, apiService);
        this.goodsCategoryID = goodsCategoryID;
        this.memberID = memberID;
    }


    @NonNull
    @Override
    protected Call<PageBean<GoodsListRowsBean>> setLoadInitialCall(ApiService apiService, LoadInitialParams<Integer> params) {
        return apiService.getGoodsShopList(1, params.requestedLoadSize, goodsCategoryID, memberID);
    }

    @Override
    protected void setLoadInitialCallback(PageBean<GoodsListRowsBean> body, LoadInitialCallback<Integer, GoodsListRowsBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 0, 2);
    }

    @NonNull
    @Override
    protected Call<PageBean<GoodsListRowsBean>> setLoadAfterCall(ApiService apiService, LoadParams<Integer> params) {
        return apiService.getGoodsShopList(params.key, params.requestedLoadSize, goodsCategoryID, memberID);
    }

    @Override
    protected boolean setLoadCallback(PageBean<GoodsListRowsBean> body, LoadParams<Integer> params, LoadCallback<Integer, GoodsListRowsBean> callback) {
        if (body.getHeader().getCode() == 0) {
            int currentPage = body.getBody().getData().getCurrentPage();
            int total = body.getBody().getData().getTotalPage();
            if (total > params.key) {
                callback.onResult(body.getBody().getData().getRows(), params.key + 1);
            }
        } else {
            listing.networkState.postValue(NetWorkState.error(body.getHeader().getMsg()));
        }
        return body.getHeader().getCode() == 0;
    }


}
