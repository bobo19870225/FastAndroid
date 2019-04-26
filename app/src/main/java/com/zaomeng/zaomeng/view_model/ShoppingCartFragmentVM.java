package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;

/**
 * Created by Sampson on 2019/4/21.
 * FastAndroid
 */
public class ShoppingCartFragmentVM extends ListViewModel<GoodsListRowsBean> {
    public ShoppingCartFragmentVM(@NonNull Application application) {
        super(application);
    }

    @Override
    public void init(Object data) {

    }

    @Override
    public Listing<GoodsListRowsBean> getListing(Object data) {
        return null;
    }
}
