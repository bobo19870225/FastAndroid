package com.jinkan.www.fastandroid.view;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
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
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

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

    @Override
    protected void initUI() {
        setDialog();
        swipeRefreshLayout = mViewDataBinding.swipeRefresh;
        setBanner();
        GoodsWithTitleAdapter goodsAdapter = getGoodsWithTitleAdapter();
        setGoodsData(goodsAdapter);
        mViewDataBinding.swipeRefresh.setOnRefreshListener(() -> setGoodsData(goodsAdapter));
    }

    private AlertDialog dialog;
    private RecyclerView recyclerView;
    private LinearLayout root;
    private ProgressBar loading;
    private Button ok;
    private void setDialog() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_goods_specification, null, false);
        root = view.findViewById(R.id.root);
        loading = view.findViewById(R.id.loading);
        recyclerView = view.findViewById(R.id.list);
        ok = view.findViewById(R.id.ok);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        dialog = builder.setView(view).create();

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
//            showLoadingDialog();
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

    private int oldPosition = -1;
    private void showSpecificationDialog(List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList) {
        if (itemList == null) {
            root.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            root.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
            FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getContext());
            layoutManager.setFlexWrap(FlexWrap.WRAP);
            layoutManager.setFlexDirection(FlexDirection.ROW);
            layoutManager.setAlignItems(AlignItems.STRETCH);
            layoutManager.setJustifyContent(JustifyContent.FLEX_START);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(new CommonAdapter<SpecificationsBean.BodyBean.DataBean.ItemListBean>(getContext(), R.layout.flexbox_item_text, itemList) {
                @Override
                protected void convert(ViewHolder holder, SpecificationsBean.BodyBean.DataBean.ItemListBean itemListBean, int position) {

                    TextView te = holder.getView(R.id.imageview);
                    te.setText(itemListBean.getObjectFeatureItemName());
                    if (oldPosition == position) {
                        te.setBackground(getResources().getDrawable(R.drawable.bg_button_them_color));
                    } else {
                        te.setBackground(getResources().getDrawable(R.drawable.button_them_color_un_select));
                    }
                    te.setOnClickListener(v -> {
                                oldPosition = position;
                                notifyDataSetChanged();
                            }
                    );
                    ViewGroup.LayoutParams lp = te.getLayoutParams();
                    if (lp instanceof FlexboxLayoutManager.LayoutParams) {
                        FlexboxLayoutManager.LayoutParams flexBoxLp = (FlexboxLayoutManager.LayoutParams) lp;
                        flexBoxLp.setFlexGrow(1.0f);
                    }

                }

            });
            ok.setOnClickListener(v -> {
                //TODO:选择规格后的逻辑
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            });
        }
        dialog.setOnDismissListener(dialog -> oldPosition = -1);
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
