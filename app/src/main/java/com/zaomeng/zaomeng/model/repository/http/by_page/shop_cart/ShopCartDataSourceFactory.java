package com.zaomeng.zaomeng.model.repository.http.by_page.shop_cart;

import androidx.paging.DataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class ShopCartDataSourceFactory extends BaseDataSourceFactory<Integer, ShopCartBean> {
    private ApiService apiService;
    private Listing<ShopCartBean> listing;
    private String sessionID;

    ShopCartDataSourceFactory(ApiService apiService,
                              Listing<ShopCartBean> listing,
                              String sessionID) {
        this.apiService = apiService;
        this.listing = listing;
        this.sessionID = sessionID;
    }


    @Override
    protected DataSource<Integer, ShopCartBean> setDataSource() {
        return new ShopCartPageKeyedDataSource(listing, apiService, sessionID);
    }


}
