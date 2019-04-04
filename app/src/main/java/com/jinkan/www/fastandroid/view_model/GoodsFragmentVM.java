package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;
import com.jinkan.www.fastandroid.model.repository.http.by_page.GoodsPageKeyRepository;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 */
public class GoodsFragmentVM extends ListViewModel<Goods> {
    private GoodsPageKeyRepository goodsPageKeyRepository;

    public GoodsFragmentVM(@NonNull Application application, GoodsPageKeyRepository goodsPageKeyRepository) {
        super(application);
        this.goodsPageKeyRepository = goodsPageKeyRepository;
    }

    @Override
    protected Listing<Goods> getListing(Object data) {
        return goodsPageKeyRepository.post((String) data, 10);
    }
}
