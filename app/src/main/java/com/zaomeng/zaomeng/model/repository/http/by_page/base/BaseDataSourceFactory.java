package com.zaomeng.zaomeng.model.repository.http.by_page.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */

public abstract class BaseDataSourceFactory<Key, Value> extends DataSource.Factory<Key, Value> {
    public final MutableLiveData<DataSource<Key, Value>> dataSourceMutableLiveData = new MutableLiveData<>();


    @NonNull
    @Override
    public final DataSource<Key, Value> create() {
        DataSource<Key, Value> keyValueDataSource = setDataSource();
//        ShopCartPageKeyedDataSource goodsPageKeyedDataSource = new ShopCartPageKeyedDataSource(listing, apiService);
        dataSourceMutableLiveData.postValue(keyValueDataSource);
        return keyValueDataSource;
    }

    protected abstract DataSource<Key, Value> setDataSource();


}
