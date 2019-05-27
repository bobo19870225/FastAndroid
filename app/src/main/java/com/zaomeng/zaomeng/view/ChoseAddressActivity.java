package com.zaomeng.zaomeng.view;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityChoseAddressBinding;
import com.zaomeng.zaomeng.view.adapter.address.AddressAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.ChoseAddressVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-27.
 * FastAndroid
 */
public class ChoseAddressActivity extends MVVMListActivity<ChoseAddressVM, ActivityChoseAddressBinding, AddressAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @NonNull
    @Override
    protected AddressAdapter setAdapter(Function0 reTry) {
        AddressAdapter addressAdapter = new AddressAdapter(reTry);
        addressAdapter.setOnItemClick((view, ItemObject, position) -> {
            Intent intent = new Intent();
            intent.putExtra("DATA", ItemObject);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });
        return addressAdapter;
    }

    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected ChoseAddressVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(ChoseAddressVM.class);
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "收货地址";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_chose_address;
    }
}
