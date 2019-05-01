package com.zaomeng.zaomeng.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentCommonlyUsedBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.utils.specification.InterfaceShowSpecification;
import com.zaomeng.zaomeng.utils.specification.ShowSpecificationHelper;
import com.zaomeng.zaomeng.view.adapter.collect.CollectAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListFragment;
import com.zaomeng.zaomeng.view_model.CommonlyUsedFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;


public class CommonlyUsedFragment extends MVVMListFragment<CommonlyUsedFragmentVM, FragmentCommonlyUsedBinding, CollectAdapter> implements InterfaceShowSpecification {
    @Inject
    ViewModelFactory viewModelFactory;
    private CollectAdapter collectAdapter;
    private ShowSpecificationHelper showSpecificationHelper;
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
        Context context = getContext();
        if (context != null) {
            showSpecificationHelper = new ShowSpecificationHelper(context, this);
        }
    }

    @NonNull
    @Override
    protected CollectAdapter setAdapter(Function0 reTry) {
        collectAdapter = new CollectAdapter(reTry);
        collectAdapter.setOnItemClick((view, ItemObject, position) -> skipTo(GoodsDetailsActivity.class, ItemObject.getObjectID()));
        collectAdapter.setOnAddClick((view, ItemObject, position) -> {
            showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), null, ItemObject.getObjectID());
            getSpecification(ItemObject);
        });
        return collectAdapter;
    }

    private void getSpecification(CollectInfoBean ItemObject) {
        mViewModel.getObjectFeatureItemList(ItemObject.getObjectID()).observe(this, specificationsBeanResource -> {
            if (specificationsBeanResource.isSuccess()) {
                SpecificationsBean resource = specificationsBeanResource.getResource();
                if (resource != null && resource.getHeader().getCode() == 0) {
                    List<SpecificationsBean.BodyBean.DataBean> data = resource.getBody().getData();
                    if (data.size() == 0) {
                        int qty = 1;
                        LiveData<Resource<Bean<String>>> addGoodsShopToCart = mViewModel.addGoodsShopToCart(ItemObject.getObjectID(), qty, null);
                        if (addGoodsShopToCart != null) {
                            addGoodsShopToCart.observe(this, beanResource -> {
                                BodyBean<String> addToShopCartBean = new HttpHelper<String>(getContext()).AnalyticalDataBody(beanResource);
                                addBadge(addToShopCartBean.getQty());
                            });
                        }

                    } else {
                        SpecificationsBean.BodyBean.DataBean dataBean = data.get(0);
                        List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList = dataBean.getItemList();
                        showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), itemList, ItemObject.getObjectID());
                    }

                } else {
                    if (resource != null) {
                        toast(resource.getHeader().getMsg());
                    }
                }
            } else {
                Throwable error = specificationsBeanResource.getError();
                if (error != null) {
                    toast(error.toString());
                }
            }
        });
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
        return ViewModelProviders.of(this, viewModelFactory).get(CommonlyUsedFragmentVM.class);
    }


    @Override
    public void toast(String msg) {
        super.toast(msg);
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

    @Override
    public void callBack(String objectID, int qty, String objectFeatureItemID) {
        LiveData<Resource<Bean<String>>> resourceLiveData = mViewModel.addGoodsShopToCart(objectID, qty, objectFeatureItemID);
        if (resourceLiveData != null) {
            resourceLiveData.observe(this, beanResource -> {
                BodyBean<String> stringBodyBean = new HttpHelper<String>(getContext()).AnalyticalDataBody(beanResource);
                if (stringBodyBean != null) {
                    addBadge(stringBodyBean.getQty());
                }
            });
        }
    }
}
