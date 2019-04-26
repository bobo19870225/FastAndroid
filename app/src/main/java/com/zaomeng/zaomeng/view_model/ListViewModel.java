package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;

/**
 * Created by Sampson on 2019/3/14.
 * FastAndroid
 * {@link MVVMListFragment}
 * {@link MVVMListActivity}
 */

public abstract class ListViewModel<T> extends BaseViewModel {
//    public Listing<T> listing;

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

//    public Listing<T> getListingData(Object data) {
//        if (data == null) return null;
//        return getListing(data);
//    }

    public abstract Listing<T> getListing(Object data);
}
