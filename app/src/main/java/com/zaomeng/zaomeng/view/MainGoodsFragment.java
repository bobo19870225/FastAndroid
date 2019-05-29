package com.zaomeng.zaomeng.view;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentGoodsBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.FocusPictureListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.GlideImageLoader;
import com.zaomeng.zaomeng.utils.GlideUtils;
import com.zaomeng.zaomeng.view.adapter.GoodsWithTitleAdapter;
import com.zaomeng.zaomeng.view.adapter.Item;
import com.zaomeng.zaomeng.view.base.MVVMFragment;
import com.zaomeng.zaomeng.view.custom_view.GridItemDecoration;
import com.zaomeng.zaomeng.view.custom_view.RoundAngleBanner;
import com.zaomeng.zaomeng.view_model.MainGoodsFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;


/**
 *
 */
public class MainGoodsFragment extends MVVMFragment<MainGoodsFragmentVM, FragmentGoodsBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    GlideUtils glideUtils;
    @Inject
    HttpHelper httpHelper;
    private List<Item> list;
    private List<NavigatorBean> rows;
    private SwipeRefreshLayout swipeRefreshLayout;

    //    private ShowSpecificationHelper showSpecificationHelper;
    @Inject
    public MainGoodsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_goods;
    }

    @Override
    protected Object getData() {
        return getActivity() == null ? null : "6ba58046-7eb2-4f11-bbb3-b934abeb29a8";
    }

    @Override
    protected void initUI() {
        swipeRefreshLayout = mViewDataBinding.swipeRefresh;
        swipeRefreshLayout.setColorSchemeResources(R.color.them_color);
        setBanner();
        GoodsWithTitleAdapter goodsAdapter = getGoodsWithTitleAdapter();
        setGoodsData(goodsAdapter);
        mViewDataBinding.swipeRefresh.setOnRefreshListener(() -> setGoodsData(goodsAdapter));
//        Context context = getContext();
//        if (context != null) {
//            showSpecificationHelper = new ShowSpecificationHelper(context, this);
//        }
    }


    private void setGoodsData(GoodsWithTitleAdapter goodsAdapter) {
        mViewModel.getNodeNavigatorList().observe(this, pageBeanResource -> {
            swipeRefreshLayout.setRefreshing(false);
            if (pageBeanResource.isSuccess()) {
                list = new ArrayList<>();
                PageBean<NavigatorBean> resource = pageBeanResource.getResource();
                PageDataBean<NavigatorBean> pageDataBean;
                if (resource != null) {
                    pageDataBean = resource.getBody().getData();
                    if (pageDataBean != null) {
                        rows = pageDataBean.getRows();
                        for (NavigatorBean navigatorBean : rows
                        ) {
                            list.add(new Item<>(navigatorBean.getName(), 0));
                            List<NavigatorBean.GoodsListBean> goodsList = navigatorBean.getGoodsList();
                            if (navigatorBean.getName().equals("推荐")) {
                                for (NavigatorBean.GoodsListBean goodsListBean : goodsList
                                ) {
                                    list.add(new Item<>(goodsListBean, 1));
                                }
                            } else {
                                list.add(new Item<>(navigatorBean.getLargeIcon(), 2));
                                for (NavigatorBean.GoodsListBean goodsListBean : goodsList
                                ) {
                                    list.add(new Item<>(goodsListBean, 3));
                                }
                            }

                        }
                        goodsAdapter.setDate(list);
                    }
                }
            }
        });
    }

    private GoodsWithTitleAdapter getGoodsWithTitleAdapter() {
        GoodsWithTitleAdapter goodsAdapter = new GoodsWithTitleAdapter(mViewModel.function0, glideUtils);
        goodsAdapter.setOnItemClick((view, ItemObject, position) -> {
            if (ItemObject instanceof String) {
                MainFragment parentFragment = (MainFragment) MainGoodsFragment.this.getParentFragment();
                if (parentFragment != null) {
                    //导航跳转
                    parentFragment.setSelectedPosition((String) ItemObject);
                }
            } else {
                MainGoodsFragment.this.skipTo(GoodsDetailsActivity.class, ((NavigatorBean.GoodsListBean) ItemObject).getObjectID());
            }
        });

        goodsAdapter.setOnAddClick((view, ItemObject, position) -> {
//            //null的时候显示加载状态
//            showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), null, ItemObject.getObjectID());

            MainGoodsFragment.this.getSpecification(ItemObject);
        });
        goodsAdapter.setOnHeaderItemClick((view, ItemObject, position) -> {
            switch (ItemObject) {
                case "commonlyUsed":
                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        ((MainActivity) activity).navigation.setSelectedItemId(R.id.treeFragment);
                    }
                    break;
                case "myOrder":
                    skipTo(OrderActivity.class, null);
                    break;
                case "myBonus":
                    skipTo(BonusActivity.class, null);
                    break;
                case "signIn":
                    skipTo(CalendarActivity.class, null);
                    break;
            }
        });
        RecyclerView list = mViewDataBinding.list;
        list.setLayoutManager(new GridLayoutManager(getContext(), 6));
        GridItemDecoration divider = new GridItemDecoration.Builder(Objects.requireNonNull(getContext()))
                .setHorizontalSpan(R.dimen.line)
                .setVerticalSpan(R.dimen.line)
                .setColorResource(R.color.bg_color)
                .setShowLastLine(true)
                .build();
        list.addItemDecoration(divider);
        list.setAdapter(goodsAdapter);
        return goodsAdapter;
    }

    @SuppressWarnings("unchecked")
    private void getSpecification(NavigatorBean.GoodsListBean ItemObject) {
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
                                BodyBean<String> addToShopCartBean = httpHelper.AnalyticalDataBody(beanResource, new InterfaceLogin() {
                                    @Override
                                    public void skipLoginActivity() {
                                        skipTo(LoginActivity.class, null);
                                    }

                                    @Override
                                    public void reLoad() {
                                        mViewModel.getObjectFeatureItemList(ItemObject.getObjectID());
                                    }
                                }, this);
                                addBadge(addToShopCartBean.getQty());
                            });
                        }

                    }
//                    else {
//                        SpecificationsBean.BodyBean.DataBean dataBean = data.get(0);
//                        List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList = dataBean.getItemList();
//                        showSpecificationHelper.showSpecificationDialog(getLayoutInflater(), itemList, ItemObject.getObjectID());
//                    }

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

    private void setBanner() {
        RoundAngleBanner banner = mViewDataBinding.banner;
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        ArrayList<String> list_path = new ArrayList<>();
        mViewModel.getFocusPictureList().observe(this, pageBeanResource -> {
            if (pageBeanResource.isSuccess()) {
                if (pageBeanResource.getResource() != null && pageBeanResource.getResource().getHeader().getCode() == 0) {
                    List<FocusPictureListRowsBean> rows = pageBeanResource.getResource().getBody().getData().getRows();
                    for (FocusPictureListRowsBean focusPictureListRowsBean : rows
                    ) {
                        list_path.add(focusPictureListRowsBean.getPicturePath());
                    }
                    //设置图片集合
                    banner.setImages(list_path);
                    //banner设置方法全部调用完毕时最后调用
                    banner.start();
                }
            }
        });
    }


    @Override
    protected MainGoodsFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(MainGoodsFragmentVM.class);
    }


    @Override
    public void toast(String msg) {
        super.toast(msg);
    }

//    @Override
//    public void callBack(String objectID, int qty, String objectFeatureItemID) {
//        LiveData<Resource<Bean<String>>> resourceLiveData = mViewModel.addGoodsShopToCart(objectID, qty, objectFeatureItemID);
//        if (resourceLiveData != null) {
//            resourceLiveData.observe(this, beanResource -> {
//                BodyBean<String> stringBodyBean = new HttpHelper<String>(getContext()).AnalyticalDataBody(beanResource);
//                if (stringBodyBean != null) {
//                    addBadge(stringBodyBean.getQty());
//                }
//            });
//        }
//    }

//    @Override
//    public void getPrice(String objectFeatureItemID) {
//        mViewModel.getPrice(objectFeatureItemID).observe(this, beanResource -> {
//            PriceBean priceBean = new HttpHelper<PriceBean>(getContext()).AnalyticalData(beanResource);
//            showSpecificationHelper.setPrice(priceBean.getShowPrice());
//        });
//    }
}
