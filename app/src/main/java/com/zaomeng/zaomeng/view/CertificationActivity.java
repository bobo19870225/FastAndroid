package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityCertificationBinding;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.CertificationVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 */
public class CertificationActivity extends MVVMActivity<CertificationVM, ActivityCertificationBinding> {
    @Inject
    ViewModelFactory viewModelFactory;

    @NonNull
    @Override
    protected CertificationVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(CertificationVM.class);
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
        return R.layout.activity_certification;
    }
}
