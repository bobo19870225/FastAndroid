package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.GoodsPageKeyRepository;

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
