package com.zaomeng.zaomeng.model.repository.http.by_page.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.zaomeng.zaomeng.model.repository.Listing;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public class BaseDataSourceFactory<Key, Value> extends DataSource.Factory<Key, Value> {
    public final MutableLiveData<DataSource<Key, Value>> dataSourceMutableLiveData = new MutableLiveData<>();
    private InterfacePageRepository<Key, Value> interfacePageRepository;
    private Listing<Value> listing;

    public BaseDataSourceFactory(InterfacePageRepository<Key, Value> interfacePageRepository, Listing<Value> listing) {
        this.interfacePageRepository = interfacePageRepository;
        this.listing = listing;
    }

    @NonNull
    @Override
    public final DataSource<Key, Value> create() {
//        DataSource<Key, Value> keyValueDataSource = setDataSource();
        BasePageKeyedDataSource<Key, Value> basePageKeyedDataSource = new BasePageKeyedDataSource<>(interfacePageRepository, listing);
        dataSourceMutableLiveData.postValue(basePageKeyedDataSource);
        return basePageKeyedDataSource;
    }

//    protected abstract DataSource<Key, Value> setDataSource();


}
