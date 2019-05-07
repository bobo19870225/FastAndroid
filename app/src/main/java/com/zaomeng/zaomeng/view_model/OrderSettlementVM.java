package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;

/**
 * Created by Sampson on 2019-05-02.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.OrderSettlementActivity}
 */
public class OrderSettlementVM extends BaseViewModel {

    private ApiService apiService;
    private String sessionID;

    public OrderSettlementVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreerencesUtils.getSessionID(getApplication());
    }

    public LiveData<Resource<PageBean<MemberShopBean>>> getAddress() {
        return apiService.getMemberShopListLD(sessionID);
    }
}
