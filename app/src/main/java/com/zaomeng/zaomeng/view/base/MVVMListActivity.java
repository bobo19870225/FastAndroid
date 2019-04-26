package com.zaomeng.zaomeng.view.base;

import androidx.annotation.CallSuper;
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
 * Created by Sampson on 2019/3/14.
 * FastAndroid
 */
public abstract class MVVMListActivity<VM extends ListViewModel, VDB extends ViewDataBinding, A extends PagedListAdapter>
        extends MVVMActivity<VM, VDB> {
    private RecyclerView recyclerView;
    private A adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    @CallSuper

    protected void setView() {
        recyclerView = setRecyclerView();
        swipeRefreshLayout = setSwipeRefreshLayout();
        setListView(transferData);
    }

    @SuppressWarnings("unchecked")
    protected void setListView(Object parameter) {
        Listing listing = mViewModel.getListing(parameter);
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


    @NonNull
    protected abstract RecyclerView setRecyclerView();

    @NonNull
    protected abstract A setAdapter(Function0 reTry);

    protected abstract SwipeRefreshLayout setSwipeRefreshLayout();


}
