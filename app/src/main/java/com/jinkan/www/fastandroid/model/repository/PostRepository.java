package com.jinkan.www.fastandroid.model.repository;

import com.jinkan.www.fastandroid.model.Movie;

/**
 * Created by Sampson on 2019/2/26.
 * FastAndroid
 */
public interface PostRepository {
    Listing<Movie> post(String sub, Integer pageSize);
}
