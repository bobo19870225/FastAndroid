package com.zaomeng.zaomeng.view.base;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.Status;
import com.zaomeng.zaomeng.view_model.ListViewModel;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/3.
 * FastAndroid
 */
public abstract class MVVMListFragment<VM extends ListViewModel, VDB extends ViewDataBinding, A extends PagedListAdapter> extends MVVMFragment<VM, VDB> {

    private A adapter;
    private
    SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    @Override
    protected final void initUI() {
        recyclerView = setRecyclerView();
        swipeRefreshLayout = setSwipeRefreshLayout();
        setListView(transferData);
        setUI();
    }

    @SuppressWarnings("unchecked")
    protected void setListView(Object parameter) {
        Listing listing = mViewModel.getListingData(parameter);
        if (listing != null) {
            LiveData<PagedList> pagedList = listing.getPagedList();
            adapter = setAdapter(listing.reTry);
            swipeRefreshLayout.setOnRefreshListener(() -> {
                listing.refreshState.setValue(swipeRefreshLayout.isRefreshing());
                listing.refresh.invoke();
//            swipeRefreshLayout.setRefreshing(false);
            });
            recyclerView.setAdapter(adapter);
            pagedList.observe(this, ts -> adapter.submitList(ts));
            listing.networkState.observe(this, o -> {

                Status status = ((NetWorkState) o).getStatus();
                if (status == Status.RUNNING) {
                    swipeRefreshLayout.setRefreshing(true);
                } else if (status == Status.SUCCESS) {
                    swipeRefreshLayout.setRefreshing(false);
                } else if (status == Status.FAILED) {
                    swipeRefreshLayout.setRefreshing(false);
                    toast(((NetWorkState) o).getMsg());
                }
            });
        }
    }

    protected abstract void setUI();

    protected abstract @NonNull
    A setAdapter(Function0 reTry);

    protected abstract @NonNull
    SwipeRefreshLayout setSwipeRefreshLayout();

    protected abstract @NonNull
    RecyclerView setRecyclerView();


}
