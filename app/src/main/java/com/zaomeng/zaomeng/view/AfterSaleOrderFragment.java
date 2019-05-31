package com.zaomeng.zaomeng.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentAfterSaleOrderBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.OrderBean;
import com.zaomeng.zaomeng.view.adapter.after_sale.AfterSaleOrderAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.AfterSaleOrderFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class AfterSaleOrderFragment extends MVVMListFragment<AfterSaleOrderFragmentVM, FragmentAfterSaleOrderBinding, AfterSaleOrderAdapter> {

    @Inject
    public AfterSaleOrderFragment() {
    }

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;
    private BottomSheetDialog bottomSheetDialog;
    private TextView submit;
    private EditText msg;
    private Context context;

    @Override
    protected void setUI() {
        context = getContext();
        if (context != null) {
            bottomSheetDialog = new BottomSheetDialog(context);
            bottomSheetDialog.setCancelable(true);
        }
        @SuppressLint("InflateParams") View view = getLayoutInflater().inflate(R.layout.dialog_after_sale, null, false);
        msg = view.findViewById(R.id.msg);
        submit = view.findViewById(R.id.submit);
        bottomSheetDialog.setContentView(view);
    }

    @NonNull
    @Override
    protected AfterSaleOrderAdapter setAdapter(Function0 reTry) {
        AfterSaleOrderAdapter orderAdapter = new AfterSaleOrderAdapter(reTry);
        orderAdapter.setOnItemReturnClick((view, ItemObject, position) -> {
            showWhyDialog(ItemObject);

        });
        return orderAdapter;
    }

    private void showWhyDialog(OrderBean.GoodsListBean itemObject) {
        submit.setOnClickListener(v -> {
            String string = msg.getText().toString();
            if (string.isEmpty()) {
                toast("请您写下退款理由");
                return;
            }
            applyMemberOrderGoodsReturn(itemObject.getId(), string);
        });
        if (!bottomSheetDialog.isShowing()) {
            bottomSheetDialog.show();
        }
    }

    private void applyMemberOrderGoodsReturn(String goodsID, String description) {
        mViewModel.applyMemberOrderGoodsReturn(goodsID, description).observe(this, beanResource -> {
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
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
                refresh();
            }
        });
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
    protected AfterSaleOrderFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(AfterSaleOrderFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_after_sale_order;
    }
}
