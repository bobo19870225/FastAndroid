package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityOrderSettlementBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.adapter.address.AddressAdapter;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.OrderSettlementVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-02.
 * FastAndroid
 */
public class OrderSettlementActivity extends MVVMActivity<OrderSettlementVM, ActivityOrderSettlementBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    private AddressAdapter addressAdapter;
    @NonNull
    @Override
    protected OrderSettlementVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(OrderSettlementVM.class);
    }

    @Override
    protected void setView() {
        addressAdapter = new AddressAdapter();
        mViewModel.getAddress().observe(this, pageBeanResource -> {
            PageDataBean<MemberShopBean> memberShopBeanPageDataBean = new HttpHelper<MemberShopBean>(getApplicationContext()).AnalyticalPageData(pageBeanResource);
            if (memberShopBeanPageDataBean != null) {
                List<MemberShopBean> rows = memberShopBeanPageDataBean.getRows();
                addressAdapter.setList(rows);
            }
        });
        mViewDataBinding.list.setAdapter(addressAdapter);
        mViewDataBinding.scrollView.smoothScrollTo(0, 0);

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
