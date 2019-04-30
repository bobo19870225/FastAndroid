package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 *{@link com.zaomeng.zaomeng.view.MessageActivity}
 */
public class MessageVM extends ListViewModel<Integer, BranchGoodsBean> {
    private ApiService apiService;

    public MessageVM(@NonNull Application application, ApiService apiService) {
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

    }


    @Override
    public Call<PageBean<BranchGoodsBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getMyMessageList(1, params.requestedLoadSize,
                SharedPreerencesUtils.getSessionID(getApplication()),
                1);
    }

    @Override
    public void setLoadInitialCallback(PageBean<BranchGoodsBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, BranchGoodsBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);

    }


    @Override
    public Call<PageBean<BranchGoodsBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return apiService.getMyMessageList(params.key, params.requestedLoadSize, SharedPreerencesUtils.getSessionID(getApplication()), 1);

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
