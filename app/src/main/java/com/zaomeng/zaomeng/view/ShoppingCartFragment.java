package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alipay.sdk.app.PayTask;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentShoppingCartBinding;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.utils.PayResult;
import com.zaomeng.zaomeng.view.adapter.shop_cart.ShopCartAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.ShoppingCartFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/21.
 * FastAndroid
 */
public class ShoppingCartFragment extends MVVMListFragment<ShoppingCartFragmentVM, FragmentShoppingCartBinding, ShopCartAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    public ShoppingCartFragment() {
    }

    private final MutableLiveData<Map<String, String>> ldResult = new MutableLiveData<>();
    @Override
    protected void doError(NetWorkState o) {

    }

    @Override
    protected void setUI() {
        requestPermission();
        mViewModel.ldIsSelectAll.observe(this, aBoolean -> {
            if (aBoolean) {
                mViewDataBinding.select.setImageResource(R.mipmap.selected);
            } else {
                mViewDataBinding.select.setImageResource(R.mipmap.un_select);
            }
        });
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "settlement":
                    List<ShopCartBean> listSelectedItem = shopCartAdapter.getListSelectedItem();
                    if (listSelectedItem.size() == 0) {
                        toast("请选择商品");
                    }
//                    pay();
                    break;
                case "selectAll":
                    shopCartAdapter.selectedAll();
                    break;
                default:
                    break;
            }
        });
        ldResult.observe(this, stringStringMap -> {
            PayResult payResult = new PayResult(stringStringMap);
            /*
             * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
             */
            String resultInfo = payResult.getResult();// 同步返回需要验证的信息
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

    private void pay() {
        final Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(getActivity());
            String orderInfo = null;
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

    private ShopCartAdapter shopCartAdapter;
    @NonNull
    @Override
    protected ShopCartAdapter setAdapter(Function0 reTry) {
        return shopCartAdapter = new ShopCartAdapter(reTry);
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
    protected ShoppingCartFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(ShoppingCartFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_shopping_cart;
    }

    /**
     * 获取权限使用的 RequestCode
     */
    private static final int PERMISSIONS_REQUEST_CODE = 1002;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            // 用户取消了权限弹窗
            if (grantResults.length == 0) {
                toast("用户取消了权限弹窗");
                return;
            }
            // 用户拒绝了某些权限
            for (int x : grantResults) {
                if (x == PackageManager.PERMISSION_DENIED) {
                    toast("用户拒绝了某些权限");
                    return;
                }
            }
            // 所需的权限均正常获取
            toast("所需的权限均正常获取");
        }
    }

    private void requestPermission() {
        // Here, thisActivity is the current activity
        Context context = getContext();
        if (context != null) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, PERMISSIONS_REQUEST_CODE);

            }
        }
    }
}
