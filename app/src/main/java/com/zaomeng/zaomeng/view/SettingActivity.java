package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivitySettingBinding;
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
}
