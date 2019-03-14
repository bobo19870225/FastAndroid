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

/**
 * Created by Sampson on 2019/3/14.
 * FastAndroid
 */
public abstract class MVVMListActivity<VM extends ListViewModel, VDB extends ViewDataBinding, A extends PagedListAdapter>
        extends MVVMActivity<VM, VDB> {
    private RecyclerView recyclerView;
    private A adapter;

    @Override
    @CallSuper
    @SuppressWarnings("unchecked")
    protected void setView() {
        recyclerView = setRecyclerView();
        adapter = setAdapter();
        recyclerView.setAdapter(adapter);
        LiveData<PagedList> pagedList = mViewModel.listing.getPagedList();
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
    protected abstract A setAdapter();

    @NonNull
    protected abstract RecyclerView setRecyclerView();


}
