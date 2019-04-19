package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.NetWorkState;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.PageBean;

import androidx.annotation.NonNull;
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
        return apiService.getGoodsShopList(0, 10, goodsCategoryID, memberID);
    }

    @Override
    protected void setLoadInitialCallback(PageBean<GoodsListRowsBean> body, LoadInitialCallback<Integer, GoodsListRowsBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 0, 1);
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
