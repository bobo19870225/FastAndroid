package com.zaomeng.zaomeng.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentOrderBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.AliPayBean;
import com.zaomeng.zaomeng.model.repository.http.bean.WeChatPayBean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.utils.PayResult;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;
import com.zaomeng.zaomeng.utils.pay_way.ChosePayWayHelper;
import com.zaomeng.zaomeng.view.adapter.order.OrderAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.OrderFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class AllOrderFragment extends MVVMListFragment<OrderFragmentVM, FragmentOrderBinding, OrderAdapter> {
    private final SingleLiveEvent<Map<String, String>> ldResult = new SingleLiveEvent<>();
    private ChosePayWayHelper chosePayWayHelper;
    private String memberOrderID;
    @Inject
    public AllOrderFragment() {
    }

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void setUI() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        Context context = getContext();
        if (context != null) {
            chosePayWayHelper = new ChosePayWayHelper(context, payWay -> {
                if (payWay == R.id.we_chat_pay) {
                    mViewModel.appApplyMemberOrderPayForWeCat(memberOrderID)
                            .observe(this, beanResource -> {
                                if (beanResource.isSuccess()) {
                                    WeChatPayBean weChatPayBean = beanResource.getResource();
                                    if (weChatPayBean != null && weChatPayBean.getHeader().getCode() == 0) {
                                        pay(weChatPayBean.getBody());
                                    } else {
                                        if (weChatPayBean != null) {
                                            toast(weChatPayBean.getHeader().getMsg());
                                        }
                                    }
                                } else {
                                    Throwable error = beanResource.getError();
                                    if (error != null) {
                                        toast(error.toString());
                                    }
                                }
                            });
                } else if (payWay == R.id.ali_pay) {
                    mViewModel.appApplyMemberOrderPay(memberOrderID)
                            .observe(this, beanResource -> {
                                if (beanResource.isSuccess()) {
                                    AliPayBean resource = beanResource.getResource();
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
        }
        ldResult.observe(this, stringStringMap -> {
            PayResult payResult = new PayResult(stringStringMap);
            /*
             * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultStatus = payResult.getResultStatus();
            // 判断resultStatus 为9000则代表支付成功
            if (TextUtils.equals(resultStatus, "9000")) {
                // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                EventBus.getDefault().post("refresh");
//                toast("支付成功");
            } else {
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                toast("支付失败");

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @NonNull
    @Override
    protected OrderAdapter setAdapter(Function0 reTry) {
        OrderAdapter orderAdapter = new OrderAdapter(reTry);
        orderAdapter.setOnItemClick((view, ItemObject, position) -> {
            memberOrderID = ItemObject.getGoodsList().get(0).getMemberOrderID();
            chosePayWayHelper.showPayWayDialog(getLayoutInflater());
        });
        orderAdapter.setOnItemCancelClick((view, ItemObject, position) -> {//取消订单
            mViewModel.cancelMemberOrder(ItemObject.getId()).observe(this, beanResource -> {
                String s = new HttpHelper<String>(getContext()).AnalyticalData(beanResource);
                if (s != null) {
                    EventBus.getDefault().post("refresh");
                }
            });
        });
        return orderAdapter;
    }

    private void pay(String dataString) {
        final Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(getActivity());
            Map<String, String> result = alipay.payV2(dataString, true);
            Log.i("msp", result.toString());
            ldResult.postValue(result);
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void pay(WeChatPayBean.BodyBean body) {
        IWXAPI api = WXAPIFactory.createWXAPI(getContext(), "wxb4ba3c02aa476ea1");
        if (body != null && body.getAppid() != null) {
            PayReq req = new PayReq();
            req.appId = body.getAppid();
            req.partnerId = body.getPartnerid();
            req.prepayId = body.getPrepayid();
            req.nonceStr = body.getNoncestr();
            req.timeStamp = body.getTimestamp();
            req.packageValue = body.getPackageX();
            req.sign = body.getSign();
            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
            api.sendReq(req);
        }

    }

    /**
     * 微信支付回调处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (event.equals("ok")) {
            refresh();
        } else if (event.equals("refresh")) {
            refresh();
        }
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
    protected OrderFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(OrderFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_order;
    }
}
