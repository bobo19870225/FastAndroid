package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityCustomerServiceBinding;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.CustomerServiceVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-17.
 * FastAndroid
 */
public class CustomerServiceActivity extends MVVMActivity<CustomerServiceVM, ActivityCustomerServiceBinding> {
    @Inject
    ViewModelFactory viewModelFactory;

    @NonNull
    @Override
    protected CustomerServiceVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(CustomerServiceVM.class);
    }

    @Override
    protected void setView() {
        mViewModel.getParameterValueByCode("customerPhone").observe(this, beanResource -> {
            String s = new HttpHelper<String>(getApplicationContext()).AnalyticalData(beanResource);
            mViewModel.ldPhone.setValue(s);
        });
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "pointRule":
                    skipTo(PointRuleActivity.class, "pointRule");
                    break;
                case "pointRul":
                    break;
            }
        });
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "联系客服";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_customer_service;
    }
}
