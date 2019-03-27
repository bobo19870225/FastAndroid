package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.ActivityMainBinding;
import com.jinkan.www.fastandroid.view.base.MVVMListActivity;
import com.jinkan.www.fastandroid.view_model.MainViewModel;
import com.jinkan.www.fastandroid.view_model.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import kotlin.jvm.functions.Function0;

public class MainActivity extends MVVMListActivity<MainViewModel, ActivityMainBinding, MovieAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;


    @Override
    protected int setLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        //直接new不会调用onCleared()!
//        return new MainViewModel(getApplication(), byPageKeyRepository);
    }

    @Override
    protected void setView() {
        super.setView();
        setToolBar("测试");
        mViewModel.singleLiveEvent.observe(this, aVoid -> mViewModel.listing.reTry.invoke());

    }

    @NonNull
    @Override
    protected MovieAdapter setAdapter(Function0 reTry) {
        return new MovieAdapter(reTry);
    }

    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }
}
