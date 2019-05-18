package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.AliPayBean;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BonusBean;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.bean.WeChatPayBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019-05-02.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.OrderSettlementActivity}
 */
public class OrderSettlementVM extends BaseViewModel {

    public String address;
    public String user;
    public String phone;
    private ApiService apiService;
    private String sessionID;
    public String bonusID;
    public final MutableLiveData<String> ldOrderNumber = new MutableLiveData<>();
    public final MutableLiveData<String> ldBonus = new MutableLiveData<>();
//    public final MutableLiveData<String> ldTotal = new MutableLiveData<>();

    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MediatorLiveData<Resource<Bean<String>>> ldSubmitOrder = new MediatorLiveData<>();

    public OrderSettlementVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }


    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());

    }

    /**
     * 0未审核，1审核通过，2审核拒绝
     */
    public LiveData<Resource<PageBean<MemberShopBean>>> getAddress() {
        return apiService.getMemberShopListLD(sessionID, 1);
    }

    public void submitOrder() {
        if (address == null) {
            action.setValue("toast:请选择收货地址");
        }
        ldSubmitOrder.addSource(apiService.createMemberOrderFromCart
                (sessionID, user, phone, address, bonusID), ldSubmitOrder::setValue);
    }

    public LiveData<Resource<AliPayBean>> appApplyMemberOrderPay(String memberPaymentID) {
        return apiService.appApplyMemberOrderPay(sessionID,
                "402892e96a4ed7a4016a4eda5984000a",
                "1", null, memberPaymentID);
    }

    public LiveData<Resource<WeChatPayBean>> appApplyMemberOrderPayForWeChat(String memberPaymentID) {
        return apiService.appApplyMemberOrderPayForWeChat(sessionID,
                "402892e96a4ed7a4016a4eda9107000c",
                "1", null, memberPaymentID);
    }

    public LiveData<Resource<PageBean<BonusBean>>> getMyMemberBonusList() {
        return apiService.getMyMemberBonusListLD(sessionID, 1);
    }

    public void bonus() {
        action.setValue("bonus");
    }

    public LiveData<Resource<PageBean<ShopCartBean>>> getOrderGoodsList() {
        return apiService.getCartGoodsListLD(sessionID, 1, 10);
    }
}
