package com.zaomeng.zaomeng.model.repository.http.by_page.CommonUsedGoods;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyRepository;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class CUGoodsPageKeyRepository extends BasePageKeyRepository<Integer, GoodsListRowsBean> {

    public CUGoodsPageKeyRepository(ApiService apiService, Listing<GoodsListRowsBean> goodsListing) {
        super(apiService, goodsListing);

    }

    @Override
    protected BaseDataSourceFactory<Integer, GoodsListRowsBean> setDataSourceFactory(ApiService apiService, Listing<GoodsListRowsBean> listing, String[] sub) {
        return new CUGoodsDataSourceFactory(apiService, listing, sub[0], sub[1]);
    }


}
