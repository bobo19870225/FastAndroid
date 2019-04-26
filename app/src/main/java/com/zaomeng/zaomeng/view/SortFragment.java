package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentSortBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.adapter.GoodsAdapter;
import com.zaomeng.zaomeng.view.adapter.GoodsParentAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.SortFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;


public class SortFragment extends MVVMListFragment<SortFragmentVM, FragmentSortBinding, GoodsAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;


    @Inject
    public SortFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_sort;
    }


    private List<GoodsSuperBean> rows;
    @Override
    protected void setUI() {
        GoodsParentAdapter goodsParentAdapter = new GoodsParentAdapter(rows);
        goodsParentAdapter.setOnItemClick((view, ItemObject, position) -> setListView(ItemObject.getId()));
        mViewDataBinding.list1.setAdapter(goodsParentAdapter);
        mViewModel.getNodeCategoryList().observe(this, pageBeanResource -> {
            HttpHelper<GoodsSuperBean> goodsSuperBeanHttpHelper = new HttpHelper<>(getContext());
            PageDataBean<GoodsSuperBean> goodsSuperBeanPageDataBean = goodsSuperBeanHttpHelper.AnalyticalPageData(pageBeanResource);
            rows = goodsSuperBeanPageDataBean.getRows();
            goodsParentAdapter.setList(rows);
        });

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
