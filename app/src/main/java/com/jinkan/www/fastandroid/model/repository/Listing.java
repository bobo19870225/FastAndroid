package com.jinkan.www.fastandroid.model.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */
public class Listing<T> {


    private LiveData<PagedList<T>> pagedList;

    private LiveData<NetWorkState> networkState;

    private LiveData<NetWorkState> refreshState;

    private ListingCallBack listingCallBack;


    public LiveData<PagedList<T>> getPagedList() {
        return pagedList;
    }

    public void setPagedList(LiveData<PagedList<T>> pagedList) {
        this.pagedList = pagedList;
    }


    public LiveData<NetWorkState> getNetworkState() {
        return networkState;
    }

    public void setNetworkState(LiveData<NetWorkState> networkState) {
        this.networkState = networkState;
    }


    public LiveData<NetWorkState> getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(LiveData<NetWorkState> refreshState) {
        this.refreshState = refreshState;
    }

    public ListingCallBack getListingCallBack() {
        return listingCallBack;
    }

    public void setListingCallBack(ListingCallBack listingCallBack) {
        this.listingCallBack = listingCallBack;
    }
}
