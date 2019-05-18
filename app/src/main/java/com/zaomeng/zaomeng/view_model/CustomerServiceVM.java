package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;

/**
 * Created by Sampson on 2019-05-17.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.CustomerServiceActivity}
 */
public class CustomerServiceVM extends BaseViewModel {
    private ApiService apiService;

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
}
