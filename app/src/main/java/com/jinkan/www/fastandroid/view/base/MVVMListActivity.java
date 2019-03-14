package com.jinkan.www.fastandroid.view.base;

import com.jinkan.www.fastandroid.model.repository.NetWorkState;
import com.jinkan.www.fastandroid.model.repository.Status;
import com.jinkan.www.fastandroid.view_model.ListViewModel;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * Created by Sampson on 2019/3/14.
 * FastAndroid
 */
public abstract class MVVMListActivity<VM extends ListViewModel, VDB extends ViewDataBinding, A extends PagedListAdapter>
        extends MVVMActivity<VM, VDB> {
    private RecyclerView recyclerView;
    private A adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LiveData<PagedList> pagedList;
    @Override
    @CallSuper
    @SuppressWarnings("unchecked")
    protected void setView() {
        recyclerView = setRecyclerView();
        adapter = setAdapter();
        swipeRefreshLayout = setSwipeRefreshLayout();
        pagedList = mViewModel.listing.getPagedList();
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mViewModel.listing.refreshState.setValue(swipeRefreshLayout.isRefreshing());
            mViewModel.listing.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });

        recyclerView.setAdapter(adapter);
        pagedList.observe(this, ts -> adapter.submitList(ts));
        mViewModel.listing.networkState.observe(this, o -> {
            Status status = ((NetWorkState) o).getStatus();
            if (status == Status.RUNNING) {

            } else if (status == Status.SUCCESS) {

            } else if (status == Status.FAILED) {

            }
        });
    }


    @NonNull
    protected abstract RecyclerView setRecyclerView();

    @NonNull
    protected abstract A setAdapter();

    protected abstract SwipeRefreshLayout setSwipeRefreshLayout();


}
