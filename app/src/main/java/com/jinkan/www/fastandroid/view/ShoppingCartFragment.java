package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.FragmentShoppingCartBinding;
import com.jinkan.www.fastandroid.view.adapter.GoodsAdapter;
import com.jinkan.www.fastandroid.view.base.MVVMListFragment;
import com.jinkan.www.fastandroid.view_model.ShoppingCartFragmentVM;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/21.
 * FastAndroid
 */
public class ShoppingCartFragment extends MVVMListFragment<ShoppingCartFragmentVM, FragmentShoppingCartBinding, GoodsAdapter> {
    @Inject
    public ShoppingCartFragment() {
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
    protected ShoppingCartFragmentVM createdViewModel() {
        return ViewModelProviders.of(this).get(ShoppingCartFragmentVM.class);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_shopping_cart;
    }
}
