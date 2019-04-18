package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.utils.SingleLiveEvent;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class MainFragmentVM extends BaseViewModel {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();

    public MainFragmentVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void init(Object data) {

    }

    public void msg() {
        action.setValue("msg");
    }
}
