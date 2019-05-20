package com.zaomeng.zaomeng.view.base;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.Status;
import com.zaomeng.zaomeng.view.adapter.BasePagedListAdapter;
import com.zaomeng.zaomeng.view_model.ListViewModel;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/3/14.
 * FastAndroid
 */
public abstract class MVVMListActivity<VM extends ListViewModel, VDB extends ViewDataBinding, A extends BasePagedListAdapter>
        extends MVVMActivity<VM, VDB> {
    private RecyclerView recyclerView;
    private A adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Listing listing;
    @Override
    @CallSuper
    protected void setView() {
        recyclerView = setRecyclerView();
        swipeRefreshLayout = setSwipeRefreshLayout();
        setListView(transferData);
    }

    @SuppressWarnings("unchecked")
    protected void setListView(Object transferData) {
        listing = mViewModel.getListing(transferData);
        if (listing != null) {
            LiveData<PagedList> pagedList = listing.getPagedList();
            adapter = setAdapter(listing.reTry);
            swipeRefreshLayout.setOnRefreshListener(() -> {
                listing.refreshState.setValue(swipeRefreshLayout.isRefreshing());
                listing.refresh.invoke();
//            swipeRefreshLayout.setRefreshing(false);
            });
//            RecyclerViewNoBugLinearLayoutManager recyclerViewNoBugLinearLayoutManager = new RecyclerViewNoBugLinearLayoutManager(getApplicationContext());
//            recyclerViewNoBugLinearLayoutManager.setOrientation(RecyclerView.VERTICAL);
//            recyclerView.setLayoutManager(recyclerViewNoBugLinearLayoutManager);
            recyclerView.setAdapter(adapter);
            pagedList.observe(this, ts -> {
//                if (emptyView != null) {
//                    if (ts.size() == 0) {
//                        emptyView.setVisibility(View.VISIBLE);
//                    } else {
//                        emptyView.setVisibility(View.GONE);
//                    }
//                }
                adapter.submitList(ts);
            });
            listing.networkState.observe(this, o -> {

                Status status = ((NetWorkState) o).getStatus();
                if (status == Status.RUNNING) {
                    swipeRefreshLayout.setRefreshing(true);
                } else if (status == Status.SUCCESS) {
                    swipeRefreshLayout.setRefreshing(false);
                    adapter.setNetworkState((NetWorkState) o);
//                    doError((NetWorkState) o);
                } else if (status == Status.FAILED) {
                    swipeRefreshLayout.setRefreshing(false);
                    toast(((NetWorkState) o).getMsg());
                    adapter.setNetworkState((NetWorkState) o);
//                    doError((NetWorkState) o);
                }
            });

        }
    }

    protected void refresh() {
        if (listing != null) {
            listing.refresh.invoke();
        }
    }
    @NonNull
    protected abstract RecyclerView setRecyclerView();

    @NonNull
    protected abstract A setAdapter(Function0 reTry);

    protected abstract SwipeRefreshLayout setSwipeRefreshLayout();


}
