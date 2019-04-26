package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class CommonlyUsedFragmentVM extends ListViewModel {
    public CommonlyUsedFragmentVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void init(Object data) {

    }

    @Override
    public Listing getListing(Object data) {
        return null;
    }
}
