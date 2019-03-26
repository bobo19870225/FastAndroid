package com.jinkan.www.fastandroid.model.repository;

import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.LiveDataCallAdapterFactory;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.LiveDataResponseBodyConverterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jinkan.www.fastandroid.SystemParameter.baseUrl;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */
@Module
public abstract class RepositoryModule {

    @Singleton
    @Provides
    static ApiService provideApiService() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .build().create(ApiService.class);
    }

}
