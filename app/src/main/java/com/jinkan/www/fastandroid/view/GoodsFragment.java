package com.jinkan.www.fastandroid.view;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.FragmentGoodsBinding;
import com.jinkan.www.fastandroid.model.repository.http.bean.FocusPictureListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.NavigatorBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.PageBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.PageDataBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.SpecificationsBean;
import com.jinkan.www.fastandroid.utils.GlideImageLoader;
import com.jinkan.www.fastandroid.view.adapter.GoodsWithTitleAdapter;
import com.jinkan.www.fastandroid.view.adapter.Item;
import com.jinkan.www.fastandroid.view.base.MVVMFragment;
import com.jinkan.www.fastandroid.view.custom_view.GridItemDecoration;
import com.jinkan.www.fastandroid.view.custom_view.RoundAngleBanner;
import com.jinkan.www.fastandroid.view_model.GoodsFragmentVM;
import com.jinkan.www.fastandroid.view_model.ViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


/**
 *
 *
 */
public class GoodsFragment extends MVVMFragment<GoodsFragmentVM, FragmentGoodsBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    private List<Item> list;
    private List<NavigatorBean> rows;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Inject
    public GoodsFragment() {
        // Required empty public constructor
    }


//    @Override
//    protected String setToolBarTitle() {
//        return null;
//    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_goods;
    }


    @Override
    protected Object getData() {
        return getActivity() == null ? null : ((MainActivity) getActivity()).transferData;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initUI() {
        builder = new AlertDialog.Builder(getContext());
        setDialog();
        swipeRefreshLayout = mViewDataBinding.swipeRefresh;
        setBanner();
        GoodsWithTitleAdapter goodsAdapter = getGoodsWithTitleAdapter();
        setGoodsData(goodsAdapter);
        mViewDataBinding.swipeRefresh.setOnRefreshListener(() -> setGoodsData(goodsAdapter));
    }

    private AlertDialog dialog;
    private AlertDialog.Builder builder;
    private LinearLayout progressBar;
    private RelativeLayout root;

    private void setDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_goods_specification, null, false);
        dialog = builder.setView(view).create();
        progressBar = view.findViewById(R.id.loading);
        root = view.findViewById(R.id.root);
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
        GoodsWithTitleAdapter goodsAdapter = new GoodsWithTitleAdapter(mViewModel.function0);
        goodsAdapter.setOnItemClick((view, ItemObject) -> skipTo(GoodsDetailsActivity.class, ((Item<NavigatorBean.GoodsListBean>) ItemObject).getData()));
        goodsAdapter.setOnAddClick((view, ItemObject) -> {
            showSpecificationDialog(null);
            getSpecification(ItemObject);


        });
        RecyclerView list = mViewDataBinding.list;
        list.setLayoutManager(new GridLayoutManager(getContext(), 6));
        GridItemDecoration divider = new GridItemDecoration.Builder(getContext())
                .setHorizontalSpan(R.dimen.line)
                .setVerticalSpan(R.dimen.line)
                .setColorResource(R.color.bg_color)
                .setShowLastLine(true)
                .build();
        list.addItemDecoration(divider);
        list.setAdapter(goodsAdapter);
        return goodsAdapter;
    }

    private void getSpecification(NavigatorBean.GoodsListBean ItemObject) {
        mViewModel.getObjectFeatureItemList(ItemObject.getObjectID()).observe(this, specificationsBeanResource -> {
            if (specificationsBeanResource.isSuccess()) {
                SpecificationsBean resource = specificationsBeanResource.getResource();
                if (resource != null && resource.getHeader().getCode() == 0) {
                    List<SpecificationsBean.BodyBean.DataBean> data = resource.getBody().getData();
                    if (data.size() == 0) {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        if (mainActivity != null) {
                            int badgeNumber = mainActivity.badge.getBadgeNumber();
                            mainActivity.badge.setBadgeNumber(badgeNumber + 1);

                        }
                    } else {
                        SpecificationsBean.BodyBean.DataBean dataBean = data.get(0);
                        List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList = dataBean.getItemList();
                        //弹选择框
                        showSpecificationDialog(itemList);
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


    private void showSpecificationDialog(List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList) {
        if (itemList == null) {
            progressBar.setVisibility(View.VISIBLE);
//            root.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
//            root.setVisibility(View.VISIBLE);
        }
        if (!dialog.isShowing()) {
            dialog.show();
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
    protected GoodsFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(GoodsFragmentVM.class);
    }


}
