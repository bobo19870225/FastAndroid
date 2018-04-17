package com.jinkan.www.fastandroid.model.http;

import android.database.Observable;

import com.jinkan.www.fastandroid.model.Movie;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sampson on 2018/4/17.
 * 网络请求接口
 */
public interface ApiService {
    @GET("top250")
    Observable<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);
}


