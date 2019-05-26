package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SendSmsCommonBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

import static com.zaomeng.zaomeng.utils.FormatUtils.isMobileNO;
import static com.zaomeng.zaomeng.utils.SystemParameter.siteID;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.RegisterActivity}
 */
public class RegisterViewModel extends BaseViewModel {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MutableLiveData<String> ldVCode = new MutableLiveData<>();
    public final MutableLiveData<String> ldGetVCode = new MutableLiveData<>();
    public final MutableLiveData<String> ldPhone = new MutableLiveData<>();
    public final MutableLiveData<String> ldPassword = new MutableLiveData<>();
    public final MediatorLiveData<Resource<Bean<LoginBean>>> ldRegister = new MediatorLiveData<>();
    public final MediatorLiveData<Resource<SendSmsCommonBean>> ldSendSmsCommonBean = new MediatorLiveData<>();
    private String sVCode;
    private String oldPhone;


    private ApiService apiService;


    RegisterViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {
        ldGetVCode.setValue("获取验证码");
    }

    public void getVCode() {
        if (ldPhone.getValue() != null && isPhoneNumber(ldPhone.getValue())) {
            action.setValue("getVCode");
            ldSendSmsCommonBean.addSource(apiService.sendSmsCommon(ldPhone.getValue(), siteID, 0), ldSendSmsCommonBean::setValue);
            oldPhone = ldPhone.getValue();
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
        if (!sVCode.equals(ldVCode.getValue())) {
            action.setValue("vCodeError");
            return;
        }
        //防止发送验证码后串改号码
        if (!oldPhone.equals(ldPhone.getValue())) {
            action.setValue("phoneError");
            return;
        }

        if (ldPassword.getValue() == null) {
            action.setValue("inputPassword");
            return;
        }
        if (ldPassword.getValue().length() < 6) {
            action.setValue("PasswordError");
            return;
        }

        ldRegister.addSource(apiService.phoneRegister(ldPhone.getValue(), ldPassword.getValue(), ldVCode.getValue(), siteID, ""), ldRegister::setValue);

    }

    public void isVCodeCorrect(String vCode) {
        sVCode = vCode;
    }
}
