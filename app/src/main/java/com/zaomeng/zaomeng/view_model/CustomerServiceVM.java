package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019-05-17.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.CustomerServiceActivity}
 */
public class CustomerServiceVM extends BaseViewModel {
    private ApiService apiService;
    public final MutableLiveData<String> ldPhone = new MutableLiveData<>();
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();

    public CustomerServiceVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public LiveData<Resource<Bean<String>>> getParameterValueByCode(String customerPhone) {
        return apiService.getParameterValueByCode(customerPhone);
    }

    public void pointRule() {
        action.setValue("pointRule");
    }

    public void FAQ() {
        action.setValue("FAQ");
    }

    public void payQuestion() {
        action.setValue("payQuestion");
    }

    public void deliveryQuestion() {
        action.setValue("deliveryQuestion");
    }

    public void returnGoodsQuestion() {
        action.setValue("returnGoodsQuestion");
    }
}
