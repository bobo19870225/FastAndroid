package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class CommonlyUsedFragmentVM extends ListViewModel<GoodsSuperBean> {
    private ApiService apiService;

    public CommonlyUsedFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }


    @Override
    protected Listing<GoodsSuperBean> getListing(Object data) {
        return null;
    }
}
