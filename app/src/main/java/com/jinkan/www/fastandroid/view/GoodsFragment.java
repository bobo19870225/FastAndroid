package com.jinkan.www.fastandroid.view;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.FragmentGoodsBinding;
import com.jinkan.www.fastandroid.model.repository.http.bean.FocusPictureListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.NavigatorBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.PageBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.PageDataBean;
import com.jinkan.www.fastandroid.utils.GlideImageLoader;
import com.jinkan.www.fastandroid.view.adapter.GoodsWithTitleAdapter;
import com.jinkan.www.fastandroid.view.adapter.Item;
import com.jinkan.www.fastandroid.view.base.MVVMFragment;
import com.jinkan.www.fastandroid.view_model.GoodsFragmentVM;
import com.jinkan.www.fastandroid.view_model.ViewModelFactory;
import com.youth.banner.Banner;

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
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected Object getData() {
        return getActivity() == null ? null : ((MainActivity) getActivity()).transferData;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void initUI() {
        setBanner();
        GoodsWithTitleAdapter goodsAdapter = getGoodsWithTitleAdapter();
        setGoodsData(goodsAdapter);
    }

    private void setGoodsData(GoodsWithTitleAdapter goodsAdapter) {
        mViewModel.getNodeNavigatorList().observe(this, pageBeanResource -> {
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
        goodsAdapter.setOnItemClick((view, ItemObject) -> skipTo(GoodsDetailsActivity.class, ((GoodsListRowsBean) ItemObject).getId()));
        goodsAdapter.setOnAddClick((view, ItemObject) -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            if (mainActivity != null) {
                int badgeNumber = mainActivity.badge.getBadgeNumber();
                mainActivity.badge.setBadgeNumber(badgeNumber + 1);
            }
        });
        mViewDataBinding.list.setLayoutManager(new GridLayoutManager(getContext(), 6));
        mViewDataBinding.list.setAdapter(goodsAdapter);
        return goodsAdapter;
    }

    private void setBanner() {
        Banner banner = mViewDataBinding.banner;
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
