package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

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
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MediatorLiveData<Resource<Bean<String>>> ldSubmit = new MediatorLiveData<>();
    public String title;

    public FeedbackVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {
        ldTextNumber.setValue("0/200");
    }

    public void submit() {
        String contentValue = ldContent.getValue();
        String contactsValue = ldContacts.getValue();
        if (contentValue == null) {
            action.setValue("toast:请填写您的建议");
            return;
        }
        if (contactsValue == null) {
            action.setValue("toast:请填写您的联系方式");
            return;
        }
        ldSubmit.addSource(apiService.submitOneFee(SharedPreferencesUtils.getSessionID(getApplication()),
                contentValue, contactsValue, title), ldSubmit::setValue);
    }
}
