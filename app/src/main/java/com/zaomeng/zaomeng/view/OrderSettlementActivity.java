package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityOrderSettlementBinding;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.OrderSettlementVM;

/**
 * Created by Sampson on 2019-05-02.
 * FastAndroid
 */
public class OrderSettlementActivity extends MVVMActivity<OrderSettlementVM, ActivityOrderSettlementBinding> {

    @NonNull
    @Override
    protected OrderSettlementVM createdViewModel() {
        return ViewModelProviders.of(this).get(OrderSettlementVM.class);
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
        return "订单结算";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_order_settlement;
    }
}
