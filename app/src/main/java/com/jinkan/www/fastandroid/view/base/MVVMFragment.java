package com.jinkan.www.fastandroid.view.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jinkan.www.fastandroid.BR;
import com.jinkan.www.fastandroid.view_model.BaseViewModel;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * Created by Sampson on 2019/4/3.
 * FastAndroid
 */
public abstract class MVVMFragment<VM extends BaseViewModel, VDB extends ViewDataBinding> extends BaseFragment {
    protected VM mViewModel;
    protected VDB mViewDataBinding;

    @Override
    protected final View setRootView(LayoutInflater inflater, ViewGroup container, int setLayoutRes) {

        mViewModel = createdViewModel();
        if (mViewModel == null) {
            throw new RuntimeException("ViewModel can't be null!");
        } else {
            mViewDataBinding = DataBindingUtil.inflate(inflater, setLayoutRes, container, false);
            mViewDataBinding.setLifecycleOwner(this);
            mViewDataBinding.setVariable(BR.model, mViewModel);
        }
        return mViewDataBinding.getRoot();

    }

    protected abstract VM createdViewModel();


    @Override
    protected final void initData(Object transferData) {
        mViewModel.init(transferData);
    }
}
