package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.SortFragment}
 */
public class SortFragmentVM extends ListViewModel<Integer, GoodsListRowsBean> {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    private ApiService apiService;


    public SortFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;

    }


    @Override
    protected Integer setPageSize() {
        return 10;
    }

    public LiveData<Resource<PageBean<GoodsSuperBean>>> getNodeCategoryList() {
        return apiService.getNodeCategoryList("c82678b8ea0149c18fe6ac5ac8590d73", 1);
    }

    public void search() {
        action.setValue("search");
    }
    @Override
    public void init(Object data) {

    }


    @Override
    public Call<PageBean<GoodsListRowsBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        if (listRequest == null)
            return null;
        return apiService.getGoodsShopList(1, params.requestedLoadSize, (String) listRequest, null);
    }

    @Override
    public void setLoadInitialCallback(PageBean<GoodsListRowsBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, GoodsListRowsBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }


    @Override
    public Call<PageBean<GoodsListRowsBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        if (listRequest == null)
            return null;
        return apiService.getGoodsShopList(params.key, params.requestedLoadSize, (String) listRequest, null);
    }

    @Override
    public boolean setLoadCallback(PageBean<GoodsListRowsBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, GoodsListRowsBean> callback, Listing<GoodsListRowsBean> listing) {
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

