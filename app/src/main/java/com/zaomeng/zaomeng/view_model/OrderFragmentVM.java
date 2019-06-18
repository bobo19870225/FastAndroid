package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.AliPayBean;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.OrderBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.WeChatPayBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.view.AllOrderFragment;

import retrofit2.Call;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 * {@link AllOrderFragment}
 */
public class OrderFragmentVM extends ListViewModel<Integer, OrderBean> {
    private ApiService apiService;
    private String sessionID;

    public OrderFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @Override
    public Call<PageBean<OrderBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getMemberOrderList(sessionID, null, 1, params.requestedLoadSize);
    }

    @Override
    public void setLoadInitialCallback(PageBean<OrderBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, OrderBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);

    }

    @Override
    public Call<PageBean<OrderBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return apiService.getMemberOrderList(sessionID, null, params.key, params.requestedLoadSize);

    }

    @Override
    public boolean setLoadCallback(PageBean<OrderBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, OrderBean> callback, Listing<OrderBean> listing) {
        if (body.getHeader().getCode() == 0) {
//            int currentPage = body.getBody().getData().getCurrentPage();
            int total = body.getBody().getData().getTotalPage();
            if (total >= params.key) {
                callback.onResult(body.getBody().getData().getRows(), params.key + 1);
            }
        } else {
            listing.networkState.postValue(NetWorkState.error(body.getHeader().getMsg()));
        }
        return body.getHeader().getCode() == 0;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());
    }

    /**
     * 微信：402892e96a4ed7a4016a4eda9107000c
     * 支付宝：402892e96a4ed7a4016a4eda5984000a
     *
     */
    public LiveData<Resource<AliPayBean>> appApplyMemberOrderPay(String memberOrderID) {
        return apiService.appApplyMemberOrderPay(sessionID,
                "402892e96a4ed7a4016a4eda5984000a",
                "1", memberOrderID, null);
    }

    public LiveData<Resource<WeChatPayBean>> appApplyMemberOrderPayForWeCat(String memberOrderID) {
        return apiService.appApplyMemberOrderPayForWeChat(sessionID,
                "402892e96a4ed7a4016a4eda9107000c",
                "1", memberOrderID, null);
    }

    public LiveData<Resource<Bean<String>>> cancelMemberOrder(String memberOrderID) {
        return apiService.cancelMemberOrder(sessionID, memberOrderID);
    }

    public LiveData<Resource<Bean<String>>> confirmMemberOrder(String memberOrderID) {
        return apiService.confirmMemberOrder(sessionID, memberOrderID);
    }

    public LiveData<Resource<Bean<String>>> applyReturnMemberOrder(String memberOrderID) {
        return apiService.applyReturnMemberOrder(sessionID, memberOrderID);
    }
}
