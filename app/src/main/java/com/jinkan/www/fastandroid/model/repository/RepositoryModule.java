package com.jinkan.www.fastandroid.model.repository;

import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.by_page.GoodsPageKeyRepository;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.LiveDataCallAdapterFactory;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.LiveDataResponseBodyConverterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jinkan.www.fastandroid.utils.SystemParameter.baseUrl;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    static ApiService provideApiService() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())//有顺序的
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .build().create(ApiService.class);
    }


    @Singleton
    @Provides
    static GoodsPageKeyRepository byPageKeyRepository(ApiService apiService, Listing<GoodsListRowsBean> listing) {
        return new GoodsPageKeyRepository(apiService, listing);
    }
}
