package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.http.ApiService;

import androidx.annotation.NonNull;

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
}
