package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentReceivedOrderBinding;
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

    @Override
    protected void setUI() {
//        ldResult.observe(this, stringStringMap -> {
//            PayResult payResult = new PayResult(stringStringMap);
//            /*
//             * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
//             */
//            String resultStatus = payResult.getResultStatus();
//            // 判断resultStatus 为9000则代表支付成功
//            if (TextUtils.equals(resultStatus, "9000")) {
//                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
////                showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
//                toast("支付成功" + payResult.toString());
//            } else {
//                toast("支付失败" + payResult.toString());
//                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
////                showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
//            }
//        });
    }

    @NonNull
    @Override
    protected OrderAdapter setAdapter(Function0 reTry) {
        OrderAdapter orderAdapter = new OrderAdapter(reTry);
//        orderAdapter.setOnItemClick((view, ItemObject, position) -> {
//            mViewModel.appApplyMemberOrderPay(ItemObject.getGoodsList().get(0).getMemberOrderID())
//                    .observe(this, beanResource -> {
//                        if (beanResource.isSuccess()) {
//                            PayBean resource = beanResource.getResource();
//                            if (resource != null && resource.getHeader().getCode() == 0) {
//                                pay(resource.getBody().getDataString());
//                            } else {
//                                if (resource != null) {
//                                    toast(resource.getHeader().getMsg());
//                                }
//                            }
//                        } else {
//                            Throwable error = beanResource.getError();
//                            if (error != null) {
//                                toast(error.toString());
//                            }
//                        }
//                    });
////            skipTo(OrderSettlementActivity.class, ItemObject.getGoodsList().get(0).getMemberOrderID());
//        });
        return orderAdapter;
    }

//    private void pay(String dataString) {
//        final Runnable payRunnable = () -> {
//            PayTask alipay = new PayTask(getActivity());
//            Map<String, String> result = alipay.payV2(dataString, true);
//            Log.i("msp", result.toString());
////                        Message msg = new Message();
////                        msg.what = SDK_PAY_FLAG;
////                        msg.obj = result;
////                        mHandler.sendMessage(msg);
//            ldResult.postValue(result);
//        };
//        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
//    }

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
