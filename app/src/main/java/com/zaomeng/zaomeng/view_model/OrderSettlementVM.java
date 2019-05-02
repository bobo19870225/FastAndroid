package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;

import java.util.List;

/**
 * Created by Sampson on 2019-05-02.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.OrderSettlementActivity}
 */
public class OrderSettlementVM extends BaseViewModel {
    private List<ShopCartBean> shopCartBeans;

    public OrderSettlementVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void init(Object data) {
        shopCartBeans = (List<ShopCartBean>) data;
        String s = "test";
    }
}
