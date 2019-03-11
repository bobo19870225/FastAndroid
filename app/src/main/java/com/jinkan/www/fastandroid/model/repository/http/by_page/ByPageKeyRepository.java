package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.Movie;
import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.PostRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * Created by Sampson on 2019/3/4.
 * FastAndroid
 */
@Singleton
public class ByPageKeyRepository implements PostRepository {

    @Inject
    public ByPageKeyRepository() {
    }


    //    @Inject
//    MoviePageKeyedDataSource moviePageKeyedDataSource;
    @Inject
    MovieDataSourceFactory movieDataSourceFactory;

    @Override
    @MainThread
    @SuppressWarnings("unchecked")
    public Listing<Movie> post(String sub, Integer pageSize) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(10)                         //配置分页加载的数量
                .setEnablePlaceholders(false)     //配置是否启动PlaceHolders
                .setInitialLoadSizeHint(10)              //初始化加载的数量
                .build();

        LiveData<PagedList<Movie>> pagedListLiveData = new LivePagedListBuilder<>(movieDataSourceFactory, config).build();
        Listing<Movie> movieListing = new Listing<>();
        movieListing.setPagedList(pagedListLiveData);
        return movieListing;
    }
}
