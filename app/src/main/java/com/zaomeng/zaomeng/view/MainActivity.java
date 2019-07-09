package com.zaomeng.zaomeng.view;

import android.content.Intent;
import android.view.Gravity;
import android.view.KeyEvent;

import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.view.base.BaseDaggerActivity;

import javax.inject.Inject;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseDaggerActivity {
    @Inject
    ApiService apiService;
    @Inject
    HttpHelper httpHelper;
    private NavController navController;
    public Badge badge;
    protected BottomNavigationView navigation;

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

    @SuppressWarnings("unchecked")
    @Override
    protected void initView() {

//        Bundle args = new Bundle();
//        args.putString("DATA", (String) transferData);
//        mainGoodsFragment.setArguments(args);

        navigation = findViewById(R.id.navigation);
        BottomNavigationItemView shoppingCart = navigation.findViewById(R.id.shoppingCartFragment);
        badge = new QBadgeView(this).bindTarget(shoppingCart)
                .setShowShadow(false)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setGravityOffset(10, 0, true)
//                .setOnDragStateChangedListener(null)
                .setBadgeNumber(0);
        final String sessionID = SharedPreferencesUtils.getSessionID(getApplicationContext());
        apiService.getCartGoodsListLD(sessionID, 1, 10)
                .observe(this, pageBeanResource -> {
                    PageDataBean<ShopCartBean> goodsPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource, new InterfaceLogin() {
                        @Override
                        public void skipLoginActivity() {
//                            skipTo(LoginActivity.class, null);
                        }

                        @Override
                        public void reLoad() {

                        }
                    }, this);
                    if (goodsPageDataBean != null) {
                        int total = goodsPageDataBean.getTotal();
                        badge.setBadgeNumber(total);
                    }
                });

//        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                if (menuItem.getItemId() == R.id.treeFragment) {
//
//                }
//                return false;
//            }
//        });
        navController = Navigation.findNavController(this, R.id.nav_fragment);
        NavigationUI.setupWithNavController(navigation, navController);
        if (transferData instanceof Integer) {
            if ((int) transferData == 3)
                navController.navigate(R.id.shoppingCartFragment);
        }
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() != R.id.mainFragment && destination.getId() != R.id.sortFragment) {
                if (sessionID == null) {

//                        navigation.setSelectedItemId(R.id.mainFragment);
//                        navigation.setSelectedItemId(navigation.getMenu().getItem(0).getItemId());
                    Intent intent = new Intent();
                    intent.setClass(getApplicationContext(), LoginActivity.class);
                    startActivityForResult(intent, 110);
//                    skipTo(LoginActivity.class);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110) {
            navController.navigate(R.id.mainFragment);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
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
