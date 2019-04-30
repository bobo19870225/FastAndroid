package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyRepository;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.InterfacePageRepository;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;

/**
 * Created by Sampson on 2019/3/14.
 * FastAndroid
 * {@link MVVMListFragment}
 * {@link MVVMListActivity}
 */

public abstract class ListViewModel<Key, Value> extends BaseViewModel implements InterfacePageRepository<Key, Value> {

    public ListViewModel(@NonNull Application application) {
        super(application);
    }

    public Object listRequest;

    public Listing<Value> getListing(Object listRequest) {
        this.listRequest = listRequest;
        return new BasePageKeyRepository<>(this).post(setPageSize());
    }

    @NonNull
    protected abstract Integer setPageSize();
}
