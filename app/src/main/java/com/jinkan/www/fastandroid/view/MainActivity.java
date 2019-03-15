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

public class MainActivity extends MVVMListActivity<MainViewModel, ActivityMainBinding, MovieAdapter> {
    //    @Inject
//    ApiService apiService;
//    @Inject
//    ByPageKeyRepository byPageKeyRepository;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    MovieAdapter movieAdapter;


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
        mViewModel.singleLiveEvent.observe(this, aVoid -> mViewModel.listing.reTry());

    }

    @NonNull
    @Override
    protected MovieAdapter setAdapter() {
        return movieAdapter;
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
