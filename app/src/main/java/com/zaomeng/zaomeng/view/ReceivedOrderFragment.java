package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentReceivedOrderBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.view.adapter.order.OrderAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.ReceivedOrderFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class ReceivedOrderFragment extends MVVMListFragment<ReceivedOrderFragmentVM, FragmentReceivedOrderBinding, OrderAdapter> {

    @Inject
    public ReceivedOrderFragment() {
    }

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;
    @Override
    protected void setUI() {
    }

    @NonNull
    @Override
    protected OrderAdapter setAdapter(Function0 reTry) {
        OrderAdapter orderAdapter = new OrderAdapter(reTry);
        orderAdapter.setOnItemConfirmClick((view, ItemObject, position) -> mViewModel.confirmMemberOrder(ItemObject.getGoodsList().get(0).getMemberOrderID()).observe(this, beanResource -> {
            String s = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {

                }
            }, this);
            if (s != null) {
                refresh();
            }
        }));
        return orderAdapter;
    }


    @NonNull
    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @Override
    protected ReceivedOrderFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(ReceivedOrderFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_received_order;
    }
}
