package com.zaomeng.zaomeng.view;

import android.view.Gravity;
import android.view.KeyEvent;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;
import com.zaomeng.zaomeng.view.base.BaseDaggerActivity;

import javax.inject.Inject;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseDaggerActivity {
    @Inject
    ApiService apiService;
    @Inject
    HttpHelper<ShopCartBean> httpHelper;
    public Badge badge;
    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

//        Bundle args = new Bundle();
//        args.putString("DATA", (String) transferData);
//        goodsFragment.setArguments(args);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationItemView shoppingCart = navigation.findViewById(R.id.shoppingCartFragment);
        badge = new QBadgeView(this).bindTarget(shoppingCart)
                .setShowShadow(true)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setGravityOffset(10, 0, true)
//                .setOnDragStateChangedListener(null)
                .setBadgeNumber(0);
        apiService.getCartGoodsListLD(SharedPreerencesUtils.getSessionID(getApplicationContext()), 1, 10)
                .observe(this, pageBeanResource -> {
                    PageDataBean<ShopCartBean> goodsPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource);
                    if (goodsPageDataBean != null) {
                        int total = goodsPageDataBean.getTotal();
                        badge.setBadgeNumber(total);
                    }
                });

        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);
        NavigationUI.setupWithNavController(navigation, navController);

    }

    private long firstTime;// 记录点击返回时第一次的时间毫秒值

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 点击了返回按键
            exitApp();// 退出应用
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出应用
     */
    private void exitApp() {
        if (System.currentTimeMillis() - firstTime >= (long) 2000) {
            toast("再按一次退出程序");
            firstTime = System.currentTimeMillis();
        } else {
            finish();// 销毁当前activity
            System.exit(0);// 完全退出应用
        }
    }

}
