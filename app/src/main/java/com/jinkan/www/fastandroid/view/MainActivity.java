package com.jinkan.www.fastandroid.view;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.view.base.BaseDaggerActivity;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

public class MainActivity extends BaseDaggerActivity {

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
//                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setGravityOffset(8, 8, true)
//                .setOnDragStateChangedListener(null)
                .setBadgeNumber(0);


        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);
        NavigationUI.setupWithNavController(navigation, navController);

    }


}
