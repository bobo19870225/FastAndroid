package com.jinkan.www.fastandroid.model.repository;

import com.jinkan.www.fastandroid.model.Subjects;

/**
 * Created by Sampson on 2019/2/26.
 * FastAndroid
 */
public interface PostRepository {
    Listing<Subjects> post(String sub, Integer pageSize);
}
