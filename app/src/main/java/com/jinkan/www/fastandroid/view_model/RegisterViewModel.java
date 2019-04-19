package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.bean.SendSmsCommonBean;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.Resource;
import com.jinkan.www.fastandroid.utils.SingleLiveEvent;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import static com.jinkan.www.fastandroid.utils.SystemParameter.siteID;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 */
public class RegisterViewModel extends BaseViewModel {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MutableLiveData<String> ldVCode = new MutableLiveData<>();
    public final MutableLiveData<String> ldGetVCode = new MutableLiveData<>();
    public final MutableLiveData<String> ldPhone = new MutableLiveData<>();
    public final MutableLiveData<String> ldPassword = new MutableLiveData<>();
    public final MediatorLiveData<Resource<SendSmsCommonBean>> ldSendSmsCommonBean = new MediatorLiveData<>();


    private ApiService apiService;


    public RegisterViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {
        ldGetVCode.setValue("获取验证码");
    }

    public void getVCode() {
        action.setValue("getVCode");
        ldSendSmsCommonBean.addSource(apiService.sendSmsCommon(ldPhone.getValue(), siteID, 0), ldSendSmsCommonBean::setValue);
    }
    public void next() {
        action.setValue("next");
    }
}
