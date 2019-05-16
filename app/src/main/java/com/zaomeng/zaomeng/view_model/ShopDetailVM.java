package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019-05-16.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.ShopDetailActivity}
 */
public class ShopDetailVM extends BaseViewModel {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MutableLiveData<String> ldName = new MediatorLiveData<>();
    public final MutableLiveData<String> ldAddress = new MediatorLiveData<>();
    public final MutableLiveData<String> ldContact = new MediatorLiveData<>();
    public final MutableLiveData<String> ldContactPhone = new MediatorLiveData<>();
    public final MutableLiveData<String> ldShopType = new MediatorLiveData<>();
    private ApiService apiService;

    public ShopDetailVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public LiveData<Resource<Bean<MemberShopBean>>> getMemberShopDetail(String memberShopID) {
        return apiService.getMemberShopDetail(SharedPreferencesUtils.getSessionID(getApplication()), memberShopID);
    }

    public void ok() {
        action.call();
    }
}
