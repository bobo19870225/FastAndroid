package com.jinkan.www.fastandroid.model.repository.http.by_page;

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
//        GoodsPageKeyedDataSource goodsPageKeyedDataSource = new GoodsPageKeyedDataSource(listing, apiService);
        dataSourceMutableLiveData.postValue(keyValueDataSource);
        return keyValueDataSource;
    }

    protected abstract DataSource<Key, Value> setDataSource();


}
