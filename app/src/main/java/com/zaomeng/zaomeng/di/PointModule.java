package com.zaomeng.zaomeng.di;

import com.zaomeng.zaomeng.view.PointFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 */
@Module
public abstract class PointModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract PointFragment pointFragment();
}
