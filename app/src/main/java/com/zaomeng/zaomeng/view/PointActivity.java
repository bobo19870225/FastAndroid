package com.zaomeng.zaomeng.view;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

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
public class PointActivity extends BaseDaggerActivity {
    private static final String[] CHANNELS = new String[]{"获取", "使用"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ViewPager mViewPager;
    public TextView point;

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_point;
    }

    @Override
    protected void initView() {
        point = findViewById(R.id.point);
        findViewById(R.id.ll_jfgz).setOnClickListener(v -> skipTo(ArticleActivity.class, "积分规则"));
        findViewById(R.id.ll_zqjf).setOnClickListener(v -> skipTo(ArticleActivity.class, "赚取积分"));
        findViewById(R.id.ll_jfdh).setOnClickListener(v -> skipTo(ArticleActivity.class, "积分兑换"));
        findViewById(R.id.back).setOnClickListener(v -> finish());
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new PointFragment());
        fragmentList.add(new PointFragment());
        mViewPager = findViewById(R.id.view_pager);
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);//获取
        integerList.add(2);//消费
        ListFragmentAdapter listFragmentAdapter = new ListFragmentAdapter(getSupportFragmentManager(), fragmentList, mDataList, integerList);
        mViewPager.setAdapter(listFragmentAdapter);
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator3);
        magicIndicator.setBackgroundResource(R.drawable.bg_button_corner_bc);
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
                clipPagerTitleView.setTextColor(Color.parseColor("#FF9933"));
                clipPagerTitleView.setClipColor(Color.WHITE);
                clipPagerTitleView.setOnClickListener(v -> mViewPager.setCurrentItem(index));
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                float navigatorHeight = context.getResources().getDimension(R.dimen.point_navigator_height);
                float borderWidth = UIUtil.dip2px(context, 1);
                float lineHeight = navigatorHeight - 2 * borderWidth;
                indicator.setLineHeight(lineHeight);
                indicator.setRoundRadius(lineHeight / 2);
//                indicator.setLineWidth(commonNavigator.getWidth()/2);
                indicator.setYOffset(borderWidth);
                indicator.setColors(Color.parseColor("#FF9933"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
}
