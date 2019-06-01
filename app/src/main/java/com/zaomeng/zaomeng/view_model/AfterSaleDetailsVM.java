package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsReturnDetailBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;

/**
 * Created by Sampson on 2019-05-31.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.AfterSaleDetailsActivity}
 */
public class AfterSaleDetailsVM extends BaseViewModel {
    private ApiService apiService;
    public final MutableLiveData<String> ldTime = new MutableLiveData<>();
    public final MutableLiveData<String> ldNo = new MutableLiveData<>();
    public final MutableLiveData<String> ldState = new MutableLiveData<>();
    public final MutableLiveData<String> ldReasons = new MutableLiveData<>();

    private String sessionID;

    public AfterSaleDetailsVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());
    }

    public LiveData<Resource<Bean<GoodsReturnDetailBean>>> getMemberOrderGoodsReturnDetail(String memberOrderGoodsReturnID) {
        return apiService.getMemberOrderGoodsReturnDetail(sessionID, memberOrderGoodsReturnID);
    }
}
