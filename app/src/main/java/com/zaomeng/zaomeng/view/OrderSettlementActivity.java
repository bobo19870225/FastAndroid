package com.zaomeng.zaomeng.view;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;

import com.alipay.sdk.app.PayTask;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityOrderSettlementBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PayBean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.utils.PayResult;
import com.zaomeng.zaomeng.view.adapter.address.AddressAdapter;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.OrderSettlementVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-02.
 * FastAndroid
 */
public class OrderSettlementActivity extends MVVMActivity<OrderSettlementVM, ActivityOrderSettlementBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    private AddressAdapter addressAdapter;
    private final MutableLiveData<Map<String, String>> ldResult = new MutableLiveData<>();

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
        mViewModel.ldSubmitOrder.observe(this, beanResource -> {
            String s = new HttpHelper<String>(getApplicationContext()).AnalyticalData(beanResource);
            if (s != null) {
                mViewModel.appApplyMemberOrderPay(s).observe(this, payBeanResource -> {
                    if (payBeanResource.isSuccess()) {
                        PayBean resource = payBeanResource.getResource();
                        if (resource != null && resource.getHeader().getCode() == 0) {
                            pay(resource.getBody().getDataString());
                        } else {
                            if (resource != null) {
                                toast(resource.getHeader().getMsg());
                            }
                        }
                    } else {
                        Throwable error = beanResource.getError();
                        if (error != null) {
                            toast(error.toString());
                        }
                    }

                });

            }
        });
        ldResult.observe(this, stringStringMap -> {
            PayResult payResult = new PayResult(stringStringMap);
            /*
             * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
                toast("支付成功" + payResult.toString());
            } else {
                toast("支付失败" + payResult.toString());
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
            }
        });
    }

    private void pay(String orderInfo) {
        final Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(this);
            Map<String, String> result = alipay.payV2(orderInfo, true);
            Log.i("msp", result.toString());
//                        Message msg = new Message();
//                        msg.what = SDK_PAY_FLAG;
//                        msg.obj = result;
//                        mHandler.sendMessage(msg);
            ldResult.postValue(result);
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
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
