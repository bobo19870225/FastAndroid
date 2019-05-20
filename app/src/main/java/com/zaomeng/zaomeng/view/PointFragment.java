package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentListBinding;
import com.zaomeng.zaomeng.view.adapter.point.PointAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.PointFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 */
public class PointFragment extends MVVMListFragment<PointFragmentVM, FragmentListBinding, PointAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    public PointFragment() {
    }

    @Override
    protected void setUI() {
        mViewModel.ldTotalPoint.observe(this, s -> {
            PointActivity activity = (PointActivity) getActivity();
            if (activity != null) {
                activity.point.setText(s);
            }
        });
    }

    @NonNull
    @Override
    protected PointAdapter setAdapter(Function0 reTry) {
        return new PointAdapter(reTry);
    }

    @NonNull
    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @Override
    protected PointFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(PointFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_list;
    }
}
