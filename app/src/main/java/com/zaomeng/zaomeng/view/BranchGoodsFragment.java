package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentBranchGoodsBinding;
import com.zaomeng.zaomeng.view.adapter.branch_goods.BranchGoodsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.BranchGoodsFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;


public class BranchGoodsFragment extends MVVMListFragment<BranchGoodsFragmentVM, FragmentBranchGoodsBinding, BranchGoodsAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    public BranchGoodsFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_branch_goods;
    }


    @Override
    protected void setUI() {

    }

    @NonNull
    @Override
    protected BranchGoodsAdapter setAdapter(Function0 reTry) {
        return new BranchGoodsAdapter(reTry);
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
    protected BranchGoodsFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(BranchGoodsFragmentVM.class);
    }


}
