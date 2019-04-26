package com.zaomeng.zaomeng.view;

import android.content.Context;
import android.content.Intent;

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
    private static final int SEARCH = 110;
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
        Context context = getContext();
        mViewModel.action.observe(this, s -> {
            if (s.equals("search")) {
                Intent intent = new Intent();
                if (context != null) {
                    intent.setClass(context, SearchActivity.class);
                    startActivityForResult(intent, SEARCH);
                }

            }
        });
        GoodsParentAdapter goodsParentAdapter = new GoodsParentAdapter(context, rows);
        goodsParentAdapter.setOnItemClick((view, ItemObject, position) -> setListView(ItemObject.getId()));
        mViewDataBinding.list1.setAdapter(goodsParentAdapter);
        mViewModel.getNodeCategoryList().observe(this, pageBeanResource -> {
            HttpHelper<GoodsSuperBean> goodsSuperBeanHttpHelper = new HttpHelper<>(context);
            PageDataBean<GoodsSuperBean> goodsSuperBeanPageDataBean = goodsSuperBeanHttpHelper.AnalyticalPageData(pageBeanResource);
            rows = goodsSuperBeanPageDataBean.getRows();
            goodsParentAdapter.setList(rows);
            //获取第一个分类的商品
            setListView(rows.get(0).getId());
            goodsParentAdapter.setSelect(0);
        });

    }

    @NonNull
    @Override
    protected GoodsAdapter setAdapter(Function0 reTry) {
        GoodsAdapter goodsAdapter = new GoodsAdapter(reTry);
        goodsAdapter.setOnItemClick((view, ItemObject, position) -> skipTo(GoodsDetailsActivity.class, ItemObject.getId()));
        return goodsAdapter;
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
