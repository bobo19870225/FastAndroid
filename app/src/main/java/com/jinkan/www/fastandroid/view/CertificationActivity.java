package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.ActivityCertificationBinding;
import com.jinkan.www.fastandroid.view.base.MVVMActivity;
import com.jinkan.www.fastandroid.view_model.CertificationVM;
import com.jinkan.www.fastandroid.view_model.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

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

//    @Override
//    protected int setToolBarMenu() {
//        return 0;
//    }
//
//    @Override
//    protected String setToolBarTitle() {
//        return null;
//    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_certification;
    }
}
