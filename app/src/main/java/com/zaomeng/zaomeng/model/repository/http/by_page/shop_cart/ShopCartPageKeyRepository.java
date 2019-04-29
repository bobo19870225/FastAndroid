package com.zaomeng.zaomeng.model.repository.http.by_page.shop_cart;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyRepository;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class ShopCartPageKeyRepository extends BasePageKeyRepository<Integer, ShopCartBean> {

    public ShopCartPageKeyRepository(ApiService apiService, Listing<ShopCartBean> goodsListing) {
        super(apiService, goodsListing);

    }

    @Override
    protected BaseDataSourceFactory<Integer, ShopCartBean> setDataSourceFactory(ApiService apiService, Listing<ShopCartBean> listing, String[] sub) {
        return new ShopCartDataSourceFactory(apiService, listing, sub[0]);
    }


}
