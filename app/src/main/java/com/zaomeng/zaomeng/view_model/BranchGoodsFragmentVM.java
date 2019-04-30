package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.view.BranchGoodsFragment;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 * {@link BranchGoodsFragment}
 */
public class BranchGoodsFragmentVM extends ListViewModel<Integer, BranchGoodsBean> {
    private ApiService apiService;


    public BranchGoodsFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }


    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @NonNull
    @Override
    public Call<PageBean<BranchGoodsBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getNavigatorReleaseList(1, params.requestedLoadSize, null, "422429993732", (String) listRequest);

    }

    @Override
    public void setLoadInitialCallback(PageBean<BranchGoodsBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, BranchGoodsBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }

    @NonNull
    @Override
    public Call<PageBean<BranchGoodsBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return apiService.getNavigatorReleaseList(params.key, params.requestedLoadSize, null, "422429993732", (String) listRequest);
    }

    @Override
    public boolean setLoadCallback(PageBean<BranchGoodsBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, BranchGoodsBean> callback, Listing<BranchGoodsBean> listing) {
        if (body.getHeader().getCode() == 0) {
//            int currentPage = body.getBody().getData().getCurrentPage();
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
