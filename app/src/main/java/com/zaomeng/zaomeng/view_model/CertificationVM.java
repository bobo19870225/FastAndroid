package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.http.ApiService;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 */
public class CertificationVM extends BaseViewModel {
    private ApiService apiService;

    public CertificationVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public void choiceType() {

    }

    public void choiceArea() {

    }

    public void setShopImage() {
//        apiService.
    }

    public void setLicenseImage() {

    }

    public void setIcFront() {

    }

    public void setIcBack() {

    }

    public void submit() {

    }


}
