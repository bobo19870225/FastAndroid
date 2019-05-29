package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SignInBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

import java.util.List;

/**
 * Created by Sampson on 2019-05-05.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.CalendarActivity}
 */
public class CalendarVM extends BaseViewModel {
    private ApiService apiService;
    private String sessionID;
    private UserDao userDao;
    public final MediatorLiveData<Resource<Bean<String>>> ldSignIn = new MediatorLiveData<>();
    public final MutableLiveData<String> ldUserName = new MutableLiveData<>();
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();

    public CalendarVM(@NonNull Application application, ApiService apiService, UserDao userDao) {
        super(application);
        this.apiService = apiService;
        this.userDao = userDao;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());
    }

    public LiveData<List<LoginBean>> getUser() {
        String[] loginInfo = SharedPreferencesUtils.getLoginInfo(getApplication());
        if (loginInfo != null && loginInfo[0] != null && loginInfo[1] != null) {
            return userDao.getUserByPhone(loginInfo[0]);
        } else {
            return null;
        }

    }
    public void signIn() {
        action.setValue("signIn");
        ldSignIn.addSource(apiService.submitOneSignIn(sessionID), ldSignIn::setValue);
    }

    public LiveData<Resource<PageBean<SignInBean>>> getSignInList() {
        return apiService.getSignInList(sessionID, 1, 100);
    }
}
