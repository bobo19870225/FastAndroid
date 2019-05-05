package com.zaomeng.zaomeng.view;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityAddressManageBinding;
import com.zaomeng.zaomeng.view.adapter.member_shop.MemberShopAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.AddressManageVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-04.
 * FastAndroid
 */
public class AddressManageActivity extends MVVMListActivity<AddressManageVM, ActivityAddressManageBinding, MemberShopAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;
    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @NonNull
    @Override
    protected MemberShopAdapter setAdapter(Function0 reTry) {
        return new MemberShopAdapter(reTry);
    }


    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }


    @Override
    protected int setToolBarMenu() {
        return R.menu.address;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add_shop) {
            skipTo(CertificationActivity.class, null);
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected String setToolBarTitle() {
        return "收货地址管理";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_address_manage;
    }

    @NonNull
    @Override
    protected AddressManageVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(AddressManageVM.class);
    }
}
