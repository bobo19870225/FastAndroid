package com.zaomeng.zaomeng.di;

import com.zaomeng.zaomeng.view.BonusFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 */
@Module
public abstract class BonusModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract BonusFragment bonusFragment();
}
