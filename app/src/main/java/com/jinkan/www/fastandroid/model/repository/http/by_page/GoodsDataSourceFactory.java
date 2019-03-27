package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;

import androidx.paging.DataSource;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class GoodsDataSourceFactory extends BaseDataSourceFactory<Integer, Goods> {
    private ApiService apiService;
    private Listing<Goods> listing;
    private String token;

    public GoodsDataSourceFactory(ApiService apiService, Listing<Goods> listing, String token) {
        this.apiService = apiService;
        this.listing = listing;
        this.token = token;
    }


    @Override
    protected DataSource<Integer, Goods> setDataSource() {
        return new GoodsPageKeyedDataSource(listing, apiService, token);
    }


}
