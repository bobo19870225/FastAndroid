package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentListBinding;
import com.zaomeng.zaomeng.view.adapter.goods.GoodsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.PointFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 */
public class PointFragment extends MVVMListFragment<PointFragmentVM, FragmentListBinding, GoodsAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    public PointFragment() {
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
    protected PointFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(PointFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_list;
    }
}
