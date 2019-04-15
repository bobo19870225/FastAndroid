package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.ActivityRegisterBinding;
import com.jinkan.www.fastandroid.view.base.MVVMActivity;
import com.jinkan.www.fastandroid.view_model.RegisterViewModel;
import com.jinkan.www.fastandroid.view_model.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 */
public class RegisterActivity extends MVVMActivity<RegisterViewModel, ActivityRegisterBinding> {
    @Inject
    ViewModelFactory viewModelFactory;

    @NonNull
    @Override
    protected RegisterViewModel createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel.class);
    }

    @Override
    protected void setView() {
        mViewModel.actionNext.observe(this, aVoid -> skipTo(CertificationActivity.class, null));
    }

//    @Override
//    protected int setToolBarMenu() {
//        return 0;
//    }
//
//    @Override
//    protected String setToolBarTitle() {
//        return "注册";
//    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_register;
    }
}
