package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsListRowsBean;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/4/21.
 * FastAndroid
 */
public class ShoppingCartFragmentVM extends ListViewModel<GoodsListRowsBean> {
    public ShoppingCartFragmentVM(@NonNull Application application) {
        super(application);
    }

    @Override
    protected Listing<GoodsListRowsBean> getListing(Object data) {
        return null;
    }
}
