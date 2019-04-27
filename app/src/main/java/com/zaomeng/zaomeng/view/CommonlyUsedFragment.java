package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentCommonlyUsedBinding;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.view.adapter.collect.CollectAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.CommonlyUsedFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;


public class CommonlyUsedFragment extends MVVMListFragment<CommonlyUsedFragmentVM, FragmentCommonlyUsedBinding, CollectAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;
    private CollectAdapter collectAdapter;
    @Inject
    public CommonlyUsedFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_commonly_used;
    }


    @Override
    protected void doError(NetWorkState o) {
        collectAdapter.setNetworkState(o);
    }

    @Override
    protected void setUI() {

    }

    @NonNull
    @Override
    protected CollectAdapter setAdapter(Function0 reTry) {
        return collectAdapter = new CollectAdapter(reTry);
    }

    @NonNull
    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @Override
    protected CommonlyUsedFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(CommonlyUsedFragmentVM.class);
    }


}
