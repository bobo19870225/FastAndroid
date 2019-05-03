package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityOrderSettlementBinding;
import com.zaomeng.zaomeng.model.repository.dataBase.Address;
import com.zaomeng.zaomeng.utils.DBUtils;
import com.zaomeng.zaomeng.view.adapter.LocationAdapter;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view.custom_view.RecyclerViewForScrollView;
import com.zaomeng.zaomeng.view_model.OrderSettlementVM;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-02.
 * FastAndroid
 */
public class OrderSettlementActivity extends MVVMActivity<OrderSettlementVM, ActivityOrderSettlementBinding> {
    @Inject
    DBUtils dbUtils;
    private List<Address> addressList;
    @NonNull
    @Override
    protected OrderSettlementVM createdViewModel() {
        return ViewModelProviders.of(this).get(OrderSettlementVM.class);
    }

    @Override
    protected void setView() {
        LocationAdapter locationAdapter = new LocationAdapter();
        addressList = dbUtils.getProvincesFromDB();
        RecyclerViewForScrollView list = mViewDataBinding.list;
        list.setAdapter(locationAdapter);
//        list.setLayoutManager(new FullyLinearLayoutManager(getApplicationContext()));
        locationAdapter.setOnItemClick((view, ItemObject, position) -> {
            List<Address> provincesFromDBByPID = dbUtils.getProvincesFromDBByPID(ItemObject.ID);
            locationAdapter.setList(provincesFromDBByPID);
        });
        locationAdapter.setList(addressList);
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
