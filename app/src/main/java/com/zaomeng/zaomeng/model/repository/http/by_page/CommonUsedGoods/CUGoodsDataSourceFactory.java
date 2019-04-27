package com.zaomeng.zaomeng.model.repository.http.by_page.CommonUsedGoods;

import androidx.paging.DataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class CUGoodsDataSourceFactory extends BaseDataSourceFactory<Integer, CollectInfoBean> {
    private ApiService apiService;
    private Listing<CollectInfoBean> listing;
    private String sessionID;
    private String objectDefineID;

    CUGoodsDataSourceFactory(ApiService apiService,
                             Listing<CollectInfoBean> listing,
                             String sessionID,
                             String objectDefineID) {
        this.apiService = apiService;
        this.listing = listing;
        this.sessionID = sessionID;
        this.objectDefineID = objectDefineID;
    }


    @Override
    protected DataSource<Integer, CollectInfoBean> setDataSource() {
        return new CUGoodsPageKeyedDataSource(listing, apiService, sessionID, objectDefineID);
    }


}
