package com.zaomeng.zaomeng.di;

import com.zaomeng.zaomeng.view.AfterSaleOrderFragment;
import com.zaomeng.zaomeng.view.AfterSaleRecordFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 */
@Module
public abstract class ServiceModule {
    @ContributesAndroidInjector
    abstract AfterSaleRecordFragment afterSaleRecordFragment();

    @ContributesAndroidInjector
    abstract AfterSaleOrderFragment afterSaleOrderFragment();

}
