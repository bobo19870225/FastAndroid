package com.zaomeng.zaomeng.di;

import com.zaomeng.zaomeng.view.AllOrderFragment;
import com.zaomeng.zaomeng.view.CommonlyUsedFragment;
import com.zaomeng.zaomeng.view.NewOrderFragment;
import com.zaomeng.zaomeng.view.ReceivedOrderFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 */
@Module
public abstract class OrderModule {
    @ContributesAndroidInjector
    abstract CommonlyUsedFragment commonlyUsedFragment();

    @ContributesAndroidInjector
    abstract AllOrderFragment orderFragment();

    @ContributesAndroidInjector
    abstract NewOrderFragment newOrderFragment();

    @ContributesAndroidInjector
    abstract ReceivedOrderFragment receivedOrderFragment();



}
