package com.zaomeng.zaomeng.view;

import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivitySearchBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.HotWordBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.utils.HttpHelper;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.SearchViewModel;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-04-25.
 * FastAndroid
 */
public class SearchActivity extends MVVMActivity<SearchViewModel, ActivitySearchBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    private int oldPosition = -1;
    private String searchWord;
    @NonNull
    @Override
    protected SearchViewModel createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
    }

    @Override
    protected void setView() {
        mViewDataBinding.search.clearFocus();
        mViewModel.action.observe(this, s -> {
            if (s.equals("cancel")) {
                finish();
            } else if (s.contains("toast:")) {
                toast(s.replaceAll("toast:", ""));
            } else if (s.equals("search")) {
                skipTo(SearchGoodsListActivity.class, mViewModel.ldSearchWord.getValue());
            }
        });
        mViewModel.getHotWordList().observe(this, pageBeanResource -> {
                    PageDataBean<HotWordBean> hotWordBeanPageDataBean = new HttpHelper<HotWordBean>(getApplicationContext()).AnalyticalPageData(pageBeanResource);
                    if (hotWordBeanPageDataBean != null) {
                        List<HotWordBean> rows = hotWordBeanPageDataBean.getRows();
                        if (rows != null) {
                            initListView(rows);
                        }
                    }

        });
        mViewDataBinding.search.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_SEARCH || id == EditorInfo.IME_NULL) {
                mViewModel.search();
                return true;
            }
            return false;
        });
    }

    private void initListView(List<HotWordBean> rows) {
        RecyclerView listHot = mViewDataBinding.listHot;
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getApplicationContext());
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        listHot.setLayoutManager(layoutManager);
        listHot.setAdapter(new CommonAdapter<HotWordBean>(getApplicationContext(), R.layout.flexbox_item_text, rows) {


            @Override
            protected void convert(ViewHolder holder, HotWordBean hotWordBean, int position) {

                TextView te = holder.getView(R.id.imageview);
                te.setText(hotWordBean.getName());
                if (oldPosition == position) {
                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_select));
                } else {
                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_un_select));
                }
                te.setOnClickListener(v -> {
                            searchWord = hotWordBean.getName();
                            mViewModel.ldSearchWord.setValue(searchWord);
                            oldPosition = position;
                            notifyDataSetChanged();
                        }
                );
                ViewGroup.LayoutParams lp = te.getLayoutParams();
                if (lp instanceof FlexboxLayoutManager.LayoutParams) {
                    FlexboxLayoutManager.LayoutParams flexBoxLp = (FlexboxLayoutManager.LayoutParams) lp;
                    flexBoxLp.width = hotWordBean.getName().getBytes().length * 32;
//                        flexBoxLp.setFlexGrow(1.0f);
//                        flexBoxLp.setFlexGrow(itemListBean.getObjectFeatureItemName().length());
                }

            }


        });
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_search;
    }
}
