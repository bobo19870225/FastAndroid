package com.jinkan.www.fastandroid.di;

import com.jinkan.www.fastandroid.view.CertificationActivity;
import com.jinkan.www.fastandroid.view.LoginActivity;
import com.jinkan.www.fastandroid.view.MainActivity;
import com.jinkan.www.fastandroid.view.RegisterActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * We want Dagger.Android to create a Subcomponent which has a parent Component of whichever module ActivityBindingModule is on,
 * in our case that will be AppComponent. The beautiful part about this setup is that you never need to tell AppComponent that it is going to have all these subcomponents
 * nor do you need to tell these subcomponents that AppComponent exists.
 * We are also telling Dagger.Android that this generated SubComponent needs to include the specified modules and be aware of a scope annotation @ActivityScoped
 * When Dagger.Android annotation processor runs it will create 4 subcomponents for us.
 */
@Module
public abstract class ActivityBindingModule {


    @ActivityScoped
    @ContributesAndroidInjector(modules = {MainModule.class})
    abstract MainActivity mainActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract LoginActivity loginActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract RegisterActivity registerActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract CertificationActivity certificationActivity();



}
