package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
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

import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class MainFragment extends MVVMFragment<MainFragmentVM, FragmentMainBinding> implements AMapLocationListener {
    private static final int LOCATION = 0;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private ViewPager viewPager;
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    MainGoodsFragment mainGoodsFragment;
    @Inject
    HttpHelper httpHelper;
    //声明mlocationClient对象
    private AMapLocationClient mlocationClient;

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
        mlocationClient = new AMapLocationClient(context);
//初始化定位参数
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(this);
//设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(2000);
//设置定位参数
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        mlocationClient.setLocationOption(mLocationOption);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
// 注意设置合适的定位时间的间隔（最小间隔支持为1000ms），并且在合适时间调用stopLocation()方法来取消定位请求
// 在定位结束后，在合适的生命周期调用onDestroy()方法
// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
//启动定位

        PermissionGen.needPermission(MainFragment.this,
                1001,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION});
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

    @PermissionSuccess(requestCode = 1001)
    private void getLocation() {
        mlocationClient.startLocation();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mlocationClient != null)
            mlocationClient.stopLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
//                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                aMapLocation.getLatitude();//获取纬度
//                aMapLocation.getLongitude();//获取经度
//                aMapLocation.getAccuracy();//获取精度信息
//                String city = aMapLocation.getCity();
                String district = aMapLocation.getDistrict();
                mViewModel.ldLocation.setValue(district);
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = new Date(aMapLocation.getTime());
//                df.format(date);//定位时间
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
            }
        }
    }
}
