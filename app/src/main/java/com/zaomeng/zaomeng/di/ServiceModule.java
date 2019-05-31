package com.zaomeng.zaomeng.di;

import com.zaomeng.zaomeng.view.AfterSaleOrderFragment;
import com.zaomeng.zaomeng.view.CommonlyUsedFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 */
@Module
public abstract class ServiceModule {
    @ContributesAndroidInjector
    abstract CommonlyUsedFragment commonlyUsedFragment();

    @ContributesAndroidInjector
    abstract AfterSaleOrderFragment afterSaleOrderFragment();
}
