package com.zaomeng.zaomeng.view;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.view.adapter.FragmentAdapter;
import com.zaomeng.zaomeng.view.base.BaseDaggerActivity;
import com.zaomeng.zaomeng.view.custom_view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class ServiceActivity extends BaseDaggerActivity {


    @Override
    protected String setToolBarTitle() {
        return "申请售后";
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_service;
    }

    @Override
    protected void initView() {
        PagerSlidingTabStrip pagerSlidingTabStrip = findViewById(R.id.table_strip);
        ViewPager viewPager = findViewById(R.id.view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new AfterSaleOrderFragment());
        fragmentList.add(new AfterSaleRecordFragment());
        String[] titles = {"售后申请", "申请记录"};
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, fragmentList, titles, null);
        viewPager.setAdapter(fragmentAdapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
//        pagerSlidingTabStrip.setCurrentPosition(0);
//        pagerSlidingTabStrip.setSelectedPosition(0);
        pagerSlidingTabStrip.setShouldExpand(true);
    }
}
