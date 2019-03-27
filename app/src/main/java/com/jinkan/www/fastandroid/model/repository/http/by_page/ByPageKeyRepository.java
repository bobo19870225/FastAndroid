package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.PostRepository;
import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import kotlin.Unit;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */
@Singleton
public class ByPageKeyRepository implements PostRepository<Goods> {

    @Inject
    public ByPageKeyRepository() {
    }


    @Inject
    MovieDataSourceFactory movieDataSourceFactory;
    @Inject
    Listing<Goods> movieListing;
    @Override
    @MainThread
    @SuppressWarnings("unchecked")
    public Listing<Goods> post(String sub, Integer pageSize) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)                         //配置分页加载的数量
                .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(10)              //初始化加载的数量
                .build();

        LiveData<PagedList<Goods>> pagedListLiveData = new LivePagedListBuilder<>(movieDataSourceFactory, config).build();
        movieListing.setPagedList(pagedListLiveData);
        movieListing.refresh = () -> {
            GoodsPageKeyedDataSource value = movieDataSourceFactory.sourceLiveData.getValue();
            if (value != null) {
                value.invalidate();
            }
            return Unit.INSTANCE;
        };
        movieListing.reTry = () -> {
            GoodsPageKeyedDataSource value = movieDataSourceFactory.sourceLiveData.getValue();
            if (value != null) {
                value.reTry();
            }
            return Unit.INSTANCE;
        };
        return movieListing;
    }


}
