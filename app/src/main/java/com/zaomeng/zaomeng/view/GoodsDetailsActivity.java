package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.youth.banner.Banner;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityGoodsDetailsBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.utils.GlideImageLoader;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.GoodsDetailsVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class GoodsDetailsActivity extends MVVMActivity<GoodsDetailsVM, ActivityGoodsDetailsBinding> {

    @Inject
    ViewModelFactory viewModelFactory;
    private String goodsName;
    private String goodsId;
    @NonNull
    @Override
    protected GoodsDetailsVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(GoodsDetailsVM.class);
    }

    @Override
    protected void setView() {

        mViewModel.action.observe(this, s -> {
            if ("back".equals(s)) {
                finish();
            } else if ("addCollect".equals(s)) {
                if (goodsId != null && goodsName != null)
                    mViewModel.addCollect(goodsId, goodsName).observe(this, beanResource -> {
                        HttpHelper<CollectBean> httpHelper = new HttpHelper<>(getApplicationContext());
                        CollectBean collectBean = httpHelper.AnalyticalData(beanResource);
                        toast("收藏成功");
                    });
            }
        });
        Banner banner = mViewDataBinding.banner;
        banner.setImageLoader(new GlideImageLoader());
        mViewModel.ldGoodsDetails.observe(this, beanResource -> {
            if (beanResource.isSuccess()) {
                Bean<GoodsDetailsBean> goodsDetailsBeanBean = beanResource.getResource();
                if (goodsDetailsBeanBean != null) {
                    if (goodsDetailsBeanBean.getHeader().getCode() == 0) {
                        GoodsDetailsBean goodsDetailsBean = goodsDetailsBeanBean.getBody().getData();
                        mViewModel.ldShowName.setValue(goodsDetailsBean.getShowName());
                        goodsName = goodsDetailsBean.getName();
                        goodsId = goodsDetailsBean.getId();
                        double showPrice = goodsDetailsBean.getShowPrice();
                        mViewModel.ldShowPrice.setValue(FormatUtils.numberFormatMoney(showPrice));
                        mViewModel.ldDescribe.setValue(goodsDetailsBean.getDescription());
                        List<String> imageURL = new ArrayList<>();
                        imageURL.add(goodsDetailsBean.getLargerImage());
                        banner.setImages(imageURL);
                        banner.start();
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
