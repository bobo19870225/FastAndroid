package com.jinkan.www.fastandroid.model.repository;

import com.jinkan.www.fastandroid.model.repository.http.ApiService;

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
                //适配RxJava2.0,RxJava1.x则为RxJavaCallAdapterFactory.create()
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiService.class);
    }

//    @Binds
//    abstract ListingCallBack provideListingCallBack(ByPageKeyRepository listingCallBack);
}
