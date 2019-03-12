package com.jinkan.www.fastandroid.view.base;

/**
 * Created by Sampson on 2019/3/12.
 * FastAndroid
 * MVVM模式的View（Activity）基类
 */
public class MVVMActivity extends BaseDaggerActivity {
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
        return 0;
    }

    @Override
    protected void initView() {

    }
}
