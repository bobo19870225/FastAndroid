package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityOrderDetailBinding;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.OrderDetailVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-17.
 * FastAndroid
 */
public class OrderDetailActivity extends MVVMActivity<OrderDetailVM, ActivityOrderDetailBinding> {
    @Inject
    ViewModelFactory viewModelFactory;

    @NonNull
    @Override
    protected OrderDetailVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(OrderDetailVM.class);
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
        return "订单详情";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_order_detail;
    }
}
