package com.zaomeng.zaomeng.view;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityOrderDetailBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.OrderBean;
import com.zaomeng.zaomeng.utils.FormatUtils;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.OrderDetailVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-17.
 * FastAndroid
 */
public class OrderDetailActivity extends MVVMActivity<OrderDetailVM, ActivityOrderDetailBinding> {
    @Inject
    ViewModelFactory viewModelFactory;

    @NonNull
    @Override
    protected OrderDetailVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(OrderDetailVM.class);
    }

    @Override
    protected void setView() {
        mViewModel.getMemberOrderDetail((String) transferData).observe(this, beanResource -> {
            OrderBean orderBean = new HttpHelper<OrderBean>(getApplicationContext()).AnalyticalData(beanResource);
            if (orderBean != null) {
                mViewModel.ldOrderNo.setValue(orderBean.getOrderCode());
                mViewModel.ldTime.setValue(orderBean.getApplyTimeStr());
                mViewModel.ldOrderNumber.setValue("共计" + orderBean.getGoodsNumbers() + "件商品");
//mViewModel.ldBonus.setValue(orderBean.get);
                mViewModel.ldDiscount.setValue(FormatUtils.numberFormatMoneyString(orderBean.getPriceStandTotal() - orderBean.getPriceTotal()));
                mViewModel.ldTotal.setValue(FormatUtils.numberFormatMoneyString(orderBean.getPriceTotal()));
                mViewModel.ldAddress.setValue(orderBean.getAddress());
                mViewModel.ldUserName.setValue(orderBean.getContactName());
                mViewModel.ldUserPhone.setValue(orderBean.getContactPhone());
                List<OrderBean.GoodsListBean> goodsList = orderBean.getGoodsList();
                setGoodsList(goodsList);
            }
        });
        mViewModel.action.observe(this, s -> {
            if (s.equals("goodsDetail")) {

            }
        });
    }

    private void setGoodsList(List<OrderBean.GoodsListBean> goodsList) {
        RecyclerView listOrder = mViewDataBinding.listOrder;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        listOrder.setLayoutManager(linearLayoutManager);
        listOrder.setAdapter(new CommonAdapter<OrderBean.GoodsListBean>(getApplicationContext(), R.layout.item_image_small, goodsList) {
            @Override
            protected void convert(ViewHolder holder, OrderBean.GoodsListBean goodsListBean, int position) {
                ImageView image = holder.itemView.findViewById(R.id.image);
                Glide.with(image).load(goodsListBean.getListImage()).into(image);
            }
        });
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "订单详情";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_order_detail;
    }
}
