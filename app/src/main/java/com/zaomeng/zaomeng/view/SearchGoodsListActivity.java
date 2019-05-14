package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityGoodsListBinding;
import com.zaomeng.zaomeng.view.adapter.goods.GoodsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.SearchGoodsListVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-14.
 * FastAndroid
 */
public class SearchGoodsListActivity extends MVVMListActivity<SearchGoodsListVM, ActivityGoodsListBinding, GoodsAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void setView() {
        super.setView();
        mViewModel.action.observe(this, s -> {
            if (s.equals("cancel")) {
                finish();
            }
        });
    }

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @NonNull
    @Override
    protected GoodsAdapter setAdapter(Function0 reTry) {
        return new GoodsAdapter(reTry);
    }

    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected SearchGoodsListVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(SearchGoodsListVM.class);
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_goods_list;
    }
}
