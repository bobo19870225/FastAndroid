package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentShoppingCartBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.adapter.shop_cart.ShopCartAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.ShoppingCartFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

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

    @Inject
    public ShoppingCartFragment() {
    }


    @Override
    protected void setUI() {
//        initDialog();
        requestPermission();
        shopCartAdapter.isSelectedAll.observe(this, aBoolean -> {
            if (aBoolean) {
                mViewDataBinding.select.setImageResource(R.mipmap.selected);
            } else {
                mViewDataBinding.select.setImageResource(R.mipmap.un_select);
            }
        });

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

//    private void initDialog() {
//        LayoutInflater layoutInflater = getLayoutInflater();
//        View view = layoutInflater.inflate(R.layout.dialog_wait, null, false);
//        waitDialog = new AlertDialog.Builder(getContext()).
//                setCancelable(false).
//                setView(view).
//                create();
//    }


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
            String s = new HttpHelper<String>(getContext()).AnalyticalData(beanResource);
            if (s == null) {
                //更改失败,刷新列表
//                showWaitDialog(false);
                setListView(transferData);
            }

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

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
}
