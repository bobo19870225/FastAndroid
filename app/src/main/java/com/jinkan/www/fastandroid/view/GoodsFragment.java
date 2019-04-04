package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.FragmentGoodsBinding;
import com.jinkan.www.fastandroid.utils.GlideImageLoader;
import com.jinkan.www.fastandroid.view.base.MVVMListFragment;
import com.jinkan.www.fastandroid.view_model.GoodsFragmentVM;
import com.youth.banner.Banner;

import java.util.ArrayList;

import javax.inject.Inject;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import kotlin.jvm.functions.Function0;


/**
 *
 *
 */
public class GoodsFragment extends MVVMListFragment<GoodsFragmentVM, FragmentGoodsBinding, GoodsAdapter> {

    @Inject
    public GoodsFragment() {
        // Required empty public constructor
    }


    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_goods;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected void setUI() {
//放图片地址的集合
        ArrayList<String> list_path = new ArrayList<>();
        //放标题的集合
//        list_title = new ArrayList<>();

        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic21363tj30ci08ct96.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic259ohaj30ci08c74r.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2b16zuj30ci08cwf4.jpg");
        list_path.add("http://ww4.sinaimg.cn/large/006uZZy8jw1faic2e7vsaj30ci08cglz.jpg");
        Banner banner = mViewDataBinding.banner;
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list_path);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    protected GoodsAdapter setAdapter(Function0 reTry) {
        return new GoodsAdapter(reTry);
    }

    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @Override
    protected GoodsFragmentVM createdViewModel() {
        return null;
    }


}
