package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentShoppingCartBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.view.adapter.shop_cart.ShopCartAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.ShoppingCartFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/21.
 * FastAndroid
 */
public class ShoppingCartFragment extends MVVMListFragment<ShoppingCartFragmentVM, FragmentShoppingCartBinding, ShopCartAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;
    //    private AlertDialog waitDialog;
//    private double priceTotal;
    private AlertDialog alertDialog;
    private TextView ok;
    @Inject
    HttpHelper httpHelper;

    @Inject
    public ShoppingCartFragment() {
    }


    @Override
    protected void setUI() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        requestPermission();
        initDeleteDialog();
        shopCartAdapter.isSelectedAll.observe(this, aBoolean -> {
            if (aBoolean) {
                mViewDataBinding.select.setImageResource(R.mipmap.selected);
            } else {
                mViewDataBinding.select.setImageResource(R.mipmap.un_select);
            }
        });
        mViewModel.ldTotal.observe(this, aDouble -> {
            TextView ttTotal = mViewDataBinding.ttTotal;
            TextView total = mViewDataBinding.total;

            if (aDouble == 0) {
                ttTotal.setVisibility(View.GONE);
                total.setVisibility(View.GONE);
                disableSettlement(false, R.color.bg_color);
            } else {
                ttTotal.setVisibility(View.VISIBLE);
                total.setVisibility(View.VISIBLE);
                total.setText(FormatUtils.numberFormatMoney(aDouble));
                mViewModel.getParameterValueByCode().observe(ShoppingCartFragment.this, beanResource -> {
                    String s = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                        @Override
                        public void skipLoginActivity() {

                        }

                        @Override
                        public void reLoad() {

                        }
                    }, ShoppingCartFragment.this);
                    if (s != null) {
                        Double totalPrice = mViewModel.ldTotal.getValue();

                        if (totalPrice != null) {
                            try {
                                if (totalPrice < Double.valueOf(s)) {
                                    disableSettlement(false, R.color.bg_color);
                                } else {
                                    disableSettlement(true, R.color.them_color);
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });
            }
        });

//        mViewModel.ldDiscount.observe(this, aDouble -> {
//            TextView ttDiscount = mViewDataBinding.ttDiscount;
//            TextView discount = mViewDataBinding.discount;
//            if (aDouble == 0) {
//                ttDiscount.setVisibility(View.GONE);
//                discount.setVisibility(View.GONE);
//            } else {
//                ttDiscount.setVisibility(View.VISIBLE);
//                discount.setVisibility(View.VISIBLE);
//                discount.setText(FormatUtils.numberFormatMoney(aDouble));
//            }
//        });
        mViewModel.action.observe(this, s -> {
            List<List<ShopCartBean>> listGoodsItem = shopCartAdapter.getListGoodsItem();
            switch (s) {
                case "settlement":
                    List<ShopCartBean> selectItem = listGoodsItem.get(1);
                    if (selectItem.size() == 0) {
                        toast("请选择商品");
                        return;
                    }
                    skipTo(OrderSettlementActivity.class, selectItem);

                    break;
                case "selectAll":
                    Boolean value = shopCartAdapter.isSelectedAll.getValue();
                    if (value != null) {
//                        mViewModel.ldIsSelectAll.setValue(!value);
                        List<ShopCartBean> shopCartBeans = listGoodsItem.get(0);
                        if (shopCartBeans.size() != 0) {
                            StringBuilder selectID = new StringBuilder();
                            for (int i = 0; i < shopCartBeans.size(); i++) {
                                if (i == shopCartBeans.size() - 1) {
                                    selectID.append(shopCartBeans.get(i).getId());
                                } else {
                                    selectID.append(shopCartBeans.get(i).getId()).append(",");
                                }
                            }
                            if (value) {
                                mViewModel.selectGoods(selectID.toString(), 0).observe(this, beanResource -> refresh());
                            } else {
                                mViewModel.selectGoods(selectID.toString(), 1).observe(this, beanResource -> refresh());
                            }
                        }
                        refresh();
                    }
                    break;
                case "delete":
                    List<ShopCartBean> selectGoods = listGoodsItem.get(1);
                    if (selectGoods.size() != 0) {
                        showDeleteDialog(selectGoods);
                    } else {
                        toast("请先选择商品");
                    }

                    break;
                default:
                    break;
            }
        });
        mViewModel.ldGoodsTotal.observe(this, integer -> {
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                activity.badge.setBadgeNumber(integer);
            }
        });

    }

    private void initDeleteDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View inflate = layoutInflater.inflate(R.layout.dialog_delete, null, false);
        TextView cancel = inflate.findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> {
            if (alertDialog.isShowing())
                alertDialog.dismiss();
        });
        ok = inflate.findViewById(R.id.ok);

        alertDialog = new AlertDialog.Builder(getContext()).
                setView(inflate).
                create();
    }

    private void disableSettlement(boolean enable, int color) {
        TextView settlement = mViewDataBinding.settlement;
        settlement.setEnabled(enable);
        settlement.setBackgroundColor(getResources().getColor(color));
    }

    @SuppressWarnings("unchecked")
    private void showDeleteDialog(List<ShopCartBean> selectGoods) {
        ok.setOnClickListener(v -> {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
            StringBuilder selectID = new StringBuilder();
            for (int i = 0; i < selectGoods.size(); i++) {
                if (i == selectGoods.size() - 1) {
                    selectID.append(selectGoods.get(i).getId());
                } else {
                    selectID.append(selectGoods.get(i).getId()).append(",");
                }
            }
            mViewModel.removeCartGoods(selectID.toString()).observe(this, beanResource -> {
                String s1 = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                    @Override
                    public void skipLoginActivity() {
                        skipTo(LoginActivity.class);
                    }

                    @Override
                    public void reLoad() {
                        mViewModel.removeCartGoods(selectID.toString());
                    }
                }, this);
                if (s1 != null) refresh();
            });
        });
        if (!alertDialog.isShowing()) {
            alertDialog.show();
            Window window = alertDialog.getWindow();
            if (window != null) {
//                window.setContentView(R.layout.dialog_delete);
                window.setLayout(800, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            }
        }

    }

    private ShopCartAdapter shopCartAdapter;

    @NonNull
    @Override
    protected ShopCartAdapter setAdapter(Function0 reTry) {
        shopCartAdapter = new ShopCartAdapter(reTry);
        shopCartAdapter.setOnAddClick((view, ItemObject, position) -> upDataGoodsNumber(ItemObject));
        shopCartAdapter.setOnReduceClick((view, ItemObject, position) -> upDataGoodsNumber(ItemObject));
        shopCartAdapter.setOnSelectClick((view, ItemObject, position) -> {
            int isSelected = ItemObject.getIsSelected();
            if (isSelected == 1) {
                isSelected = 0;
            } else {
                isSelected = 1;
            }
            mViewModel.selectGoods(ItemObject.getId(), isSelected).observe(this, beanResource -> {
                refresh();
//                shopCartAdapter.notifyDataSetChanged();
            });
        });
        return shopCartAdapter;
    }

    private void upDataGoodsNumber(ShopCartBean ItemObject) {
//        showWaitDialog(true);
        mViewModel.updateCartGoodsNumber(ItemObject.getId(), ItemObject.getQty()).observe(this, beanResource -> {
            String s = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {
                    mViewModel.updateCartGoodsNumber(ItemObject.getId(), ItemObject.getQty());
                }
            }, this);
            if (s == null) {
                //更改失败,刷新列表
//                showWaitDialog(false);
                setListView(transferData);
            }
            refresh();
        });
    }


//    private void showWaitDialog(boolean isShow) {
//        if (isShow) {
//            waitDialog.show();
//        } else if (waitDialog.isShowing()) {
//            waitDialog.dismiss();
//        }
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

    //    private void doRefresh() {
//        List<List<ShopCartBean>> listGoodsItem1 = shopCartAdapter.getListGoodsItem();
//        priceTotal = 0;
//        List<ShopCartBean> shopCartBeans1 = listGoodsItem1.get(1);
//        for (ShopCartBean shopCartBean : shopCartBeans1
//        ) {
//            priceTotal += shopCartBean.getPriceTotal();
//        }
//        refresh();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (event.equals("refreshShopCart")) {
            refresh();
        }
    }
}
