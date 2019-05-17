package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.http.ApiService;

/**
 * Created by Sampson on 2019-05-17.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.OrderDetailActivity}
 */
public class OrderDetailVM extends BaseViewModel {
    private ApiService apiService;

    public OrderDetailVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }
}
