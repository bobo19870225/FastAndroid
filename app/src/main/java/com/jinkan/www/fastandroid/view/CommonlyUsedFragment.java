package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.FragmentCommonlyUsedBinding;
import com.jinkan.www.fastandroid.view.adapter.GoodsAdapter;
import com.jinkan.www.fastandroid.view.base.MVVMListFragment;
import com.jinkan.www.fastandroid.view_model.CommonlyUsedFragmentVM;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import kotlin.jvm.functions.Function0;


public class CommonlyUsedFragment extends MVVMListFragment<CommonlyUsedFragmentVM, FragmentCommonlyUsedBinding, GoodsAdapter> {
    @Inject
    public CommonlyUsedFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_commonly_used;
    }


    @Override
    protected void setUI() {

    }

    @NonNull
    @Override
    protected GoodsAdapter setAdapter(Function0 reTry) {
        return new GoodsAdapter(reTry);
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
    protected CommonlyUsedFragmentVM createdViewModel() {
        return null;
    }


}
