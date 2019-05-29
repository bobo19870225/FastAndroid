package com.zaomeng.zaomeng.view;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityOrderSettlementBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.AliPayBean;
import com.zaomeng.zaomeng.model.repository.http.bean.BonusBean;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.bean.WeChatPayBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.utils.PayResult;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.OrderSettlementVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @Inject
    HttpHelper httpHelper;
    private final MutableLiveData<Map<String, String>> ldResult = new MutableLiveData<>();
    private int ldPayType;

    @NonNull
    @Override
    protected OrderSettlementVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(OrderSettlementVM.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void setView() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        setOrderList();
        initTotalPrice();
//        setAddressList();
        setAddress();
        RadioGroup radioGroup = mViewDataBinding.radioGroup;
        ldPayType = radioGroup.getCheckedRadioButtonId();

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> ldPayType = checkedId);

        mViewModel.ldSubmitOrder.observe(this, beanResource -> {
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
                if (ldPayType == R.id.radio_weixin) {
                    mViewModel.appApplyMemberOrderPayForWeChat(s).observe(this, payBeanResource -> {
                        if (payBeanResource.isSuccess()) {
                            WeChatPayBean weChatPayBean = payBeanResource.getResource();
                            if (weChatPayBean != null && weChatPayBean.getHeader().getCode() == 0) {
                                weChatPay(weChatPayBean.getBody());
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
                } else if (ldPayType == R.id.radio_zfb) {
                    mViewModel.appApplyMemberOrderPay(s).observe(this, payBeanResource -> {
                        if (payBeanResource.isSuccess()) {
                            AliPayBean resource = payBeanResource.getResource();
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
                skipTo(OrderActivity.class, null);
                finish();
            } else {
                toast("支付失败" + payResult.toString());
                // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
            }
        });
        mViewDataBinding.ttUhq.setClickable(false);
        mViewModel.getMyMemberBonusList().observe(this, pageBeanResource -> {
            PageDataBean<BonusBean> bonusBeanPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {
                    mViewModel.getMyMemberBonusList();
                }
            }, this);
            if (bonusBeanPageDataBean != null) {
                int total = bonusBeanPageDataBean.getTotal();
                if (total != 0) {
                    mViewDataBinding.ttUhq.setClickable(true);
                    mViewModel.ldBonus.setValue("可用" + total + "个红包");
                } else {

                    mViewModel.ldBonus.setValue("无可用红包");
                }

            } else {
                mViewModel.ldBonus.setValue("无可用红包");
            }
        });
        mViewModel.action.observe(this, s -> {
            if (s.equals("bonus")) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ChoseBonusActivity.class);
                startActivityForResult(intent, 110);
//                skipTo(ChoseBonusActivity.class,null);
            } else if (s.equals("choseAddress")) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), ChoseAddressActivity.class);
                startActivityForResult(intent, 111);
//                skipTo(ChoseAddressActivity.class, null);
            }
        });
    }


    private void setAddress() {
        mViewModel.ldAddress.setValue("点击选择地址");
        mViewDataBinding.use.setVisibility(View.GONE);
        mViewDataBinding.phone.setVisibility(View.GONE);
    }

    private double priceNow = 0;

    @SuppressWarnings("unchecked")
    private void initTotalPrice() {
        mViewModel.getOrderGoodsList().observe(this, pageBeanResource -> {
            PageBodyBean<ShopCartBean> shopCartBeanPageBodyBean = httpHelper.AnalyticalPageDataBody(pageBeanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {
                    mViewModel.getOrderGoodsList();
                }
            }, this);
            if (shopCartBeanPageBodyBean != null) {
                priceNow = shopCartBeanPageBodyBean.getPriceAfterDiscount();
                mViewDataBinding.total.setText(String.format("共计：%s", FormatUtils.numberFormatMoney(priceNow)));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110 && resultCode == 0) {
            if (data != null) {
                BonusBean bonusBean = data.getParcelableExtra("DATA");
                double total = priceNow - bonusBean.getAmount();
                if (total > 0) {
                    mViewModel.bonusID = bonusBean.getId();
                    mViewModel.ldBonus.setValue("-" + FormatUtils.numberFormatMoney(bonusBean.getAmount()));
                    mViewDataBinding.bonus.setTextColor(getResources().getColor(R.color.text_them));
                    mViewDataBinding.total.setText(String.format("共计：%s", FormatUtils.numberFormatMoney(total)));
                } else {
                    mViewModel.bonusID = null;
                    mViewModel.ldBonus.setValue("所选红包不可用");
                    mViewDataBinding.bonus.setTextColor(getResources().getColor(R.color.text_main));
                    mViewDataBinding.total.setText(String.format("共计：%s", FormatUtils.numberFormatMoney(priceNow)));
                }

            }
        } else if (requestCode == 111 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                MemberShopBean memberShopBean = data.getParcelableExtra("DATA");
                mViewDataBinding.use.setVisibility(View.VISIBLE);
                mViewDataBinding.phone.setVisibility(View.VISIBLE);
                mViewModel.ldAddress.setValue(memberShopBean.getAddress());
                mViewModel.ldUser.setValue(memberShopBean.getContact());
                mViewModel.ldPhone.setValue(memberShopBean.getContactPhone());
            }
        }
    }

    /**
     * "package": "Sign=WXPay",
     * "appid": "wx2fc887560c55de68",
     * "sign": "6BE4AFC4B41983AF247A7DC0B96744E4",
     * "partnerid": "1534247421",
     * "prepayid": "wx151038117152786d11bb22a03593819073",
     * "noncestr": "1175103810",
     * "timestamp": "1557887891"
     */
    private void weChatPay(WeChatPayBean.BodyBean body) {
        IWXAPI api = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");
        if (body != null && body.getAppid() != null) {
            PayReq req = new PayReq();
            //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
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

//    private void setAddressList() {
//        addressAdapter = new AddressAdapter();
//        addressAdapter.setOnItemClick((view, ItemObject, position) -> {
//            mViewModel.address = ItemObject.getAddress();
//            mViewModel.user = ItemObject.getContact();
//            mViewModel.phone = ItemObject.getContactPhone();
//        });
//        mViewModel.getAddress().observe(this, pageBeanResource -> {
//            PageDataBean<MemberShopBean> memberShopBeanPageDataBean = new HttpHelper<MemberShopBean>(getApplicationContext()).AnalyticalPageData(pageBeanResource);
//            if (memberShopBeanPageDataBean != null) {
//                List<MemberShopBean> rows = memberShopBeanPageDataBean.getRows();
//                addressAdapter.setList(rows);
//            }
//        });
//        mViewDataBinding.list.setAdapter(addressAdapter);
////        mViewDataBinding.scrollView.smoothScrollTo(0, 0);
//    }

    @SuppressWarnings("unchecked")
    private void setOrderList() {
        RecyclerView listOrder = mViewDataBinding.listOrder;
        List<ShopCartBean> shopCartBeans = (List<ShopCartBean>) transferData;
        mViewModel.ldOrderNumber.setValue("共" + shopCartBeans.size() + "件");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        listOrder.setLayoutManager(linearLayoutManager);
        listOrder.setAdapter(new CommonAdapter<ShopCartBean>(getApplicationContext(), R.layout.item_image_small, shopCartBeans) {
            @Override
            protected void convert(ViewHolder holder, ShopCartBean shopCartBean, int position) {
                ImageView image = holder.itemView.findViewById(R.id.image);
                Glide.with(image).load(shopCartBean.getLittleImage()).into(image);
            }
        });
    }


    private void pay(String orderInfo) {
        final Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(this);
            Map<String, String> result = alipay.payV2(orderInfo, true);
            Log.i("msp", result.toString());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 微信支付回调处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        finish();
    }
}
