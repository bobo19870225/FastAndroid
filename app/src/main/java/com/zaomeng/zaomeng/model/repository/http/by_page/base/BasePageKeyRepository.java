package com.zaomeng.zaomeng.model.repository.http.by_page.base;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.PostRepository;
import com.zaomeng.zaomeng.model.repository.http.ApiService;

import kotlin.Unit;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public abstract class BasePageKeyRepository<Key, Value> implements PostRepository<Value> {

    private Listing<Value> listing;
    private ApiService apiService;

    public BasePageKeyRepository(ApiService apiService, Listing<Value> listing) {
        this.apiService = apiService;
        this.listing = listing;
    }

    @Override
    @MainThread
    public Listing<Value> post(String[] sub, Integer pageSize) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(pageSize)                         //配置分页加载的数量
                .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(pageSize)              //初始化加载的数量
                .build();
        BaseDataSourceFactory<Key, Value> dataSourceFactory = setDataSourceFactory(apiService, listing, sub);
//        ShopCartDataSourceFactory goodsDataSourceFactory = new ShopCartDataSourceFactory(apiService, listing, sub[0], sub[1]);
        LiveData<PagedList<Value>> pagedListLiveData = new LivePagedListBuilder<>(dataSourceFactory, config).build();
        listing.setPagedList(pagedListLiveData);


        listing.refresh = () -> {
            DataSource<Key, Value> value = dataSourceFactory.dataSourceMutableLiveData.getValue();
            if (value != null) {
                value.invalidate();
            }
            return Unit.INSTANCE;
        };
        listing.reTry = () -> {
            BasePageKeyedDataSource<Key, Value> value = (BasePageKeyedDataSource<Key, Value>) dataSourceFactory.dataSourceMutableLiveData.getValue();
            if (value != null) {
                value.reTry();
            }
            return Unit.INSTANCE;
        };
        return listing;
    }

    protected abstract BaseDataSourceFactory<Key, Value> setDataSourceFactory(ApiService apiService, Listing<Value> listing, String[] sub);


}
