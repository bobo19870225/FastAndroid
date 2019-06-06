package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.utils.MyDataCleanManager;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019-05-11.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.SettingActivity}
 */
public class SettingVM extends BaseViewModel {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MutableLiveData<String> ldCache = new MutableLiveData<>();
    public final MutableLiveData<String> ldVersion = new MutableLiveData<>();

    public SettingVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void init(Object data) {

    }

    public void exit() {
        action.setValue("exit");
        SharedPreferencesUtils.cleanLoginInfo(getApplication());
        SharedPreferencesUtils.cleanSessionID(getApplication());
        SharedPreferencesUtils.cleanMemberID(getApplication());
    }

    public void us() {
        action.setValue("us");
    }

    public void cleanCache() {
        try {
            MyDataCleanManager.clearAllCache(getApplication());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ldCache.setValue("0KB");
    }

}
