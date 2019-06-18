package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/4/21.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.ShoppingCartFragment}
 */
public class ShoppingCartFragmentVM extends ListViewModel<Integer, ShopCartBean> {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MutableLiveData<Integer> ldGoodsTotal = new MutableLiveData<>();
    private ApiService apiService;
    private String sessionID;

    ShoppingCartFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());
    }

    public final MutableLiveData<String> ldGoodsNumber = new MediatorLiveData<>();
    public final MutableLiveData<Double> ldTotal = new MediatorLiveData<>();
//    public final MutableLiveData<Double> ldDiscount = new MediatorLiveData<>();


    @Override
    public void init(Object data) {
//        ldIsSelectAll.setValue(false);
    }


    /**
     * 删除商品
     */
    public void delete() {
        action.setValue("delete");
    }

    public LiveData<Resource<Bean<String>>> removeCartGoods(String cartGoodsID) {
        return apiService.removeCartGoods(sessionID, cartGoodsID);
    }

    /**
     * 全选商品
     */
    public void selectAll() {
//        apiService.createMemberOrderFromCart(sessionID,)
        action.setValue("selectAll");
    }

    /**
     * 结算
     */
    public void settlement() {
        action.setValue("settlement");
    }


    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @NonNull
    @Override
    public Call<PageBean<ShopCartBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getCartGoodsList(sessionID, 1, params.requestedLoadSize);
    }

    @Override
    public void setLoadInitialCallback(PageBean<ShopCartBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, ShopCartBean> callback) {
        ldGoodsTotal.postValue(body.getBody().getData().getTotal());
        ldTotal.postValue(body.getBody().getPriceTotal());
//        ldDiscount.postValue(body.getBody().getDiscountPrice());
//        ldFreight.postValue("-" + FormatUtils.numberFormatMoneyString(body.getBody().getDiscountPrice()));
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }

    @NonNull
    @Override
    public Call<PageBean<ShopCartBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return apiService.getCartGoodsList(sessionID, params.key, params.requestedLoadSize);
    }

    @Override
    public boolean setLoadCallback(PageBean<ShopCartBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, ShopCartBean> callback, Listing<ShopCartBean> listing) {
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

    public LiveData<Resource<Bean<String>>> selectGoods(String cartGoodsID, Integer isSelect) {
        return apiService.selectCartGoods(sessionID, cartGoodsID, isSelect);
    }

    public LiveData<Resource<Bean<String>>> updateCartGoodsNumber(String cartGoodsID, int qty) {
        return apiService.updateCartGoodsNumber(sessionID, cartGoodsID, qty);
    }

    public LiveData<Resource<Bean<String>>> getParameterValueByCode() {
        return apiService.getParameterValueByCode("minOrderFee");
    }
}
