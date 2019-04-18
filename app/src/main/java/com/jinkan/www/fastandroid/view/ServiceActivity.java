package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.view.adapter.FragmentAdapter;
import com.jinkan.www.fastandroid.view.base.BaseActivity;
import com.jinkan.www.fastandroid.view.custom_view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class ServiceActivity extends BaseActivity {


    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
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
        pagerSlidingTabStrip = findViewById(R.id.table_strip);
        viewPager = findViewById(R.id.view_pager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new CommonlyUsedFragment());
        fragmentList.add(new CommonlyUsedFragment());
        String[] titles = {"售后申请", "申请记录"};
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, fragmentList, titles);
        viewPager.setAdapter(fragmentAdapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
//        pagerSlidingTabStrip.setCurrentPosition(0);
//        pagerSlidingTabStrip.setSelectedPosition(0);
        pagerSlidingTabStrip.setShouldExpand(true);
    }
}