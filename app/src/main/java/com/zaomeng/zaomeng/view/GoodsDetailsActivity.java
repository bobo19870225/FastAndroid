package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityGoodsDetailsBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.adapter.goods_details.GoodsDetailsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.GoodsDetailsVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

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
    @NonNull
    @Override
    protected GoodsDetailsVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(GoodsDetailsVM.class);
    }

    @Override
    protected void setView() {
        super.setView();
        mViewModel.action.observe(this, s -> {
            if ("back".equals(s)) {
                finish();
            } else if ("addCollect".equals(s)) {
                if (goodsId != null && goodsName != null)
                    mViewModel.addCollect(goodsId, goodsName).observe(this, beanResource -> {
                        HttpHelper<String> httpHelper = new HttpHelper<>(getApplicationContext());
                        String collectBean = httpHelper.AnalyticalData(beanResource);
                        if (collectBean != null) toast("收藏成功");
                    });
            }
        });
//        Banner banner = mViewDataBinding.banner;
//        banner.setImageLoader(new GlideImageLoader());
        mViewModel.ldGoodsDetails.observe(this, beanResource -> {
            if (beanResource.isSuccess()) {
                Bean<GoodsDetailsBean> goodsDetailsBeanBean = beanResource.getResource();
                if (goodsDetailsBeanBean != null) {
                    if (goodsDetailsBeanBean.getHeader().getCode() == 0) {
                        GoodsDetailsBean goodsDetailsBean = goodsDetailsBeanBean.getBody().getData();
                        setListView(goodsDetailsBean.getId());
                        mViewModel.ldShowName.setValue(goodsDetailsBean.getShowName());
                        goodsName = goodsDetailsBean.getName();
                        goodsId = goodsDetailsBean.getId();
                        double showPrice = goodsDetailsBean.getRealPrice();
                        mViewModel.ldShowPrice.setValue(FormatUtils.numberFormatMoney(showPrice));
                        mViewModel.ldDescribe.setValue(goodsDetailsBean.getDescription());
//                        List<String> imageURL = new ArrayList<>();
//                        imageURL.add(goodsDetailsBean.getLargerImage());
//                        banner.setImages(imageURL);
//                        banner.start();
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

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @NonNull
    @Override
    protected GoodsDetailsAdapter setAdapter(Function0 reTry) {
        return new GoodsDetailsAdapter(reTry);
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
