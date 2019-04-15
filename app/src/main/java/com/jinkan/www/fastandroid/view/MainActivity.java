package com.jinkan.www.fastandroid.view;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.view.base.BaseDaggerActivity;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseDaggerActivity {


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
        NavController navController = Navigation.findNavController(this, R.id.nav_fragment);
        NavigationUI.setupWithNavController(navigation, navController);

    }


}
