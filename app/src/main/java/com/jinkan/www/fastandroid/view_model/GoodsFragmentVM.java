package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 */
public class GoodsFragmentVM extends ListViewModel<Goods> {
    public GoodsFragmentVM(@NonNull Application application) {
        super(application);
    }

    @Override
    protected Listing<Goods> getListing(Object data) {
        return null;
    }
}
