package com.jinkan.www.fastandroid.view.base;

import com.jinkan.www.fastandroid.BR;
import com.jinkan.www.fastandroid.view_model.BaseViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by Sampson on 2019/3/12.
 * FastAndroid
 * MVVM模式的View（Activity）基类
 */
public abstract class MVVMActivity<VM extends BaseViewModel, VDB extends ViewDataBinding> extends BaseDaggerActivity {
    protected VM mViewModel;
    protected VDB mViewDataBinding;

    /**
     * 绑定布局，获取mViewDataBinding和mViewModel。
     */
    @Override
    protected final void setRootView() {
        mViewModel = createdViewModel();
        if (mViewModel == null) {
            throw new RuntimeException("ViewModel can't be null!");
        } else {
            mViewDataBinding = DataBindingUtil.setContentView(this, setLayoutRes());
            mViewDataBinding.setLifecycleOwner(this);
            mViewDataBinding.setVariable(BR.model, mViewModel);
        }
    }

    protected abstract VM createdViewModel();

    @Override
    protected final void initView() {
        mViewModel.init();
        setView();
    }

    /**
     * UI操作
     */
    protected abstract void setView();
}