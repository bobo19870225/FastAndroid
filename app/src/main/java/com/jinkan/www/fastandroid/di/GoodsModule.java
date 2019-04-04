package com.jinkan.www.fastandroid.di;

import com.jinkan.www.fastandroid.view.GoodsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 */
@Module
public abstract class GoodsModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract GoodsFragment goodsFragment();
}
