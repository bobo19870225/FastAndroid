package com.zaomeng.zaomeng.model.repository.http.by_page.branch_goods;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyRepository;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class BranchGoodsPageKeyRepository extends BasePageKeyRepository<Integer, BranchGoodsBean> {

    public BranchGoodsPageKeyRepository(ApiService apiService, Listing<BranchGoodsBean> goodsListing) {
        super(apiService, goodsListing);

    }

    @Override
    protected BaseDataSourceFactory<Integer, BranchGoodsBean> setDataSourceFactory(ApiService apiService, Listing<BranchGoodsBean> listing, String[] sub) {
        return new BranchGoodsDataSourceFactory(apiService, listing, sub[0], sub[1]);
    }


}
