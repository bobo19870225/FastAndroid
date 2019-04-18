package com.jinkan.www.fastandroid.di;

import com.jinkan.www.fastandroid.view.CommonlyUsedFragment;

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


}
