package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentCommonlyUsedBinding;
import com.zaomeng.zaomeng.view.adapter.GoodsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.CommonlyUsedFragmentVM;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;


public class CommonlyUsedFragment extends MVVMListFragment<CommonlyUsedFragmentVM, FragmentCommonlyUsedBinding, GoodsAdapter> {
    @Inject
    public CommonlyUsedFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_commonly_used;
    }


    @Override
    protected void setUI() {

    }

    @NonNull
    @Override
    protected GoodsAdapter setAdapter(Function0 reTry) {
        return new GoodsAdapter(reTry);
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
        return ViewModelProviders.of(this).get(CommonlyUsedFragmentVM.class);
    }


}
