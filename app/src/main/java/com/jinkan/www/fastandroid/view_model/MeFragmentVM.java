package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.utils.SingleLiveEvent;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/4/16.
 * FastAndroid
 */
public class MeFragmentVM extends BaseViewModel {
    public SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public MeFragmentVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void init(Object data) {

    }

    public void allOrder() {
        action.setValue("allOrder");
    }

    public void payment() {
        action.setValue("payment");
    }

    public void receivingGoods() {
        action.setValue("receivingGoods");
    }

    public void cancel() {
        action.setValue("cancel");
    }
}
