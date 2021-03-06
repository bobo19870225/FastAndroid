package com.zaomeng.zaomeng.di;

import com.zaomeng.zaomeng.view.AddressManageActivity;
import com.zaomeng.zaomeng.view.AfterSaleDetailsActivity;
import com.zaomeng.zaomeng.view.ArticleActivity;
import com.zaomeng.zaomeng.view.BonusActivity;
import com.zaomeng.zaomeng.view.CalendarActivity;
import com.zaomeng.zaomeng.view.CertificationActivity;
import com.zaomeng.zaomeng.view.ChoseAddressActivity;
import com.zaomeng.zaomeng.view.ChoseBonusActivity;
import com.zaomeng.zaomeng.view.ChoseShopTypeActivity;
import com.zaomeng.zaomeng.view.CustomerServiceActivity;
import com.zaomeng.zaomeng.view.FeedbackActivity;
import com.zaomeng.zaomeng.view.FindPasswordActivity;
import com.zaomeng.zaomeng.view.GoodsDetailsActivity;
import com.zaomeng.zaomeng.view.LocationActivity;
import com.zaomeng.zaomeng.view.LoginActivity;
import com.zaomeng.zaomeng.view.MainActivity;
import com.zaomeng.zaomeng.view.MessageActivity;
import com.zaomeng.zaomeng.view.MessageTypeActivity;
import com.zaomeng.zaomeng.view.OrderActivity;
import com.zaomeng.zaomeng.view.OrderDetailActivity;
import com.zaomeng.zaomeng.view.OrderSettlementActivity;
import com.zaomeng.zaomeng.view.PointActivity;
import com.zaomeng.zaomeng.view.RegisterActivity;
import com.zaomeng.zaomeng.view.SearchActivity;
import com.zaomeng.zaomeng.view.SearchGoodsListActivity;
import com.zaomeng.zaomeng.view.ServiceActivity;
import com.zaomeng.zaomeng.view.SettingActivity;
import com.zaomeng.zaomeng.view.ShopDetailActivity;
import com.zaomeng.zaomeng.view.UserInfoActivity;

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

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract GoodsDetailsActivity goodsDetails();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {OrderModule.class})
    abstract OrderActivity orderActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ServiceModule.class})
    abstract ServiceActivity serviceActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {OrderModule.class})
    abstract MessageActivity messageActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {OrderModule.class})
    abstract LocationActivity locationActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract SearchActivity searchActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract OrderSettlementActivity orderSettlementActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract AddressManageActivity addressManageActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract CalendarActivity calendarActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {PointModule.class})
    abstract PointActivity pointActivity();

    @ActivityScoped
    @ContributesAndroidInjector(modules = {BonusModule.class})
    abstract BonusActivity bonusActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract FeedbackActivity feedbackActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract SettingActivity settingActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract UserInfoActivity userInfoActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract FindPasswordActivity findPasswordActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract SearchGoodsListActivity searchGoodsListActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract ShopDetailActivity shopDetailActivity();

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract ChoseBonusActivity choseBonusActivity();

    @ActivityScoped

    @ContributesAndroidInjector()
    abstract OrderDetailActivity orderDetailActivity();


    @ContributesAndroidInjector()
    abstract CustomerServiceActivity customerServiceActivity();

    @ContributesAndroidInjector()
    abstract ArticleActivity pointRuleActivity();

    @ContributesAndroidInjector()
    abstract MessageTypeActivity messageTypeActivity();

    @ContributesAndroidInjector()
    abstract ChoseAddressActivity choseAddressActivity();

    @ContributesAndroidInjector()
    abstract AfterSaleDetailsActivity afterSaleDetailsActivity();

    @ContributesAndroidInjector()
    abstract ChoseShopTypeActivity choseShopTypeActivity();


}
