package com.jinkan.www.fastandroid.model.repository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */
public class Listing<T> {
    @Singleton
    @Inject
    public Listing() {
    }

    @NotNull
    private LiveData<PagedList<T>> pagedList;
    @NotNull
    private LiveData<NetWorkState> networkState;
    @NotNull
    private LiveData<NetWorkState> refreshState;
    private ListingCallBack listingCallBack;

    @NotNull
    public LiveData<PagedList<T>> getPagedList() {
        return pagedList;
    }

    public void setPagedList(@NotNull LiveData<PagedList<T>> pagedList) {
        this.pagedList = pagedList;
    }

    @NotNull
    public LiveData<NetWorkState> getNetworkState() {
        return networkState;
    }

    public void setNetworkState(@NotNull LiveData<NetWorkState> networkState) {
        this.networkState = networkState;
    }

    @NotNull
    public LiveData<NetWorkState> getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(@NotNull LiveData<NetWorkState> refreshState) {
        this.refreshState = refreshState;
    }

    public ListingCallBack getListingCallBack() {
        return listingCallBack;
    }

    public void setListingCallBack(ListingCallBack listingCallBack) {
        this.listingCallBack = listingCallBack;
    }
}
