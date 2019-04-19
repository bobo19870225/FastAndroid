package com.jinkan.www.fastandroid.model.repository;

/**
 * Created by Sampson on 2019/2/26.
 * FastAndroid
 */
public interface PostRepository<T> {
    Listing<T> post(String[] sub, Integer pageSize);
}
