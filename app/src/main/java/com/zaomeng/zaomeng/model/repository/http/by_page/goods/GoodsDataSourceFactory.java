package com.zaomeng.zaomeng.model.repository.http.by_page.goods;

import androidx.paging.DataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class GoodsDataSourceFactory extends BaseDataSourceFactory<Integer, GoodsListRowsBean> {
    private ApiService apiService;
    private Listing<GoodsListRowsBean> listing;
    private String goodsCategoryID;
    private String memberID;

    public GoodsDataSourceFactory(ApiService apiService,
                                  Listing<GoodsListRowsBean> listing,
                                  String goodsCategoryID,
                                  String memberID) {
        this.apiService = apiService;
        this.listing = listing;
        this.goodsCategoryID = goodsCategoryID;
        this.memberID = memberID;
    }


    @Override
    protected DataSource<Integer, GoodsListRowsBean> setDataSource() {
        return new GoodsPageKeyedDataSource(listing, apiService, goodsCategoryID, memberID);
    }


}
