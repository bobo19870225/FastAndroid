package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SignInBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;

/**
 * Created by Sampson on 2019-05-05.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.CalendarActivity}
 */
public class CalendarVM extends BaseViewModel {
    private ApiService apiService;
    private String sessionID;
    public final MediatorLiveData<Resource<Bean<String>>> ldSignIn = new MediatorLiveData<>();
    public final MutableLiveData<String> action = new MutableLiveData<>();

    public CalendarVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreerencesUtils.getSessionID(getApplication());
    }

    public void signIn() {
        action.setValue("signIn");
        ldSignIn.addSource(apiService.submitOneSignIn(sessionID), ldSignIn::setValue);
    }

    public LiveData<Resource<PageBean<SignInBean>>> getSignInList() {
        return apiService.getSignInList(sessionID, 1, 100);
    }
}
