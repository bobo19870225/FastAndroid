package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.ActivityGoodsDetailsBinding;
import com.jinkan.www.fastandroid.view.base.MVVMActivity;
import com.jinkan.www.fastandroid.view_model.GoodsDetailsVM;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class GoodsDetailsActivity extends MVVMActivity<GoodsDetailsVM, ActivityGoodsDetailsBinding> {


    @NonNull
    @Override
    protected GoodsDetailsVM createdViewModel() {
        return ViewModelProviders.of(this).get(GoodsDetailsVM.class);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_goods_details;
    }
}
