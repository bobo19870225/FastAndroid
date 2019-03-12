package com.jinkan.www.fastandroid.model.repository.http;


import com.jinkan.www.fastandroid.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sampson on 2018/4/17.
 * 网络请求接口
 */
public interface ApiService {
    @GET("top250")
    Call<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);
}


