package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityAfterSaleDetailsBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsReturnDetailBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.AfterSaleDetailsVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-31.
 * FastAndroid
 */
public class AfterSaleDetailsActivity extends MVVMActivity<AfterSaleDetailsVM, ActivityAfterSaleDetailsBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;

    @NonNull
    @Override
    protected AfterSaleDetailsVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(AfterSaleDetailsVM.class);
    }

    @Override
    protected void setView() {
        mViewModel.getMemberOrderGoodsReturnDetail((String) transferData).observe(this, beanResource -> {
            GoodsReturnDetailBean pageDataBean = (GoodsReturnDetailBean) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {
                    mViewModel.getMemberOrderGoodsReturnDetail((String) transferData);
                }
            }, AfterSaleDetailsActivity.this);
            if (pageDataBean != null) {
                mViewModel.ldNo.setValue(pageDataBean.getReturnCode());
                mViewModel.ldTime.setValue(pageDataBean.getApplyTimeStr());
                mViewDataBinding.price.setText(FormatUtils.numberFormatMoney(pageDataBean.getPriceTotalReturn()));
                mViewModel.ldState.setValue(pageDataBean.getMemo());
            }
        });

    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "申请退款详情";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_after_sale_details;
    }
}
