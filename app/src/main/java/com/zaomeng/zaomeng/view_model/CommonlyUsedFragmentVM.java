package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;
import com.zaomeng.zaomeng.view.CommonlyUsedFragment;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 * {@link CommonlyUsedFragment}
 */
public class CommonlyUsedFragmentVM extends ListViewModel<Integer, CollectInfoBean> {
    private ApiService apiService;

    public CommonlyUsedFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }


    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @NonNull
    @Override
    public Call<PageBean<CollectInfoBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getCollectList(SharedPreerencesUtils.getSessionID(getApplication()), "422429993732", 1, params.requestedLoadSize);
    }

    @Override
    public void setLoadInitialCallback(PageBean<CollectInfoBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, CollectInfoBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }

    @NonNull
    @Override
    public Call<PageBean<CollectInfoBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return apiService.getCollectList(SharedPreerencesUtils.getSessionID(getApplication()), "422429993732", params.key, params.requestedLoadSize);
    }

    @Override
    public boolean setLoadCallback(PageBean<CollectInfoBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, CollectInfoBean> callback, Listing<CollectInfoBean> listing) {
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

    /**
     * 商品规格
     */
    public LiveData<Resource<SpecificationsBean>> getObjectFeatureItemList(String objectID) {
        return apiService.getObjectFeatureItemList(objectID);
    }

    public LiveData<Resource<Bean<String>>> addGoodsShopToCart(@NonNull String goodsShopID, @NonNull Integer qty, String objectFeatureItemID1) {
        String sessionID = SharedPreerencesUtils.getSessionID(getApplication());
        if (sessionID != null) {
            return apiService.addGoodsShopToCart(sessionID,
                    goodsShopID, qty, objectFeatureItemID1);

        }
        return null;
    }
}
