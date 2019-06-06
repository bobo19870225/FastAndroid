package com.zaomeng.zaomeng.view;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivitySettingBinding;
import com.zaomeng.zaomeng.utils.MyDataCleanManager;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.SettingVM;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class SettingActivity extends MVVMActivity<SettingVM, ActivitySettingBinding> {
    @Override
    protected String setToolBarTitle() {
        return "设置";
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_setting;
    }

    @NonNull
    @Override
    protected SettingVM createdViewModel() {
        return ViewModelProviders.of(this).get(SettingVM.class);
    }


    @Override
    protected void setView() {
        try {
            mViewModel.ldCache.setValue(MyDataCleanManager.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        getAppVersionName();
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "exit":
                    skipTo(MainActivity.class, null, true);
                    break;
                case "us":
                    skipTo(ArticleActivity.class, "关于我们");
                    break;
            }

        });
    }

    /**
     * 返回当前程序版本名
     */
    private void getAppVersionName() {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = getApplicationContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getApplicationContext().getPackageName(), 0);
            versionName = pi.versionName;
//            versioncode = pi.versionCode;
            mViewModel.ldVersion.setValue(versionName);
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }

    }
}
