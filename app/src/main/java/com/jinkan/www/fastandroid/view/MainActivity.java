package com.jinkan.www.fastandroid.view;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.view.base.BaseDaggerActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseDaggerActivity {

    private BottomNavigationItemView shoppingCart;
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
        shoppingCart = navigation.findViewById(R.id.treeFragment);
        badge = new QBadgeView(this).bindTarget(shoppingCart)
                .setShowShadow(true)
//                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setGravityOffset(8, 8, true)
                .setOnDragStateChangedListener((dragState, badge, targetView) -> {
                    badge.setBadgeNumber(2);
                })
                .setBadgeNumber(1);


        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);
        NavigationUI.setupWithNavController(navigation, navController);

    }


}
