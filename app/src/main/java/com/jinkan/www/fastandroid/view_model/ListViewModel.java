package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.Listing;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

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
    public void init() {
        listing = getListing();
    }

    protected abstract Listing<T> getListing();
}
