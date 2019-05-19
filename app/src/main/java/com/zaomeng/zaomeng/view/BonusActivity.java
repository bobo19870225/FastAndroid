package com.zaomeng.zaomeng.view;

import android.content.Context;
import android.graphics.Color;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.view.adapter.ListFragmentAdapter;
import com.zaomeng.zaomeng.view.base.BaseDaggerActivity;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 */
public class BonusActivity extends BaseDaggerActivity {


    private static final String[] CHANNELS = new String[]{"未使用", "已使用", "已过期"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ViewPager mViewPager;

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "优惠券";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_bonus;
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.view_pager);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new BonusFragment());
        fragmentList.add(new BonusFragment());
        fragmentList.add(new BonusFragment());
        List<Integer> listSortType = new ArrayList<>();
        listSortType.add(1);
        listSortType.add(2);
        listSortType.add(3);
        ListFragmentAdapter listFragmentAdapter = new ListFragmentAdapter(
                getSupportFragmentManager(),
                fragmentList,
                mDataList, listSortType);
        mViewPager.setAdapter(listFragmentAdapter);
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
//        magicIndicator.setBackgroundResource(R.drawable.bg_button_corner_bc);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(mDataList.get(index));
                clipPagerTitleView.setTextColor(Color.BLACK);
                clipPagerTitleView.setClipColor(Color.parseColor("#FF9933"));
                clipPagerTitleView.setOnClickListener(v -> mViewPager.setCurrentItem(index));
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float borderWidth = UIUtil.dip2px(context, 1);
                indicator.setLineHeight(UIUtil.dip2px(context, 2));
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setYOffset(borderWidth);
                indicator.setColors(Color.parseColor("#FF9933"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
}
