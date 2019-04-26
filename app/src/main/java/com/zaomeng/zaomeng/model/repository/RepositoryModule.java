package com.zaomeng.zaomeng.model.repository;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.CommonUsedGoods.CUGoodsPageKeyRepository;
import com.zaomeng.zaomeng.model.repository.http.by_page.goods.GoodsPageKeyRepository;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.LiveDataCallAdapterFactory;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.LiveDataResponseBodyConverterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zaomeng.zaomeng.utils.SystemParameter.baseUrl;

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

    @Singleton
    @Provides
    static CUGoodsPageKeyRepository cuGoodsPageKeyRepository(ApiService apiService, Listing<GoodsListRowsBean> listing) {
        return new CUGoodsPageKeyRepository(apiService, listing);
    }
}
