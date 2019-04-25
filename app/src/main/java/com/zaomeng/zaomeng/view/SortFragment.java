package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentSortBinding;
import com.zaomeng.zaomeng.view.adapter.GoodsAdapter;
import com.zaomeng.zaomeng.view.adapter.GoodsParentAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.SortFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;


public class SortFragment extends MVVMListFragment<SortFragmentVM, FragmentSortBinding, GoodsAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;
    private List<String> list = new ArrayList<>();

    @Inject
    public SortFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_sort;
    }


    @Override
    protected Object getData() {
        return getActivity() == null ? null : ((MainActivity) getActivity()).transferData;
    }

    @Override
    protected void setUI() {
        list.add("包子");
        list.add("来一份");
        list.add("来四分");
        GoodsParentAdapter goodsParentAdapter = new GoodsParentAdapter(list);
        goodsParentAdapter.setOnItemClick((view, ItemObject, position) -> {

        });
        mViewDataBinding.list1.setAdapter(goodsParentAdapter);
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
    protected SortFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(SortFragmentVM.class);
    }
}
