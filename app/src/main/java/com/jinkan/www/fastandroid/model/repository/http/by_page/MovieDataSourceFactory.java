package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */
@Singleton
public class MovieDataSourceFactory extends DataSource.Factory<Integer, Goods> {
    public final MutableLiveData<GoodsPageKeyedDataSource> sourceLiveData = new MutableLiveData<>();
    @Inject
    ApiService apiService;
    @Inject
    Listing<Goods> listing;
    @Inject
    public MovieDataSourceFactory() {
    }

    @NonNull
    @Override
    public DataSource<Integer, Goods> create() {
        GoodsPageKeyedDataSource goodsPageKeyedDataSource = new GoodsPageKeyedDataSource(listing, apiService);
        sourceLiveData.postValue(goodsPageKeyedDataSource);
        return goodsPageKeyedDataSource;
    }


}
