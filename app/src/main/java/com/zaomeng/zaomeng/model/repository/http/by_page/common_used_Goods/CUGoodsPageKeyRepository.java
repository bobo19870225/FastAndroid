package com.zaomeng.zaomeng.model.repository.http.by_page.common_used_Goods;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyRepository;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class CUGoodsPageKeyRepository extends BasePageKeyRepository<Integer, CollectInfoBean> {

    public CUGoodsPageKeyRepository(ApiService apiService, Listing<CollectInfoBean> goodsListing) {
        super(apiService, goodsListing);

    }

    @Override
    protected BaseDataSourceFactory<Integer, CollectInfoBean> setDataSourceFactory(ApiService apiService, Listing<CollectInfoBean> listing, String[] sub) {
        return new CUGoodsDataSourceFactory(apiService, listing, sub[0], sub[1]);
    }


}