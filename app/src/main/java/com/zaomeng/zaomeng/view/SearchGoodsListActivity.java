package com.zaomeng.zaomeng.view;

import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityGoodsListBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.view.adapter.goods.GoodsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.SearchGoodsListVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-14.
 * FastAndroid
 */
public class SearchGoodsListActivity extends MVVMListActivity<SearchGoodsListVM, ActivityGoodsListBinding, GoodsAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;

    @Override
    protected void setView() {
        super.setView();
        mViewModel.action.observe(this, s -> {
            if (s.equals("cancel")) {
                skipTo(MainActivity.class, true);
            } else if (s.equals("clean")) {
                finish();
            }
        });
        mViewDataBinding.search.clearFocus();
        mViewDataBinding.search.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_SEARCH || id == EditorInfo.IME_NULL) {
                if (mViewModel.ldSearch.getValue() == null) {
                    toast("请填写搜索关键词");
                } else {
                    setListView(mViewModel.ldSearch.getValue());
                }
                return true;
            }
            return false;
        });
    }

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @NonNull
    @Override
    protected GoodsAdapter setAdapter(Function0 reTry) {
        GoodsAdapter goodsAdapter = new GoodsAdapter(reTry);
        goodsAdapter.setOnItemClick((view, ItemObject, position) -> skipTo(GoodsDetailsActivity.class, ItemObject.getId()));
        goodsAdapter.setOnAddClick((view, ItemObject, position) -> {
//            showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), null, ItemObject.getId());
//            getSpecification(ItemObject);
            mViewModel.addGoodsShopToCart(ItemObject.getId(), 1, ItemObject.getObjectFeatureItemID1()).observe(this, beanResource -> {
                Object o = httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                    @Override
                    public void skipLoginActivity() {
                        skipTo(LoginActivity.class);
                    }

                    @Override
                    public void reLoad() {
                        mViewModel.addGoodsShopToCart(ItemObject.getId(), 1, ItemObject.getObjectFeatureItemID1());
                    }
                }, this);
                if (o != null) {
//                    addBadge(1);
                    toast("添加成功");
                }
            });

        });
        return goodsAdapter;
    }

    //    private void addBadge(int qty) {
//
//            int badgeNumber = mainActivity.badge.getBadgeNumber();
//            mainActivity.badge.setBadgeNumber(badgeNumber + qty);
//
//    }
    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected SearchGoodsListVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(SearchGoodsListVM.class);
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_goods_list;
    }
}
