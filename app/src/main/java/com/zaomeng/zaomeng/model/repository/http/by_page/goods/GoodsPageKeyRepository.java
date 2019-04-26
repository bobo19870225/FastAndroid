package com.zaomeng.zaomeng.model.repository.http.by_page.goods;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BaseDataSourceFactory;
import com.zaomeng.zaomeng.model.repository.http.by_page.base.BasePageKeyRepository;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class GoodsPageKeyRepository extends BasePageKeyRepository<Integer, GoodsListRowsBean> {

    public GoodsPageKeyRepository(ApiService apiService, Listing<GoodsListRowsBean> goodsListing) {
        super(apiService, goodsListing);

    }

    @Override
    protected BaseDataSourceFactory<Integer, GoodsListRowsBean> setDataSourceFactory(ApiService apiService, Listing<GoodsListRowsBean> listing, String[] sub) {
        return new GoodsDataSourceFactory(apiService, listing, sub[0], sub[1]);
    }


}
