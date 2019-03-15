package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.Subjects;
import com.jinkan.www.fastandroid.model.repository.Listing;
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
public class MovieDataSourceFactory extends DataSource.Factory<String, Subjects> {
    public final MutableLiveData<MoviePageKeyedDataSource> sourceLiveData = new MutableLiveData<>();
    @Inject
    ApiService apiService;
    @Inject
    Listing<Subjects> listing;
    @Inject
    public MovieDataSourceFactory() {
    }

    @NonNull
    @Override
    public DataSource<String, Subjects> create() {
        MoviePageKeyedDataSource moviePageKeyedDataSource = new MoviePageKeyedDataSource(listing, apiService);
        sourceLiveData.postValue(moviePageKeyedDataSource);
        return moviePageKeyedDataSource;
    }


}
