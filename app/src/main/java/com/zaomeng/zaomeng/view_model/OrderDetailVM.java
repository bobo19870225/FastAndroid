package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.OrderBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019-05-17.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.OrderDetailActivity}
 */
public class OrderDetailVM extends BaseViewModel {
    public final MutableLiveData<String> ldOrderNo = new MutableLiveData<>();
    public final MutableLiveData<String> ldTime = new MutableLiveData<>();
    public final MutableLiveData<String> ldBonus = new MutableLiveData<>();
    public final MutableLiveData<String> ldTotal = new MutableLiveData<>();
    public final MutableLiveData<String> ldDiscount = new MutableLiveData<>();
    public final MutableLiveData<String> ldOrderNumber = new MutableLiveData<>();
    public final MutableLiveData<String> ldAddress = new MutableLiveData<>();
    public final MutableLiveData<String> ldUserName = new MutableLiveData<>();
    public final MutableLiveData<String> ldUserPhone = new MutableLiveData<>();
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    private ApiService apiService;
    private String sessionID;

    public OrderDetailVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());

    }

    public LiveData<Resource<Bean<OrderBean>>> getMemberOrderDetail(String memberOrderID) {
        return apiService.getMemberOrderDetail(sessionID, memberOrderID);
    }

    public void goodsDetail() {
        action.setValue("goodsDetail");
    }
}
