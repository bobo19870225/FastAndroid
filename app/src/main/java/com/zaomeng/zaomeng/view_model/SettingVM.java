package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019-05-11.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.SettingActivity}
 */
public class SettingVM extends BaseViewModel {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();

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
}
