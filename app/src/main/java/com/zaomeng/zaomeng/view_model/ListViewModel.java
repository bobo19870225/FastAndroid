package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;

/**
 * Created by Sampson on 2019/3/14.
 * FastAndroid
 */

public abstract class ListViewModel<T> extends BaseViewModel {
    public Listing<T> listing;

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    @CallSuper
    public void init(Object data) {
        listing = getListing(data);
    }

    protected abstract Listing<T> getListing(Object data);
}
