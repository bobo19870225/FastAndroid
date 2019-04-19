package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.by_page.GoodsPageKeyRepository;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class SortFragmentVM extends ListViewModel<GoodsListRowsBean> {
    private ApiService apiService;
    private GoodsPageKeyRepository goodsPageKeyRepository;

    public SortFragmentVM(@NonNull Application application, ApiService apiService, GoodsPageKeyRepository goodsPageKeyRepository) {
        super(application);
        this.apiService = apiService;
        this.goodsPageKeyRepository = goodsPageKeyRepository;

    }

    @Override
    protected Listing<GoodsListRowsBean> getListing(Object data) {
        return goodsPageKeyRepository.post((String[]) data, 10);
    }
}
