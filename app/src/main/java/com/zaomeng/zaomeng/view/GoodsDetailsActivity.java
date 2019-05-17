package com.zaomeng.zaomeng.view;

import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityGoodsDetailsBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsHeaderBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsImageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.utils.specification.InterfaceShowSpecification;
import com.zaomeng.zaomeng.utils.specification.ShowSpecificationHelper;
import com.zaomeng.zaomeng.view.adapter.goods_details.GoodsDetailsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.GoodsDetailsVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class GoodsDetailsActivity extends MVVMListActivity<GoodsDetailsVM, ActivityGoodsDetailsBinding, GoodsDetailsAdapter> implements InterfaceShowSpecification {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper<ShopCartBean> httpHelper;
    private ShowSpecificationHelper showSpecificationHelper;
    private String goodsName;
    private String goodsId;
    private GoodsDetailsAdapter goodsDetailsAdapter;
    private GoodsDetailsHeaderBean goodsDetailsHeaderBean;
    private Badge badge;
    @NonNull
    @Override
    protected GoodsDetailsVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(GoodsDetailsVM.class);
    }

    @Override
    protected void setView() {
        super.setView();
        showSpecificationHelper = new ShowSpecificationHelper(this, this);
        badge = new QBadgeView(this).bindTarget(mViewDataBinding.shopCar)
                .setShowShadow(false)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setGravityOffset(-2, 0, true)
//                .setOnDragStateChangedListener(null)
                .setBadgeNumber(0);
        mViewModel.getCartGoodsListLD().observe(this, pageBeanResource -> {
            PageDataBean<ShopCartBean> goodsPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource);
            if (goodsPageDataBean != null) {
                int total = goodsPageDataBean.getTotal();
                badge.setBadgeNumber(total);
            }
        });


        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "back":
                    finish();
                    break;
                case "addCollect":
                    if (goodsId != null && goodsName != null)
                        mViewModel.addCollect(goodsId, goodsName).observe(this, beanResource -> {
                            HttpHelper<String> httpHelper = new HttpHelper<>(getApplicationContext());
                            String collectBean = httpHelper.AnalyticalData(beanResource);
                            if (collectBean != null) {
                                mViewDataBinding.collect.setImageResource(R.mipmap.collect);
                                toast("收藏成功");
                            }
                        });
                    break;
                case "shopCar":
                    skipTo(MainActivity.class, 3, true);
                    break;
                case "addToShopCar":
                    showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), null, goodsId);
                    mViewModel.getObjectFeatureItemList(goodsId).observe(this, specificationsBeanResource -> {
                        if (specificationsBeanResource.isSuccess()) {
                            SpecificationsBean resource = specificationsBeanResource.getResource();
                            if (resource != null && resource.getHeader().getCode() == 0) {
                                List<SpecificationsBean.BodyBean.DataBean> data = resource.getBody().getData();
                                if (data.size() == 0) {
                                    int qty = 1;
                                    LiveData<Resource<Bean<String>>> addGoodsShopToCart = mViewModel.addGoodsToCart(goodsId, qty, null);
                                    if (addGoodsShopToCart != null) {
                                        addGoodsShopToCart.observe(this, beanResource -> {
                                            BodyBean<String> addToShopCartBean = new HttpHelper<String>(getApplicationContext()).AnalyticalDataBody(beanResource);
                                            badge.setBadgeNumber(badge.getBadgeNumber() + addToShopCartBean.getQty());
                                        });
                                    }

                                } else {
                                    SpecificationsBean.BodyBean.DataBean dataBean = data.get(0);
                                    List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList = dataBean.getItemList();
                                    showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), itemList, goodsId);
                                }

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
                    break;
            }

        });
        mViewModel.ldGoodsDetails.observe(this, beanResource -> {
            if (beanResource.isSuccess()) {
                Bean<GoodsDetailsBean> goodsDetailsBeanBean = beanResource.getResource();
                if (goodsDetailsBeanBean != null) {
                    if (goodsDetailsBeanBean.getHeader().getCode() == 0) {
                        GoodsDetailsBean goodsDetailsBean = goodsDetailsBeanBean.getBody().getData();
                        setListView(goodsDetailsBean.getId());
//                        mViewModel.ldShowName.setValue(goodsDetailsBean.getShowName());
                        goodsName = goodsDetailsBean.getName();
                        goodsId = goodsDetailsBean.getId();
                        goodsDetailsHeaderBean = new GoodsDetailsHeaderBean();
                        goodsDetailsHeaderBean.setGoodsName(goodsDetailsBean.getShowName());
                        goodsDetailsHeaderBean.setPrice(goodsDetailsBean.getRealPrice());
                        goodsDetailsHeaderBean.setDescribe(goodsDetailsBean.getDescription());
                        getBannerImage();
                        if (goodsDetailsBean.getIsCollect() == 1) {
                            mViewDataBinding.collect.setImageResource(R.mipmap.collect);
                        } else {
                            mViewDataBinding.collect.setImageResource(R.mipmap.un_collect);
                        }

                    } else {
                        toast(goodsDetailsBeanBean.getHeader().getMsg());
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

    private void getBannerImage() {
        mViewModel.getBannerImageList(goodsId).observe(this, pageBeanResource -> {
            PageDataBean<GoodsDetailsImageBean> goodsDetailsImageBeanPageDataBean = new HttpHelper<GoodsDetailsImageBean>(getApplicationContext()).AnalyticalPageData(pageBeanResource);
            if (goodsDetailsImageBeanPageDataBean != null) {
                List<GoodsDetailsImageBean> rows = goodsDetailsImageBeanPageDataBean.getRows();
                List<String> imageURL = new ArrayList<>();
                for (GoodsDetailsImageBean g : rows
                ) {
                    imageURL.add(g.getUrl());
                }
                goodsDetailsHeaderBean.setListBannerURL(imageURL);
                goodsDetailsAdapter.setHeaderData(goodsDetailsHeaderBean);
                mViewDataBinding.list.scrollToPosition(0);
            }
        });
    }

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @NonNull
    @Override
    protected GoodsDetailsAdapter setAdapter(Function0 reTry) {
        goodsDetailsAdapter = new GoodsDetailsAdapter(reTry);

        return goodsDetailsAdapter;
    }

    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
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
        return R.layout.activity_goods_details;
    }

    @Override
    public void callBack(String objectID, int qty, String objectFeatureItemID) {
        if (goodsId != null) {
            mViewModel.addGoodsToCart(objectID, qty, objectFeatureItemID).observe(this, beanResource -> {
                String s = new HttpHelper<String>(getApplicationContext()).AnalyticalData(beanResource);
                if (s != null)
                    badge.setBadgeNumber(badge.getBadgeNumber() + qty);
            });
        }
    }
}
