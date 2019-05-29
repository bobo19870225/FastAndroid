package com.zaomeng.zaomeng.model.repository;

import android.content.Context;

import com.zaomeng.zaomeng.BuildConfig;
import com.zaomeng.zaomeng.model.repository.dataBase.AddressDao;
import com.zaomeng.zaomeng.model.repository.dataBase.AppDatabase;
import com.zaomeng.zaomeng.model.repository.dataBase.SearchDao;
import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.LiveDataCallAdapterFactory;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.LiveDataResponseBodyConverterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//这里可以选择拦截级别
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }
        OkHttpClient client = builder.build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(LiveDataResponseBodyConverterFactory.create())//有顺序的
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .client(client)
                .build().create(ApiService.class);
    }

    @Singleton
    @Provides
    static AppDatabase appDatabase(Context context) {
//        dbCopy.copyDBFromAssets();
        return AppDatabase.getInstance(context);
    }

    @Singleton
    @Provides
    static AddressDao addressDao(AppDatabase appDatabase) {
        return appDatabase.addressDao();
    }

    @Singleton
    @Provides
    static UserDao userDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }


    @Singleton
    @Provides
    static SearchDao searchDao(AppDatabase appDatabase) {
        return appDatabase.searchDao();
    }

    @Singleton
    @Provides
    static HttpHelper httpHelper(Context context, ApiService apiService, UserDao userDao) {
        return new HttpHelper(context, apiService, userDao);
    }

}
