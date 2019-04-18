package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.TreeFragment;
import com.jinkan.www.fastandroid.view.adapter.FragmentAdapter;
import com.jinkan.www.fastandroid.view.base.BaseDaggerActivity;
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
public class OrderActivity extends BaseDaggerActivity {
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;

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
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        pagerSlidingTabStrip = findViewById(R.id.table_strip);
        viewPager = findViewById(R.id.view_pager);
        FragmentManager fm = getSupportFragmentManager();
        String[] titles = {"全部订单", "待付款", "待收货"};
        List<Fragment> list = new ArrayList<>();
        list.add(new TreeFragment());
        list.add(new TreeFragment());
        list.add(new TreeFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fm, list, titles);
        viewPager.setAdapter(fragmentAdapter);
        pagerSlidingTabStrip.setViewPager(viewPager);

        if (transferData.equals("allOrder")) {
            pagerSlidingTabStrip.setCurrentPosition(0);
            pagerSlidingTabStrip.setSelectedPosition(0);
        } else if (transferData.equals("payment")) {
            pagerSlidingTabStrip.setCurrentPosition(1);
            pagerSlidingTabStrip.setSelectedPosition(1);
        } else if (transferData.equals("receivingGoods")) {
            pagerSlidingTabStrip.setCurrentPosition(2);
            pagerSlidingTabStrip.setSelectedPosition(2);
        }


        pagerSlidingTabStrip.setShouldExpand(true);
    }
}
