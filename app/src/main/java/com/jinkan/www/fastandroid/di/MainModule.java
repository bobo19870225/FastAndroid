package com.jinkan.www.fastandroid.di;

import com.jinkan.www.fastandroid.view.CommonlyUsedFragment;
import com.jinkan.www.fastandroid.view.GoodsFragment;
import com.jinkan.www.fastandroid.view.HomeFragment;
import com.jinkan.www.fastandroid.view.MainFragment;
import com.jinkan.www.fastandroid.view.MeFragment;
import com.jinkan.www.fastandroid.view.SortFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 */
@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract GoodsFragment goodsFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract HomeFragment homeFragment();
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MeFragment meFragment();

    @ContributesAndroidInjector
    abstract SortFragment sortFragment();

    @ContributesAndroidInjector
    abstract CommonlyUsedFragment commonlyUsedFragment();

    @ContributesAndroidInjector
    abstract MainFragment mainFragment();


}
