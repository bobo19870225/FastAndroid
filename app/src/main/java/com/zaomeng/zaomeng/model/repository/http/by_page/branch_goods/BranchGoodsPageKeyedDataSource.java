package com.zaomeng.zaomeng.model.repository.http.by_page.branch_goods;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyedDataSource;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */

public class BranchGoodsPageKeyedDataSource extends BasePageKeyedDataSource<Integer, BranchGoodsBean> {

    private String navigatorID;
    private String objectDefineID;


    BranchGoodsPageKeyedDataSource(Listing<BranchGoodsBean> listing,
                                   ApiService apiService,
                                   String navigatorID,
                                   String objectDefineID) {
        super(listing, apiService);
        this.navigatorID = navigatorID;
        this.objectDefineID = objectDefineID;

    }


    @NonNull
    @Override
    protected Call<PageBean<BranchGoodsBean>> setLoadInitialCall(ApiService apiService, LoadInitialParams<Integer> params) {
        return apiService.getNavigatorReleaseList(1, params.requestedLoadSize, null, objectDefineID, navigatorID);
    }

    @Override
    protected void setLoadInitialCallback(PageBean<BranchGoodsBean> body, LoadInitialCallback<Integer, BranchGoodsBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 0, 2);
    }

    @NonNull
    @Override
    protected Call<PageBean<BranchGoodsBean>> setLoadAfterCall(ApiService apiService, LoadParams<Integer> params) {
        return apiService.getNavigatorReleaseList(params.key, params.requestedLoadSize, null, objectDefineID, navigatorID);
    }


    @Override
    protected boolean setLoadCallback(PageBean<BranchGoodsBean> body, LoadParams<Integer> params, LoadCallback<Integer, BranchGoodsBean> callback) {
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
