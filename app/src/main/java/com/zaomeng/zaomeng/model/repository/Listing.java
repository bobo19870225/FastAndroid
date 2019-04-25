package com.zaomeng.zaomeng.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import javax.inject.Inject;
import javax.inject.Singleton;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */
@Singleton
public class Listing<T> {


    @Inject
    public Listing() {
    }

    private LiveData<PagedList<T>> pagedList;

    public final MutableLiveData<NetWorkState> networkState = new MutableLiveData<>();

    public final MutableLiveData<NetWorkState> refreshState = new MutableLiveData<>();


    public LiveData<PagedList<T>> getPagedList() {
        return pagedList;
    }

    public void setPagedList(LiveData<PagedList<T>> pagedList) {
        this.pagedList = pagedList;
    }

    public Function0 refresh;
    public Function0 reTry;
}
