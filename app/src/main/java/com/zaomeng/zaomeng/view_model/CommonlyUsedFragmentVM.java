package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class CommonlyUsedFragmentVM extends ListViewModel {
    private ApiService apiService;

    public CommonlyUsedFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    @Override
    public Listing getListing(Object data) {
        return (Listing) apiService.getCollectList((String) data, "422429993732", 1, 10);
    }
}
