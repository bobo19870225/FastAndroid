package com.zaomeng.zaomeng.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.FragmentGoodsBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.FocusPictureListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.GlideImageLoader;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.adapter.GoodsWithTitleAdapter;
import com.zaomeng.zaomeng.view.adapter.Item;
import com.zaomeng.zaomeng.view.base.MVVMFragment;
import com.zaomeng.zaomeng.view.custom_view.GridItemDecoration;
import com.zaomeng.zaomeng.view.custom_view.RoundAngleBanner;
import com.zaomeng.zaomeng.view_model.MainGoodsFragmentVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;


/**
 *
 *
 */
public class MainGoodsFragment extends MVVMFragment<MainGoodsFragmentVM, FragmentGoodsBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    private List<Item> list;
    private List<NavigatorBean> rows;
    private SwipeRefreshLayout swipeRefreshLayout;
    private BottomSheetDialog bottomSheetDialog;
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
        return getActivity() == null ? null : ((MainActivity) getActivity()).transferData;
    }

    @Override
    protected void initUI() {
        swipeRefreshLayout = mViewDataBinding.swipeRefresh;
        setBanner();
        GoodsWithTitleAdapter goodsAdapter = getGoodsWithTitleAdapter();
        setGoodsData(goodsAdapter);
        mViewDataBinding.swipeRefresh.setOnRefreshListener(() -> setGoodsData(goodsAdapter));
        bottomSheetDialog = new BottomSheetDialog(Objects.requireNonNull(getContext()));
        bottomSheetDialog.setCancelable(false);
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "commonlyUsed":
                    FragmentActivity activity = getActivity();
                    if (activity != null) {
                        ((MainActivity) activity).navigation.setSelectedItemId(R.id.treeFragment);
                    }
                    break;
                case "myOrder":
                    break;
                case "myCoupon":
                    break;
                case "myPoints":
                    break;
            }
        });
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
            MainGoodsFragment.this.showSpecificationDialog(null, ItemObject);
            MainGoodsFragment.this.getSpecification(ItemObject);
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
                                BodyBean<String> addToShopCartBean = new HttpHelper<String>(getContext()).AnalyticalDataBody(beanResource);
                                addBadge(addToShopCartBean.getQty());
                            });
                        }

                    } else {
                        SpecificationsBean.BodyBean.DataBean dataBean = data.get(0);
                        List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList = dataBean.getItemList();
                        showSpecificationDialog(itemList, ItemObject);
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

    private int oldPosition = -1;
    private String objectFeatureItemID;
    private String specificationName;

    private void showSpecificationDialog(List<SpecificationsBean.BodyBean.DataBean.ItemListBean> itemList, NavigatorBean.GoodsListBean itemObject) {

        //默认Cancelable和CanceledOnTouchOutside均为true
        //bsDialog.setCancelable(true);
        //bsDialog.setCanceledOnTouchOutside(true);
        //为Dialog设置布局
        LayoutInflater layoutInflater = getLayoutInflater();
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.dialog_goods_specification, null, false);
        LinearLayout root = view.findViewById(R.id.root);
        ProgressBar loading = view.findViewById(R.id.loading);
        //    private AlertDialog dialog;
        RecyclerView recyclerView = view.findViewById(R.id.list);
        Button ok = view.findViewById(R.id.ok);
        ImageView cancel = view.findViewById(R.id.cancel);
        cancel.setOnClickListener(v -> {
            if (bottomSheetDialog.isShowing())
                bottomSheetDialog.dismiss();
        });
        TextView number = view.findViewById(R.id.number);
        TextView add = view.findViewById(R.id.add);
        add.setOnClickListener(v -> {
            int sl = Integer.valueOf(number.getText().toString());
            sl += 1;
            number.setText(String.valueOf(sl));
        });
        TextView reduce = view.findViewById(R.id.reduce);
        reduce.setOnClickListener(v -> {
            int sl = Integer.valueOf(number.getText().toString());
            if (sl == 0)
                return;
            sl -= 1;
            number.setText(String.valueOf(sl));
        });
        bottomSheetDialog.setContentView(view);
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
                        objectFeatureItemID = itemListBean.getObjectFeatureItemID();
                        specificationName = itemListBean.getObjectFeatureItemName();
                        oldPosition = position;
                                notifyDataSetChanged();
                            }
                    );
                    ViewGroup.LayoutParams lp = te.getLayoutParams();
                    if (lp instanceof FlexboxLayoutManager.LayoutParams) {
                        FlexboxLayoutManager.LayoutParams flexBoxLp = (FlexboxLayoutManager.LayoutParams) lp;
                        flexBoxLp.width = itemListBean.getObjectFeatureItemName().getBytes().length * 32;
//                        flexBoxLp.setFlexGrow(1.0f);
//                        flexBoxLp.setFlexGrow(itemListBean.getObjectFeatureItemName().length());
                    }

                }

            });
            ok.setOnClickListener(v -> {
                int qty = Integer.parseInt(number.getText().toString());
                if (specificationName == null) {
                    toast("请选择规格");
                    return;
                }
                if (qty == 0) {
                    toast("请选择数量");
                    return;
                }
                LiveData<Resource<Bean<String>>> resourceLiveData = mViewModel.addGoodsShopToCart(itemObject.getObjectID(), qty, objectFeatureItemID);
                if (resourceLiveData != null) {
                    resourceLiveData.observe(this, beanResource -> {
                        BodyBean<String> stringBodyBean = new HttpHelper<String>(getContext()).AnalyticalDataBody(beanResource);
                        if (stringBodyBean != null) {
                            addBadge(stringBodyBean.getQty());
                        }
                    });
                }

                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
            });
        }
        bottomSheetDialog.setOnDismissListener(dialog -> oldPosition = -1);
        if (!bottomSheetDialog.isShowing()) {
            bottomSheetDialog.show();
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


}
