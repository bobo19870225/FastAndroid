package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/4/21.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.ShoppingCartFragment}
 */
public class ShoppingCartFragmentVM extends ListViewModel<Integer, ShopCartBean> {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    private ApiService apiService;

    public ShoppingCartFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    public final MutableLiveData<String> ldGoodsNumber = new MediatorLiveData<>();
    public final MutableLiveData<String> ldFreight = new MediatorLiveData<>();
    public final MutableLiveData<String> ldTotal = new MediatorLiveData<>();

//    public final MutableLiveData<String> ldGoodsNumber = new MediatorLiveData<>();


    @Override
    public void init(Object data) {

    }


    /**
     * 删除商品
     */
    public void delete() {

    }

    /**
     * 全选商品
     */
    public void selectAll() {

    }

    /**
     * 结算
     */
    public void settlement() {
        action.setValue("settlement");
    }


    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @NonNull
    @Override
    public Call<PageBean<ShopCartBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getCartGoodsList(SharedPreerencesUtils.getSessionID(getApplication()), 1, params.requestedLoadSize);
    }

    @Override
    public void setLoadInitialCallback(PageBean<ShopCartBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, ShopCartBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }

    @NonNull
    @Override
    public Call<PageBean<ShopCartBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return apiService.getCartGoodsList(SharedPreerencesUtils.getSessionID(getApplication()), params.key, params.requestedLoadSize);
    }

    @Override
    public boolean setLoadCallback(PageBean<ShopCartBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, ShopCartBean> callback, Listing<ShopCartBean> listing) {
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
