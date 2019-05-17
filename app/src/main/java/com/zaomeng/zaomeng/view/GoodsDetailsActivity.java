package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityGoodsDetailsBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsHeaderBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsImageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.adapter.goods_details.GoodsDetailsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.GoodsDetailsVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class GoodsDetailsActivity extends MVVMListActivity<GoodsDetailsVM, ActivityGoodsDetailsBinding, GoodsDetailsAdapter> {

    @Inject
    ViewModelFactory viewModelFactory;
    private String goodsName;
    private String goodsId;
    private GoodsDetailsAdapter goodsDetailsAdapter;
    private GoodsDetailsHeaderBean goodsDetailsHeaderBean;
    @NonNull
    @Override
    protected GoodsDetailsVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(GoodsDetailsVM.class);
    }

    @Override
    protected void setView() {
        super.setView();
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
}
