package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.bean.SendSmsCommonBean;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.Resource;
import com.jinkan.www.fastandroid.utils.SingleLiveEvent;

import static com.jinkan.www.fastandroid.utils.FormatUtils.isMobileNO;
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
    private Boolean vCode = false;

    public void setVcode(Boolean vCode) {
        this.vCode = vCode;
    }

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
        if (ldPassword.getValue() != null && isPhoneNumber(ldPassword.getValue())) {
            action.setValue("getVCode");
            ldSendSmsCommonBean.addSource(apiService.sendSmsCommon(ldPhone.getValue(), siteID, 0), ldSendSmsCommonBean::setValue);

        } else {
            action.setValue("phoneError");
        }
    }

    private boolean isPhoneNumber(String value) {
        return isMobileNO(value);
    }

    public void next() {
        if (ldPhone.getValue() == null) {
            action.setValue("phoneError");
            return;
        }
        if (!isPhoneNumber(ldPhone.getValue())) {
            action.setValue("phoneError");
            return;
        }
        if (ldVCode.getValue() == null) {
            action.setValue("inputVCode");
            return;
        }
        if (!vCode) {
            action.setValue("vCodeError");
            return;
        }
        if (ldPassword.getValue() == null) {
            action.setValue("inputPassword");
            return;
        }
        if (ldPassword.getValue().length() < 6) {
            action.setValue("PasswordError");
        }
        action.setValue("next");

    }
}
