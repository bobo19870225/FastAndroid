package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentBonusBinding;
import com.zaomeng.zaomeng.view.adapter.bonus.BonusAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.BonusFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 */
public class BonusFragment extends MVVMListFragment<BonusFragmentVM, FragmentBonusBinding, BonusAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    public BonusFragment() {
    }

    @Override
    protected void setUI() {

    }

    @NonNull
    @Override
    protected BonusAdapter setAdapter(Function0 reTry) {
        return new BonusAdapter(reTry);
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
    protected BonusFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(BonusFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_bonus;
    }
}
