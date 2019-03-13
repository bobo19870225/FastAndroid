package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.ActivityMainBinding;
import com.jinkan.www.fastandroid.view.base.MVVMActivity;
import com.jinkan.www.fastandroid.view_model.MainViewModel;
import com.jinkan.www.fastandroid.view_model.ViewModelFactory;

import javax.inject.Inject;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends MVVMActivity<MainViewModel, ActivityMainBinding> {
    //    @Inject
//    ApiService apiService;
//    @Inject
//    ByPageKeyRepository byPageKeyRepository;
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
        setToolBar("测试");
        final MovieAdapter movieAdapter = new MovieAdapter();
        mViewDataBinding.list.setAdapter(movieAdapter);
        mViewDataBinding.getModel().movieListing.getPagedList().observe(this, subjects -> movieAdapter.submitList(subjects));
        mViewModel.singleLiveEvent.observe(this, new Observer<Void>() {
            @Override
            public void onChanged(Void aVoid) {
                finish();
            }
        });

    }
}
