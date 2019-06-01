package com.zaomeng.zaomeng.view;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentAfterSaleRecordBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.view.adapter.after_sale.AfterSaleRecordAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.AfterSaleRecordFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class AfterSaleRecordFragment extends MVVMListFragment<AfterSaleRecordFragmentVM, FragmentAfterSaleRecordBinding, AfterSaleRecordAdapter> {

    @Inject
    public AfterSaleRecordFragment() {
    }

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;

    private Context context;

    @Override
    protected void setUI() {
        context = getContext();
    }

    @NonNull
    @Override
    protected AfterSaleRecordAdapter setAdapter(Function0 reTry) {
        AfterSaleRecordAdapter afterSaleOrderAdapter = new AfterSaleRecordAdapter(reTry);
        afterSaleOrderAdapter.setOnItemClick((view, ItemObject, position) -> {
            skipTo(AfterSaleDetailsActivity.class, ItemObject.getId());
        });
        return afterSaleOrderAdapter;
    }


    @NonNull
    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        RecyclerView recyclerView = mViewDataBinding.list;
        LinearLayoutManager layoutManager = new LinearLayoutManager(context) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
            }
        };
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        return recyclerView;
    }

    @Override
    protected AfterSaleRecordFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(AfterSaleRecordFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_after_sale_record;
    }
}
