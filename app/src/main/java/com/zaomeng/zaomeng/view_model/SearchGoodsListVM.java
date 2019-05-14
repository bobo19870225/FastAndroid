package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

import retrofit2.Call;

/**
 * Created by Sampson on 2019-05-14.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.SearchGoodsListActivity}
 */
public class SearchGoodsListVM extends ListViewModel<Integer, GoodsListRowsBean> {
    private ApiService apiService;
    public final MutableLiveData<String> ldSearch = new MutableLiveData<>();
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public SearchGoodsListVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @Override
    public Call<PageBean<GoodsListRowsBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        if (listRequest == null)
            return null;
        return apiService.getGoodsShopList(1, params.requestedLoadSize, null, null, (String) listRequest);

    }

    @Override
    public void setLoadInitialCallback(PageBean<GoodsListRowsBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, GoodsListRowsBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }

    @Override
    public Call<PageBean<GoodsListRowsBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        if (listRequest == null)
            return null;
        return apiService.getGoodsShopList(params.key, params.requestedLoadSize, null, null, (String) listRequest);

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

    @Override
    public void init(Object data) {
        ldSearch.setValue((String) data);
    }

    public void cancel() {
        action.setValue("cancel");
    }
}
