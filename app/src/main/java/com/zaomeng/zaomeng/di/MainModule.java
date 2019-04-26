package com.zaomeng.zaomeng.di;

import com.zaomeng.zaomeng.view.CommonlyUsedFragment;
import com.zaomeng.zaomeng.view.GoodsFragment;
import com.zaomeng.zaomeng.view.MainFragment;
import com.zaomeng.zaomeng.view.MeFragment;
import com.zaomeng.zaomeng.view.ShoppingCartFragment;
import com.zaomeng.zaomeng.view.SortFragment;

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

    //    @FragmentScoped
//    @ContributesAndroidInjector
//    abstract HomeFragment homeFragment();
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MeFragment meFragment();

    @ContributesAndroidInjector
    abstract SortFragment sortFragment();

    @ContributesAndroidInjector
    abstract CommonlyUsedFragment commonlyUsedFragment();

    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @ContributesAndroidInjector
    abstract ShoppingCartFragment shoppingCartFragment();


}
