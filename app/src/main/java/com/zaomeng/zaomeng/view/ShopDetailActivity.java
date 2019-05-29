package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityShopDetailBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.MemberShopBean;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.ShopDetailVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-16.
 * FastAndroid
 */
public class ShopDetailActivity extends MVVMActivity<ShopDetailVM, ActivityShopDetailBinding> {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;

    @NonNull
    @Override
    protected ShopDetailVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(ShopDetailVM.class);
    }

    @Override
    protected void setView() {
        String memberShopID = (String) transferData;
        mViewModel.getMemberShopDetail(memberShopID).observe(this, beanResource -> {
            MemberShopBean memberShopBean = (MemberShopBean) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {

                }
            }, this);
            if (memberShopBean != null) {
                mViewModel.ldName.setValue(memberShopBean.getName());
                mViewModel.ldAddress.setValue(memberShopBean.getAddress());
                mViewModel.ldContact.setValue(memberShopBean.getContact());
                mViewModel.ldContactPhone.setValue(memberShopBean.getContactPhone());
                mViewModel.ldShopType.setValue(String.valueOf(memberShopBean.getShopType()));
                setImage(memberShopBean);
            }
        });
        mViewModel.action.observe(this, s -> finish());
    }

    private void setImage(MemberShopBean memberShopBean) {
        Glide.with(getApplicationContext()).load(memberShopBean.getShopFaceImage()).into(mViewDataBinding.imgShop);
        Glide.with(getApplicationContext()).load(memberShopBean.getBusinessImage()).into(mViewDataBinding.imgLicense);
        Glide.with(getApplicationContext()).load(memberShopBean.getContactIdCardFaceImage()).into(mViewDataBinding.imgIcFront);
        Glide.with(getApplicationContext()).load(memberShopBean.getContactIdCardBackImage()).into(mViewDataBinding.imgIcBack);
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "店铺详情";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_shop_detail;
    }
}
