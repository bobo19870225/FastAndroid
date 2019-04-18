package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.utils.SingleLiveEvent;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class GoodsDetailsVM extends BaseViewModel {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public GoodsDetailsVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void init(Object data) {

    }

    public void back() {
        action.setValue("back");
    }

    public void collect() {

    }

    public void addToShopCar() {

    }
}
