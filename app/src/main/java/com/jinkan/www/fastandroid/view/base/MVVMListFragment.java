package com.jinkan.www.fastandroid.view.base;

import com.jinkan.www.fastandroid.model.repository.NetWorkState;
import com.jinkan.www.fastandroid.model.repository.Status;
import com.jinkan.www.fastandroid.view_model.ListViewModel;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/3.
 * FastAndroid
 */
public abstract class MVVMListFragment<VM extends ListViewModel, VDB extends ViewDataBinding, A extends PagedListAdapter> extends MVVMFragment<VM, VDB> {

    private
    RecyclerView recyclerView;
    private A adapter;
    private
    SwipeRefreshLayout swipeRefreshLayout;
    private LiveData<PagedList> pagedList;

    @SuppressWarnings("unchecked")
    @Override
    protected final void initUI() {
        recyclerView = setRecyclerView();
        swipeRefreshLayout = setSwipeRefreshLayout();
        pagedList = mViewModel.listing.getPagedList();
        adapter = setAdapter(mViewModel.listing.reTry);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            mViewModel.listing.refreshState.setValue(swipeRefreshLayout.isRefreshing());
            mViewModel.listing.refresh.invoke();
//            swipeRefreshLayout.setRefreshing(false);
        });

        recyclerView.setAdapter(adapter);
        pagedList.observe(this, ts -> adapter.submitList(ts));
        mViewModel.listing.networkState.observe(this, o -> {

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
        setUI();
    }

    protected abstract void setUI();

    protected abstract @NonNull
    A setAdapter(Function0 reTry);

    protected abstract @NonNull
    SwipeRefreshLayout setSwipeRefreshLayout();

    protected abstract @NonNull
    RecyclerView setRecyclerView();


}