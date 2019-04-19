package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.ActivityGoodsDetailsBinding;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsDetailsBean;
import com.jinkan.www.fastandroid.utils.FormatUtils;
import com.jinkan.www.fastandroid.utils.GlideImageLoader;
import com.jinkan.www.fastandroid.view.base.MVVMActivity;
import com.jinkan.www.fastandroid.view_model.GoodsDetailsVM;
import com.jinkan.www.fastandroid.view_model.ViewModelFactory;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class GoodsDetailsActivity extends MVVMActivity<GoodsDetailsVM, ActivityGoodsDetailsBinding> {

    @Inject
    ViewModelFactory viewModelFactory;
    @NonNull
    @Override
    protected GoodsDetailsVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(GoodsDetailsVM.class);
    }

    @Override
    protected void setView() {
        Banner banner = mViewDataBinding.banner;
        banner.setImageLoader(new GlideImageLoader());
        mViewModel.ldGoodsDetails.observe(this, goodsDetailsBeanBean -> {
            if (goodsDetailsBeanBean.getHeader().getCode() == 0) {
                GoodsDetailsBean goodsDetailsBean = (GoodsDetailsBean) goodsDetailsBeanBean.getBody().getData();
                mViewModel.ldShowName.setValue(goodsDetailsBean.getShowName());
                double showPrice = goodsDetailsBean.getShowPrice();
                mViewModel.ldShowPrice.setValue(FormatUtils.numberFormatMoney(showPrice));
                List<String> imageURL = new ArrayList<>();
                imageURL.add(goodsDetailsBean.getLargerImage());
                banner.setImages(imageURL);
                banner.start();
            } else {
                toast(goodsDetailsBeanBean.getHeader().getMsg());
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
