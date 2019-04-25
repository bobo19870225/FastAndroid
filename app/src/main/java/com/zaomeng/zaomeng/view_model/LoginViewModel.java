package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

import static com.zaomeng.zaomeng.utils.SystemParameter.siteID;

/**
 * Created by Sampson on 2019/3/26.
 * FastAndroid
 */
public class LoginViewModel extends BaseViewModel {
    public final MutableLiveData<String> ldPhone = new MutableLiveData<>();
    public final MutableLiveData<String> ldPassword = new MutableLiveData<>();
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MutableLiveData<Boolean> ldShow = new MediatorLiveData<>();


    private ApiService apiService;
    public final MediatorLiveData<Resource<Bean<LoginBean>>> ldLogin = new MediatorLiveData<>();

    public LoginViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public void register() {
        action.setValue("register");
    }

    public void forgetPassword() {
        action.setValue("forgetPassword");
    }

    public void login() {
        if (FormatUtils.isMobileNO(ldPhone.getValue())) {
            String passwordValue = ldPassword.getValue();
            if (passwordValue != null && passwordValue.length() >= 6) {
                ldShow.setValue(true);
                ldLogin.addSource(apiService.appLogin(ldPhone.getValue(), ldPassword.getValue(), siteID, "1"), ldLogin::setValue);
            } else {
                ldShow.setValue(false);
                action.setValue("密码不少于6位");
            }
        } else {
            ldShow.setValue(false);
            action.setValue("电话号码错误");
        }

    }
}
