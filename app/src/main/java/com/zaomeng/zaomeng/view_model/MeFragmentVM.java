package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberStatisticsInfo;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019/4/16.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.MeFragment}
 */
public class MeFragmentVM extends BaseViewModel {

    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MutableLiveData<String> ldPoint = new MutableLiveData<>();
    public final MutableLiveData<String> ldCoupon = new MutableLiveData<>();
    private ApiService apiService;

    public MeFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public void allOrder() {
        action.setValue("allOrder");
    }

    public void payment() {
        action.setValue("payment");
    }

    public void receivingGoods() {
        action.setValue("receivingGoods");
    }

    public void cancel() {
        action.setValue("cancel");
    }

    public void address() {
        action.setValue("address");
    }

    public void calendar() {
        action.setValue("calendar");
    }

    public void point() {
        action.setValue("point");
    }

    public void bonus() {
        action.setValue("bonus");
    }

    public void feedback() {
        action.setValue("feedback");
    }

    public void setting() {
        action.setValue("setting");
    }

    public void userInfo() {
        action.setValue("userInfo");
    }

    public void customerService() {
        action.setValue("customerService");
    }

    public void message() {
        action.setValue("message");
    }


    public LiveData<Resource<Bean<MemberStatisticsInfo>>> getMemberStatisticsInfo() {
        return apiService.getMemberStatisticsInfo(SharedPreferencesUtils.getSessionID(getApplication()));
    }

    public LiveData<Resource<Bean<Integer>>> getNoReadMessageNum() {
        return apiService.getNoReadMessageNum(SharedPreferencesUtils.getSessionID(getApplication()));
    }
}
