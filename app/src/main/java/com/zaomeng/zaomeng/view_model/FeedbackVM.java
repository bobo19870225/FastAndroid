package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.FeedbackActivity}
 */
public class FeedbackVM extends BaseViewModel {
    private ApiService apiService;
    public final MutableLiveData<String> ldContent = new MutableLiveData<>();
    public final MutableLiveData<String> ldContacts = new MutableLiveData<>();
    public final MutableLiveData<String> ldTextNumber = new MutableLiveData<>();


    public FeedbackVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {
        ldTextNumber.setValue("0/200");
    }

    public void submit() {

    }
}
