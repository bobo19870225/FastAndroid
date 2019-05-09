package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PayBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;

/**
 * Created by Sampson on 2019-05-02.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.OrderSettlementActivity}
 */
public class OrderSettlementVM extends BaseViewModel {

    private ApiService apiService;
    private String sessionID;

    public final MediatorLiveData<Resource<Bean<String>>> ldSubmitOrder = new MediatorLiveData<>();
    public OrderSettlementVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());
    }

    public LiveData<Resource<PageBean<MemberShopBean>>> getAddress() {
        return apiService.getMemberShopListLD(sessionID);
    }

    public void submitOrder() {
        ldSubmitOrder.addSource(apiService.createMemberOrderFromCart(sessionID,
                "卢声波",
                "18101603953",
                "上海市，天目中路538弄1号6B"), ldSubmitOrder::setValue);
    }

    public LiveData<Resource<PayBean>> appApplyMemberOrderPay(String memberPaymentID) {
        return apiService.appApplyMemberOrderPay(sessionID,
                "402892e96a4ed7a4016a4eda5984000a",
                "1", null, memberPaymentID);
    }
}
