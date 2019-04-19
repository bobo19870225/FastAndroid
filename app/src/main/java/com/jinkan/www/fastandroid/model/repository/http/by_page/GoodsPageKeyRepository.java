package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.PostRepository;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsListRowsBean;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import kotlin.Unit;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class GoodsPageKeyRepository implements PostRepository<GoodsListRowsBean> {

    private Listing<GoodsListRowsBean> goodsListing;
    private ApiService apiService;

    public GoodsPageKeyRepository(ApiService apiService, Listing<GoodsListRowsBean> goodsListing) {
        this.apiService = apiService;
        this.goodsListing = goodsListing;
    }

    @Override
    @MainThread
    @SuppressWarnings("unchecked")
    public Listing<GoodsListRowsBean> post(String[] sub, Integer pageSize) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)                         //配置分页加载的数量
                .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(10)              //初始化加载的数量
                .build();
        GoodsDataSourceFactory goodsDataSourceFactory = new GoodsDataSourceFactory(apiService, goodsListing, sub[0], sub[1]);
        LiveData<PagedList<GoodsListRowsBean>> pagedListLiveData = new LivePagedListBuilder<>(goodsDataSourceFactory, config).build();
        goodsListing.setPagedList(pagedListLiveData);

        goodsListing.refresh = () -> {
            GoodsPageKeyedDataSource value = (GoodsPageKeyedDataSource) goodsDataSourceFactory.dataSourceMutableLiveData.getValue();
            if (value != null) {
                value.invalidate();
            }
            return Unit.INSTANCE;
        };
        goodsListing.reTry = () -> {
            GoodsPageKeyedDataSource value = (GoodsPageKeyedDataSource) goodsDataSourceFactory.dataSourceMutableLiveData.getValue();
            if (value != null) {
                value.reTry();
            }
            return Unit.INSTANCE;
        };
        return goodsListing;
    }


}
