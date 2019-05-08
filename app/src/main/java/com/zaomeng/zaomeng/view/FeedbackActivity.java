package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityFeedbackBinding;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.FeedbackVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class FeedbackActivity extends MVVMActivity<FeedbackVM, ActivityFeedbackBinding> {
    @Inject
    ViewModelFactory viewModelFactory;

    @NonNull
    @Override
    protected FeedbackVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(FeedbackVM.class);
    }

    @Override
    protected void setView() {
        mViewDataBinding.contant.setHorizontallyScrolling(false);
        mViewDataBinding.contant.setSingleLine(false);
        mViewModel.ldContent.observe(this, s -> {
            int length = s.length();
            String s1 = length + "/" + "200";
            mViewModel.ldTextNumber.setValue(s1);
        });
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "意见反馈";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_feedback;
    }
}
