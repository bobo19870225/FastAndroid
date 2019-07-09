package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentBranchGoodsBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.view.adapter.branch_goods.BranchGoodsAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.BranchGoodsFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;


public class BranchGoodsFragment extends MVVMListFragment<BranchGoodsFragmentVM, FragmentBranchGoodsBinding, BranchGoodsAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;
    @Inject
    public BranchGoodsFragment() {
        // Required empty public constructor
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_branch_goods;
    }


    @Override
    protected void setUI() {

    }

    @NonNull
    @Override
    protected BranchGoodsAdapter setAdapter(Function0 reTry) {
        BranchGoodsAdapter branchGoodsAdapter = new BranchGoodsAdapter(reTry);
        branchGoodsAdapter.setOnItemClick((view, ItemObject, position) -> skipTo(GoodsDetailsActivity.class, ItemObject.getObjectID()));
        branchGoodsAdapter.setOnAddClick((view, ItemObject, position) -> {
//            showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), null, ItemObject.getId());
//            getSpecification(ItemObject);
            mViewModel.addGoodsShopToCart(ItemObject.getObjectID(), 1, ItemObject.getObjectFeatureItemID1()).observe(this, beanResource -> {
                Object o = httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                    @Override
                    public void skipLoginActivity() {
                        skipTo(LoginActivity.class);
                    }

                    @Override
                    public void reLoad() {
                        mViewModel.addGoodsShopToCart(ItemObject.getObjectID(), 1, ItemObject.getObjectFeatureItemID1());
                    }
                }, this);
                if (o != null) {
                    addBadge(1);
                    toast("添加成功");
                }
            });

        });
        return branchGoodsAdapter;
    }

    /**
     * 添加小红点
     *
     * @param qty 数量
     */
    private void addBadge(int qty) {
        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            int badgeNumber = mainActivity.badge.getBadgeNumber();
            mainActivity.badge.setBadgeNumber(badgeNumber + qty);
        }
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
    protected BranchGoodsFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(BranchGoodsFragmentVM.class);
    }


}
