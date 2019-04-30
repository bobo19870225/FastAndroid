package com.zaomeng.zaomeng.model.repository.http.by_page.base;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.PostRepository;

import kotlin.Unit;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class BasePageKeyRepository<Key, Value> implements PostRepository<Value> {

    //    private Listing<Value> listing;
    //    private ApiService apiService;
    private InterfacePageRepository<Key, Value> interfacePageRepository;

    public BasePageKeyRepository(InterfacePageRepository<Key, Value> interfacePageRepository) {
        this.interfacePageRepository = interfacePageRepository;
    }

    @Override
    @MainThread
    public Listing<Value> post(Integer pageSize) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(pageSize)                         //配置分页加载的数量
                .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(pageSize)              //初始化加载的数量
                .build();
//        BaseDataSourceFactory<Key, Value> dataSourceFactory = setDataSourceFactory( listing, sub);
        Listing<Value> listing = new Listing<>();
        BaseDataSourceFactory<Key, Value> baseDataSourceFactory = new BaseDataSourceFactory<>(interfacePageRepository, listing);

        LiveData<PagedList<Value>> pagedListLiveData = new LivePagedListBuilder<>(baseDataSourceFactory, config).build();
        listing.setPagedList(pagedListLiveData);


        listing.refresh = () -> {
            DataSource<Key, Value> value = baseDataSourceFactory.dataSourceMutableLiveData.getValue();
            if (value != null) {
                value.invalidate();
            }
            return Unit.INSTANCE;
        };
        listing.reTry = () -> {
            BasePageKeyedDataSource<Key, Value> value = (BasePageKeyedDataSource<Key, Value>) baseDataSourceFactory.dataSourceMutableLiveData.getValue();
            if (value != null) {
                value.reTry();
            }
            return Unit.INSTANCE;
        };
        return listing;
    }

//    protected abstract BaseDataSourceFactory<Key, Value> setDataSourceFactory(ApiService apiService, Listing<Value> listing, String[] sub);


}
