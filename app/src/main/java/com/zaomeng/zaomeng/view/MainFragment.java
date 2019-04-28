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
    ViewModelFactory viewModelFactory;
    @Inject
    MainGoodsFragment mainGoodsFragment;
    private Badge msgBadge;

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
//                    BranchGoodsFragment branchGoodsFragment = new BranchGoodsFragment();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("DATA", IDs.get(0));
//                    branchGoodsFragment.setArguments(bundle);
//                    //第二个
//                    BranchGoodsFragment branchGoodsFragment1 = new BranchGoodsFragment();
//                    Bundle bundle1 = new Bundle();
//                    bundle1.putString("DATA", IDs.get(1));
//                    branchGoodsFragment.setArguments(bundle1);
//                    //第三个
//                    BranchGoodsFragment branchGoodsFragment2 = new BranchGoodsFragment();
//                    Bundle bundle2 = new Bundle();
//                    bundle2.putString("DATA", IDs.get(2));
//                    branchGoodsFragment.setArguments(bundle2);
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
