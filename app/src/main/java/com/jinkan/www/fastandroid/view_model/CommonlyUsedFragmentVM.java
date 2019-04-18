package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.Listing;

import androidx.annotation.NonNull;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class CommonlyUsedFragmentVM extends ListViewModel {
    public CommonlyUsedFragmentVM(@NonNull Application application) {
        super(application);
    }

    @Override
    protected Listing getListing(Object data) {
        return null;
    }
}
