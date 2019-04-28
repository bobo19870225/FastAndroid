package com.zaomeng.zaomeng.model.repository.http.by_page.branch_goods;

import androidx.paging.DataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class BranchGoodsDataSourceFactory extends BaseDataSourceFactory<Integer, BranchGoodsBean> {
    private ApiService apiService;
    private Listing<BranchGoodsBean> listing;
    private String navigatorID;
    private String objectDefineID;

    BranchGoodsDataSourceFactory(ApiService apiService,
                                 Listing<BranchGoodsBean> listing,
                                 String navigatorID,
                                 String objectDefineID) {
        this.apiService = apiService;
        this.listing = listing;
        this.navigatorID = navigatorID;
        this.objectDefineID = objectDefineID;
    }


    @Override
    protected DataSource<Integer, BranchGoodsBean> setDataSource() {
        return new BranchGoodsPageKeyedDataSource(listing, apiService, navigatorID, objectDefineID);
    }


}
