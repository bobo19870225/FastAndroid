package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivitySearchBinding;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.SearchViewModel;

/**
 * Created by Sampson on 2019-04-25.
 * FastAndroid
 */
public class SearchActivity extends MVVMActivity<SearchViewModel, ActivitySearchBinding> {
    @NonNull
    @Override
    protected SearchViewModel createdViewModel() {
        return ViewModelProviders.of(this).get(SearchViewModel.class);
    }

    @Override
    protected void setView() {

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
        return R.layout.activity_search;
    }
}
