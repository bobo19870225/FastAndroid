package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.FragmentGoodsBinding;
import com.jinkan.www.fastandroid.model.repository.http.bean.FocusPictureListRowsBean;
import com.jinkan.www.fastandroid.utils.GlideImageLoader;
import com.jinkan.www.fastandroid.view.adapter.GoodsAdapter;
import com.jinkan.www.fastandroid.view.base.MVVMListFragment;
import com.jinkan.www.fastandroid.view_model.GoodsFragmentVM;
import com.jinkan.www.fastandroid.view_model.ViewModelFactory;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import kotlin.jvm.functions.Function0;


/**
 *
 *
 */
public class GoodsFragment extends MVVMListFragment<GoodsFragmentVM, FragmentGoodsBinding, GoodsAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;

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
    protected void setUI() {
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

    @NonNull
    @Override
    protected GoodsAdapter setAdapter(Function0 reTry) {
        GoodsAdapter goodsAdapter = new GoodsAdapter(reTry);
        goodsAdapter.setOnItemClick((view, ItemObject) -> skipTo(GoodsDetailsActivity.class, ItemObject));
        goodsAdapter.setOnAddClick((view, ItemObject) -> {
            MainActivity mainActivity = (MainActivity) getActivity();
            if (mainActivity != null) {
                int badgeNumber = mainActivity.badge.getBadgeNumber();
                mainActivity.badge.setBadgeNumber(badgeNumber + 1);
            }
        });
        return goodsAdapter;
    }

    @NonNull
    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
//        return null;
    }

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @Override
    protected GoodsFragmentVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(GoodsFragmentVM.class);
    }


}
