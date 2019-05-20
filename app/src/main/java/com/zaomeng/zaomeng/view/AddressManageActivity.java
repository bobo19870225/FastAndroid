package com.zaomeng.zaomeng.view;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityAddressManageBinding;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.adapter.member_shop.MemberShopAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.AddressManageVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        MemberShopAdapter memberShopAdapter = new MemberShopAdapter(reTry);
        memberShopAdapter.setOnItemClick((view, ItemObject, position) -> skipTo(ShopDetailActivity.class, ItemObject.getId()));
        memberShopAdapter.setOnEditClick((view, ItemObject, position) -> {
            skipTo(CertificationActivity.class, ItemObject);
        });
        memberShopAdapter.setOnDeleteClick((view, ItemObject, position) -> mViewModel.removeMemberShop(ItemObject.getId()).observe(this, beanResource -> {
            String s = new HttpHelper<String>(getApplicationContext()).AnalyticalData(beanResource);
            if (s != null)
                refresh();
        }));
        return memberShopAdapter;
    }


    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @Override
    protected void setView() {
        super.setView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 刷新界面
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (event.equals("refresh")) {
            refresh();
        }
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
