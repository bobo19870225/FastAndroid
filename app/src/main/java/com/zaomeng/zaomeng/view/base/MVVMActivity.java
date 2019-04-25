package com.zaomeng.zaomeng.view.base;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.zaomeng.zaomeng.BR;
import com.zaomeng.zaomeng.view_model.BaseViewModel;

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
//        return mViewDataBinding.getRoot();
    }

    protected abstract @NonNull
    VM createdViewModel();

    @Override
    protected final void initView() {
        mViewModel.init(transferData);
        setView();
    }

    /**
     * UI操作
     */
    protected abstract void setView();
}
