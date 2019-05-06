package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019/4/16.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.MeFragment}
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

    public void address() {
        action.setValue("address");
    }

    public void calendar() {
        action.setValue("calendar");
    }
}
