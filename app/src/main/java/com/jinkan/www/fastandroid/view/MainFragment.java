package com.jinkan.www.fastandroid.view;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.FragmentMainBinding;
import com.jinkan.www.fastandroid.view.adapter.FragmentAdapter;
import com.jinkan.www.fastandroid.view.base.MVVMFragment;
import com.jinkan.www.fastandroid.view.custom_view.PagerSlidingTabStrip;
import com.jinkan.www.fastandroid.view_model.MainFragmentVM;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class MainFragment extends MVVMFragment<MainFragmentVM, FragmentMainBinding> {
    private static final int LOCATION = 0;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
    @Inject
    GoodsFragment goodsFragment;
    private Badge msgBadge;

    @Inject
    public MainFragment() {
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initUI() {
        Context context = getContext();
        msgBadge = new QBadgeView(context).bindTarget(mViewDataBinding.iconMsg)
                .setShowShadow(true)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
//                .setGravityOffset(-8, -8, true)
//                .setOnDragStateChangedListener(null)
                .setBadgeNumber(5);
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "msg":
                    skipTo(MessageActivity.class, null);
                    msgBadge.setBadgeNumber(0);
                    break;
                case "location":
                    Intent intent = new Intent();
                    if (context != null) {
                        intent.setClass(context, LocationActivity.class);
                    }
                    startActivityForResult(intent, LOCATION);
                    break;
            }
        });

        pagerSlidingTabStrip = rootView.findViewById(R.id.table_strip);
        viewPager = rootView.findViewById(R.id.view_pager);
        //注意了！在Fragment中要用getChildFragmentManager().
        FragmentManager fragmentManager = getChildFragmentManager();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(goodsFragment);
        fragmentList.add(new CommonlyUsedFragment());
        fragmentList.add(new CommonlyUsedFragment());
        fragmentList.add(new CommonlyUsedFragment());
        String[] titles = {"推荐", "早盟优选", "品质热卖", "本周上新"};
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, fragmentList, titles);
        viewPager.setAdapter(fragmentAdapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setSelectedPosition(0);
        pagerSlidingTabStrip.setShouldExpand(false);
    }

    @Override
    protected MainFragmentVM createdViewModel() {
        return ViewModelProviders.of(this).get(MainFragmentVM.class);
    }


}
