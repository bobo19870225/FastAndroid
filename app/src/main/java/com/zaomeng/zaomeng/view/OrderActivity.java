package com.zaomeng.zaomeng.view;

import android.content.Context;
import android.graphics.Color;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class OrderActivity extends BaseDaggerActivity {
    private static final String[] CHANNELS = new String[]{"全部订单", "待付款", "待收货"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private ViewPager viewPager;

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "我的订单";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {
        viewPager = findViewById(R.id.view_pager);
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> list = new ArrayList<>();
        list.add(new AllOrderFragment());
        list.add(new NewOrderFragment());
        list.add(new ReceivedOrderFragment());
        ListFragmentAdapter listFragmentAdapter = new ListFragmentAdapter(fm, list, mDataList);
        viewPager.setAdapter(listFragmentAdapter);
        initMagicIndicator();
        if (transferData != null) {
            switch ((String) transferData) {
                case "allOrder":
                    viewPager.setCurrentItem(0);
                    break;
                case "payment":
                    viewPager.setCurrentItem(1);
                    break;
                case "receivingGoods":
                    viewPager.setCurrentItem(2);
                    break;
            }
        }
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
                clipPagerTitleView.setOnClickListener(v -> viewPager.setCurrentItem(index));
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
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }
}
