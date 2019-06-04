package com.zaomeng.zaomeng.view;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityAddressManageBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
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
    @Inject
    HttpHelper httpHelper;
    private AlertDialog alertDialog;
    private TextView ok;

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
        memberShopAdapter.setOnEditClick((view, ItemObject, position) -> skipTo(CertificationActivity.class, ItemObject));
        memberShopAdapter.setOnDeleteClick((view, ItemObject, position) -> {
            showDeleteDialog(ItemObject.getId());
        });
        return memberShopAdapter;
    }

    @SuppressWarnings("unchecked")
    private void showDeleteDialog(String id) {
        ok.setOnClickListener(v -> mViewModel.removeMemberShop(id).observe(AddressManageActivity.this, beanResource -> {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            String s = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class, null);
                }

                @Override
                public void reLoad() {
                    mViewModel.removeMemberShop(id);
                }
            }, this);
            if (s != null)
                refresh();
        }));
        if (!alertDialog.isShowing()) {
            alertDialog.show();
            Window window = alertDialog.getWindow();
            if (window != null) {
//                window.setContentView(R.layout.dialog_delete);
                window.setLayout(800, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            }
        }

    }


    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @Override
    protected void setView() {
        super.setView();
        LayoutInflater layoutInflater = getLayoutInflater();
        View inflate = layoutInflater.inflate(R.layout.dialog_delete, null, false);
        TextView cancel = inflate.findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> {
            if (alertDialog.isShowing())
                alertDialog.dismiss();
        });
        ok = inflate.findViewById(R.id.ok);

        alertDialog = new AlertDialog.Builder(this).
                setView(inflate).
                create();
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
