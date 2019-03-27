package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.dataBase.User;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.Message;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.Resource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;

/**
 * Created by Sampson on 2019/3/26.
 * FastAndroid
 */
public class LoginViewModel extends BaseViewModel {
    private ApiService apiService;
    public final MediatorLiveData<Resource<Message<User>>> ldLogin = new MediatorLiveData<>();

    public LoginViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public void login(String email, String password) {
        ldLogin.addSource(apiService.login(email, password), ldLogin::setValue);
    }
}
