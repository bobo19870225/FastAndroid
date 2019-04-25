package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class MainFragmentVM extends BaseViewModel {
    private ApiService apiService;
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();


    public MainFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public LiveData<Resource<PageBean<NavigatorBean>>> getNodeNavigatorList() {
        return apiService.getNodeNavigatorList("076333d6bd284605ab2293fb698db804", 2, "422429993732");
    }
    public void msg() {
        action.setValue("msg");
    }

    public void location() {
        action.setValue("location");
    }

    public void search() {
        action.setValue("search");
    }

}
