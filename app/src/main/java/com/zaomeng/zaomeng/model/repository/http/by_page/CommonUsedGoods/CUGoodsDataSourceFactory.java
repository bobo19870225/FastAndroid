package com.zaomeng.zaomeng.model.repository.http.by_page.CommonUsedGoods;

import androidx.paging.DataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class CUGoodsDataSourceFactory extends BaseDataSourceFactory<Integer, GoodsListRowsBean> {
    private ApiService apiService;
    private Listing<GoodsListRowsBean> listing;
    private String sessionID;
    private String objectDefineID;

    CUGoodsDataSourceFactory(ApiService apiService,
                             Listing<GoodsListRowsBean> listing,
                             String sessionID,
                             String objectDefineID) {
        this.apiService = apiService;
        this.listing = listing;
        this.sessionID = sessionID;
        this.objectDefineID = objectDefineID;
    }


    @Override
    protected DataSource<Integer, GoodsListRowsBean> setDataSource() {
        return new CUGoodsPageKeyedDataSource(listing, apiService, sessionID, objectDefineID);
    }


}
