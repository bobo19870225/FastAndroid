package com.zaomeng.zaomeng.view;

import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityGoodsDetailsBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsHeaderBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsImageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PriceBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
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
    HttpHelper httpHelper;
    private ShowSpecificationHelper showSpecificationHelper;
    private String goodsName;
    private String goodsId;
    private GoodsDetailsAdapter goodsDetailsAdapter;
    private GoodsDetailsHeaderBean goodsDetailsHeaderBean;
    private Badge badge;
    private final MutableLiveData<Integer> ldIsCollect = new MutableLiveData<>();
    private String collectID;
    private double realPrice;

    @NonNull
    @Override
    protected GoodsDetailsVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(GoodsDetailsVM.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void setView() {
        super.setView();
        getGoodsDetail(false);
        showSpecificationHelper = new ShowSpecificationHelper(this, this);
        badge = new QBadgeView(this).bindTarget(mViewDataBinding.shopCar)
                .setShowShadow(false)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setGravityOffset(-2, 0, true)
//                .setOnDragStateChangedListener(null)
                .setBadgeNumber(0);
        mViewModel.getCartGoodsListLD().observe(this, pageBeanResource -> {
            PageDataBean<ShopCartBean> goodsPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource, new InterfaceLogin() {

                @Override
                public void skipLoginActivity() {
                    toLogin();
                }

                @Override
                public void reLoad() {
                    mViewModel.getCartGoodsListLD();
                }
            }, this);
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
                    if (goodsId != null && goodsName != null) {
                        Integer ldIsCollectValue = ldIsCollect.getValue();
                        if (ldIsCollectValue != null && ldIsCollectValue == 0) {
                            mViewModel.addCollect(goodsId, goodsName).observe(this, beanResource -> {
                                String collectBean = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                                    @Override
                                    public void skipLoginActivity() {
                                        toLogin();
                                    }

                                    @Override
                                    public void reLoad() {
                                        mViewModel.addCollect(goodsId, goodsName);
                                    }
                                }, this);
                                if (collectBean != null) {
                                    getGoodsDetail(true);
                                    toast("收藏成功");
                                }
                            });
                        } else {
                            mViewModel.removeCollect(collectID).observe(this, beanResource -> {
                                String collectBean = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                                    @Override
                                    public void skipLoginActivity() {
                                        toLogin();
                                    }

                                    @Override
                                    public void reLoad() {
                                        mViewModel.removeCollect(goodsId);
                                    }
                                }, this);
                                if (collectBean != null) {
                                    getGoodsDetail(true);
                                    toast("取消收藏");
                                }
                            });
                        }
                    }

                    break;
                case "shopCar":
                    skipTo(MainActivity.class, 3, true);
                    break;
                case "addToShopCar":
                    showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), null, goodsId, realPrice, true);
                    mViewModel.getObjectFeatureItemList(goodsId).observe(this, specificationsBeanResource -> {
                        if (specificationsBeanResource.isSuccess()) {
                            SpecificationsBean resource = specificationsBeanResource.getResource();
                            if (resource != null && resource.getHeader().getCode() == 0) {
                                List<SpecificationsBean.BodyBean.DataBean> data = resource.getBody().getData();
                                if (data.size() == 0) {
                                    showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), null, goodsId, realPrice, false);
                                } else {
                                    SpecificationsBean.BodyBean.DataBean dataBean = data.get(0);
                                    List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList = dataBean.getItemList();
                                    showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), itemList, goodsId, realPrice, false);
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
        ldIsCollect.observe(this, integer -> {
            if (integer == 1) {
                mViewDataBinding.collect.setImageResource(R.mipmap.collect);
            } else {
                mViewDataBinding.collect.setImageResource(R.mipmap.un_collect);
            }
        });
    }

    private void getGoodsDetail(boolean isForCollect) {
        mViewModel.getGoodsShopDetail((String) transferData).observe(this, beanResource -> {
            GoodsDetailsBean goodsDetailsBean = (GoodsDetailsBean) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {

                }
            }, this);
            if (goodsDetailsBean != null) {
                ldIsCollect.setValue(goodsDetailsBean.getIsCollect());
                collectID = goodsDetailsBean.getCollectID();
                if (!isForCollect) {
                    setListView(goodsDetailsBean.getId());
                    goodsName = goodsDetailsBean.getName();
                    goodsId = goodsDetailsBean.getId();
                    goodsDetailsHeaderBean = new GoodsDetailsHeaderBean();
                    goodsDetailsHeaderBean.setGoodsName(goodsDetailsBean.getShowName());
                    realPrice = goodsDetailsBean.getRealPrice();
                    goodsDetailsHeaderBean.setPrice(realPrice);
                    goodsDetailsHeaderBean.setDescribe(goodsDetailsBean.getDescription());
                    getBannerImage();
                }
            }
        });
    }

    private void toLogin() {
        skipTo(LoginActivity.class, null);
    }

    @SuppressWarnings("unchecked")
    private void getBannerImage() {
        mViewModel.getBannerImageList(goodsId).observe(this, pageBeanResource -> {
            PageDataBean<GoodsDetailsImageBean> goodsDetailsImageBeanPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    toLogin();
                }

                @Override
                public void reLoad() {
                    mViewModel.getBannerImageList(goodsId);
                }
            }, this);
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

    @SuppressWarnings("unchecked")
    @Override
    public void callBack(String objectID, int qty, String objectFeatureItemID) {
        if (goodsId != null) {
            mViewModel.addGoodsToCart(objectID, qty, objectFeatureItemID).observe(this, beanResource -> {
                String s = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                    @Override
                    public void skipLoginActivity() {
                        toLogin();
                    }

                    @Override
                    public void reLoad() {
//                        mViewModel.addCollect(goodsId, goodsName);
                    }
                }, this);
                if (s != null)
                    badge.setBadgeNumber(badge.getBadgeNumber() + qty);
            });
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getPrice(String goodsID, String objectFeatureItemID) {
        mViewModel.getPrice(goodsID, objectFeatureItemID).observe(this, beanResource -> {
            PriceBean priceBean = (PriceBean) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    toLogin();
                }

                @Override
                public void reLoad() {
                    mViewModel.getPrice(goodsID, objectFeatureItemID);
                }
            }, this);
            showSpecificationHelper.setPrice(priceBean.getShowPrice());
        });
    }

}
