package com.zaomeng.zaomeng.view;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentMainBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.view.adapter.FragmentAdapter;
import com.zaomeng.zaomeng.view.base.MVVMFragment;
import com.zaomeng.zaomeng.view.custom_view.PagerSlidingTabStrip;
import com.zaomeng.zaomeng.view_model.MainFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
    ViewModelFactory viewModelFactory;
    @Inject
    MainGoodsFragment mainGoodsFragment;
    @Inject
    HttpHelper httpHelper;

    @Inject
    public MainFragment() {
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_main;
    }

    private List<String> titles = new ArrayList<>();
    private List<String> IDs = new ArrayList<>();

    @Override
    protected void initUI() {
        Context context = getContext();
        mViewModel.getNoReadMessageNum().observe(this, beanResource -> {
            Integer integer = (Integer) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {

                }

                @Override
                public void reLoad() {

                }
            }, this);
            if (integer != null) {
                new QBadgeView(context).bindTarget(mViewDataBinding.iconMsg)
                        .setShowShadow(true)
                        .setBadgeGravity(Gravity.END | Gravity.TOP)
                        .setGravityOffset(-2, -2, true)
                        .setBadgeNumber(integer);
            }

        });

        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "msg":
                    skipTo(MessageTypeActivity.class, null);
                    break;
                case "location":
                    Intent intent = new Intent();
                    if (context != null) {
                        intent.setClass(context, LocationActivity.class);
                    }
                    startActivityForResult(intent, LOCATION);
                    break;
                case "search":
                    skipTo(SearchActivity.class, null);
                    break;
            }
        });

        pagerSlidingTabStrip = rootView.findViewById(R.id.table_strip);
        viewPager = rootView.findViewById(R.id.view_pager);
        mViewModel.getNodeNavigatorList().observe(this, pageBeanResource -> {
            if (pageBeanResource.isSuccess()) {
                PageBean<NavigatorBean> resource = pageBeanResource.getResource();
                if (resource != null && resource.getHeader().getCode() == 0) {
                    List<NavigatorBean> rows = resource.getBody().getData().getRows();
                    for (NavigatorBean n : rows
                    ) {
                        titles.add(n.getName());
                        IDs.add(n.getId());
                    }
                    //注意了！在Fragment中要用getChildFragmentManager().
                    FragmentManager fragmentManager = getChildFragmentManager();
                    List<Fragment> fragmentList = new ArrayList<>();
                    fragmentList.add(mainGoodsFragment);
                    //------------------------------------------------
                    fragmentList.add(new BranchGoodsFragment());
                    fragmentList.add(new BranchGoodsFragment());
                    fragmentList.add(new BranchGoodsFragment());
                    String[] strings = new String[titles.size()];
                    FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, fragmentList, titles.toArray(strings), IDs);
                    viewPager.setAdapter(fragmentAdapter);
                    pagerSlidingTabStrip.setViewPager(viewPager);
                    pagerSlidingTabStrip.setShouldExpand(false);

                } else {
                    if (resource != null) {
                        toast(resource.getHeader().getMsg());
                    }
                }
            } else {
                Throwable error = pageBeanResource.getError();
                if (error != null) {
                    toast(error.toString());
                }
            }
        });


    }

    void setSelectedPosition(String title) {
        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).equals(title)) {
                pagerSlidingTabStrip.setSelectedPosition(i);
            }
        }
    }


    @Override
    protected MainFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(MainFragmentVM.class);
    }


}
