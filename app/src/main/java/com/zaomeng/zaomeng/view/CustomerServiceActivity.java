package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityCustomerServiceBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
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
    @Inject
    HttpHelper httpHelper;

    @NonNull
    @Override
    protected CustomerServiceVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(CustomerServiceVM.class);
    }

    @Override
    protected void setView() {
        mViewModel.getParameterValueByCode("customerPhone").observe(this, beanResource -> {
            String s = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class, null);
                }

                @Override
                public void reLoad() {

                }
            }, this);
            mViewModel.ldPhone.setValue(s);
        });
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "pointRule":
                    skipTo(ArticleActivity.class, "积分规则");
                    break;
                case "FAQ":
                    skipTo(ArticleActivity.class, "常见问题");
                    break;
                case "payQuestion":
                    skipTo(ArticleActivity.class, "支付问题");
                    break;
                case "deliveryQuestion":
                    skipTo(ArticleActivity.class, "配送问题");
                    break;
                case "returnGoodsQuestion":
                    skipTo(ArticleActivity.class, "退换货问题");
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
