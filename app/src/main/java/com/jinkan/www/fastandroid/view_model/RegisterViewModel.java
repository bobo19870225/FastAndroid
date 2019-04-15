package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.utils.SingleLiveEvent;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 */
public class RegisterViewModel extends BaseViewModel {
    public final SingleLiveEvent<Void> actionNext = new SingleLiveEvent<>();
    private ApiService apiService;

    public RegisterViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public void next() {
        actionNext.call();
    }
}
