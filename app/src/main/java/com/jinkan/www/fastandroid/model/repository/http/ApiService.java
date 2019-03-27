package com.jinkan.www.fastandroid.model.repository.http;


import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;
import com.jinkan.www.fastandroid.model.repository.dataBase.User;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.Resource;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sampson on 2018/4/17.
 * 网络请求接口
 */
public interface ApiService {
//    @GET("top250")
//    Call<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("login")
    LiveData<Resource<Message<User>>> login(@Query("loginname") String account, @Query("password") String password);

    @GET("getGoodsList")
    Call<Message<Goods>> getGoodsList(@Query("pageIndex") Integer pageIndex,
                                      @Query("pageSize") Integer pageSize,
                                      @Query("token") String token);
}


