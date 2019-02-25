package com.jinkan.www.fastandroid.model.http;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.jinkan.www.fastandroid.SystemParameter.baseUrl;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */
public class HttpRepsitory {
    private Retrofit retrofit;

    @Inject
    public HttpRepsitory() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                //适配RxJava2.0,RxJava1.x则为RxJavaCallAdapterFactory.create()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }


}
