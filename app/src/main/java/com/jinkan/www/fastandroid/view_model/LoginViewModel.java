package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.bean.Bean;
import com.jinkan.www.fastandroid.model.repository.http.bean.LoginBean;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.Resource;
import com.jinkan.www.fastandroid.utils.SingleLiveEvent;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import static com.jinkan.www.fastandroid.utils.SystemParameter.siteID;

/**
 * Created by Sampson on 2019/3/26.
 * FastAndroid
 */
public class LoginViewModel extends BaseViewModel {
    public final MutableLiveData<String> ldPhone = new MutableLiveData<>();
    public final MutableLiveData<String> ldPassword = new MutableLiveData<>();
    public final SingleLiveEvent<Void> actionRegister = new SingleLiveEvent<>();
    public final SingleLiveEvent<Void> actionForgetPassword = new SingleLiveEvent<>();

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
        actionRegister.call();
    }

    public void forgetPassword() {
        actionForgetPassword.call();
    }
    public void login(String email, String password) {
        ldLogin.addSource(apiService.appLogin(email, password, siteID, "1"), ldLogin::setValue);
    }
}
