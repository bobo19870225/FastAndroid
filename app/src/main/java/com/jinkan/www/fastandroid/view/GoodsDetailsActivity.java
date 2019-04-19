package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.ActivityGoodsDetailsBinding;
import com.jinkan.www.fastandroid.model.repository.http.bean.Bean;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsDetailsBean;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.Resource;
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
import androidx.lifecycle.Observer;
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
        mViewModel.ldGoodsDetails.observe(this, new Observer<Resource<Bean<GoodsDetailsBean>>>() {
            @Override
            public void onChanged(Resource<Bean<GoodsDetailsBean>> beanResource) {
                if (beanResource.isSuccess()) {
                    Bean<GoodsDetailsBean> goodsDetailsBeanBean = beanResource.getResource();
                    if (goodsDetailsBeanBean != null) {
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
                    }
                } else {
                    Throwable error = beanResource.getError();
                    if (error != null) {
                        toast(error.toString());
                    }

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
