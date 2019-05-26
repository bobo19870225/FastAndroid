package com.zaomeng.zaomeng.view;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentSortBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.adapter.GoodsParentAdapter;
import com.zaomeng.zaomeng.view.adapter.goods.GoodsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.SortFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;


public class SortFragment extends MVVMListFragment<SortFragmentVM, FragmentSortBinding, GoodsAdapter> {
    private static final int SEARCH = 110;
    @Inject
    ViewModelFactory viewModelFactory;
    //    private ShowSpecificationHelper showSpecificationHelper;
    private int oldPosition = 0;
    @Inject
    public SortFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_sort;
    }


    private List<GoodsSuperBean> rows;

    @Override
    protected void setUI() {
        Context context = getContext();
        mViewModel.action.observe(this, s -> {
            if (s.equals("search")) {
                Intent intent = new Intent();
                if (context != null) {
                    intent.setClass(context, SearchActivity.class);
                    startActivityForResult(intent, SEARCH);
                }

            }
        });
        GoodsParentAdapter goodsParentAdapter = new GoodsParentAdapter(context, rows);
        goodsParentAdapter.setOnItemClick((view, ItemObject, position) -> {
            if (oldPosition != position)//防止重复点击
                setListView(ItemObject.getId());
            oldPosition = position;
        });
        mViewDataBinding.list1.setAdapter(goodsParentAdapter);
        mViewModel.getNodeCategoryList().observe(this, pageBeanResource -> {
            HttpHelper<GoodsSuperBean> goodsSuperBeanHttpHelper = new HttpHelper<>(context);
            PageDataBean<GoodsSuperBean> goodsSuperBeanPageDataBean = goodsSuperBeanHttpHelper.AnalyticalPageData(pageBeanResource);
            if (goodsSuperBeanPageDataBean != null) {
                rows = goodsSuperBeanPageDataBean.getRows();
                goodsParentAdapter.setList(rows);
                //获取第一个分类的商品
                setListView(rows.get(0).getId());
                goodsParentAdapter.setSelect(0);
            }
        });
//        if (context != null) {
//            showSpecificationHelper = new ShowSpecificationHelper(context, this);
//        }
    }

    @NonNull
    @Override
    protected GoodsAdapter setAdapter(Function0 reTry) {
        GoodsAdapter goodsAdapter = new GoodsAdapter(reTry);
        goodsAdapter.setOnItemClick((view, ItemObject, position) -> skipTo(GoodsDetailsActivity.class, ItemObject.getId()));
        goodsAdapter.setOnAddClick((view, ItemObject, position) -> {
//            showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), null, ItemObject.getId());
            getSpecification(ItemObject);
        });
        return goodsAdapter;
    }

    private void getSpecification(GoodsListRowsBean ItemObject) {
        mViewModel.getObjectFeatureItemList(ItemObject.getId()).observe(this, specificationsBeanResource -> {
            if (specificationsBeanResource.isSuccess()) {
                SpecificationsBean resource = specificationsBeanResource.getResource();
                if (resource != null && resource.getHeader().getCode() == 0) {
                    List<SpecificationsBean.BodyBean.DataBean> data = resource.getBody().getData();
                    if (data.size() == 0) {
                        int qty = 1;
                        LiveData<Resource<Bean<String>>> addGoodsShopToCart = mViewModel.addGoodsShopToCart(ItemObject.getId(), qty, null);
                        if (addGoodsShopToCart != null) {
                            addGoodsShopToCart.observe(this, beanResource -> {
                                BodyBean<String> addToShopCartBean = new HttpHelper<String>(getContext()).AnalyticalDataBody(beanResource);
                                addBadge(addToShopCartBean.getQty());
                            });
                        }

                    }
//                    else {
//                        SpecificationsBean.BodyBean.DataBean dataBean = data.get(0);
//                        List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList = dataBean.getItemList();
//                        showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), itemList, ItemObject.getId());
//                    }

                } else {
                    if (resource != null) {
                        toast(resource.getHeader().getMsg());
                    }
                }
            } else {
                Throwable error = specificationsBeanResource.getError();
                if (error != null) {
                    toast(error.toString());
                }
            }
        });
    }

    /**
     * 添加小红点
     *
     * @param qty 数量
     */
    private void addBadge(int qty) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            int badgeNumber = mainActivity.badge.getBadgeNumber();
            mainActivity.badge.setBadgeNumber(badgeNumber + qty);
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
    protected SortFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(SortFragmentVM.class);
    }

    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

//    @Override
//    public void callBack(String objectID, int qty, String objectFeatureItemID) {
//        LiveData<Resource<Bean<String>>> resourceLiveData = mViewModel.addGoodsShopToCart(objectID, qty, objectFeatureItemID);
//        if (resourceLiveData != null) {
//            resourceLiveData.observe(this, beanResource -> {
//                BodyBean<String> stringBodyBean = new HttpHelper<String>(getContext()).AnalyticalDataBody(beanResource);
//                if (stringBodyBean != null) {
//                    addBadge(stringBodyBean.getQty());
//                }
//            });
//        }
//    }

//    @Override
//    public void getPrice(String objectFeatureItemID) {
//        mViewModel.getPrice(objectFeatureItemID).observe(this, beanResource -> {
//            PriceBean priceBean = new HttpHelper<PriceBean>(getContext()).AnalyticalData(beanResource);
//            showSpecificationHelper.setPrice(priceBean.getShowPrice());
//        });
//    }
}
