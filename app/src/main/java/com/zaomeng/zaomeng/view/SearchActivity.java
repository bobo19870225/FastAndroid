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
import com.zaomeng.zaomeng.model.repository.dataBase.HistorySearchKey;
import com.zaomeng.zaomeng.model.repository.dataBase.SearchDao;
import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.HotWordBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.SearchViewModel;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-04-25.
 * FastAndroid
 */
public class SearchActivity extends MVVMActivity<SearchViewModel, ActivitySearchBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    SearchDao searchDao;
    @Inject
    UserDao userDao;
    @Inject
    HttpHelper httpHelper;
    private String memberID;
    //    private int oldPosition = -1;
//    private int historyOldPosition = -1;
    private String searchWord;
    private List<HistorySearchKey> list;

    @NonNull
    @Override
    protected SearchViewModel createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void setView() {
        userDao.getAllUser().observe(this, loginBeans -> {
            memberID = loginBeans.get(0).getId();
            searchDao.getSearchKeyByMemberID(memberID).observe(this, historySearchKeys -> {
                list = historySearchKeys;
                initHistoryListView(list);
            });
        });
        mViewModel.getHotWordList().observe(this, pageBeanResource -> {
            PageDataBean<HotWordBean> hotWordBeanPageDataBean = httpHelper.AnalyticalPageData(pageBeanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {

                }
            }, this);
            if (hotWordBeanPageDataBean != null) {
                List<HotWordBean> rows = hotWordBeanPageDataBean.getRows();
                if (rows != null) {
                    initListView(rows);
                }
            }

        });
        mViewModel.action.observe(this, s -> {
            if (s.equals("cancel")) {
                finish();
            } else if (s.contains("toast:")) {
                toast(s.replaceAll("toast:", ""));
            } else if (s.equals("search")) {
                searchGoods();
            } else if (s.equals("clean")) {
                ExecutorService DB_IO = Executors.newFixedThreadPool(2);
                DB_IO.execute(() -> {
                    searchDao.clean();
                    DB_IO.shutdown();//关闭线程
                });

            }
        });
        mViewDataBinding.search.clearFocus();
        mViewDataBinding.search.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_SEARCH || id == EditorInfo.IME_NULL) {
                searchGoods();
                return true;
            }
            return false;
        });
    }

    private void searchGoods() {
        HistorySearchKey historySearchKey = new HistorySearchKey();
        String value = mViewModel.ldSearchWord.getValue();
        if (value != null && !value.equals("")) {
            historySearchKey.key = value;
            historySearchKey.memberID = memberID;
            skipTo(SearchGoodsListActivity.class, value);
            ExecutorService DB_IO = Executors.newFixedThreadPool(2);
            DB_IO.execute(() -> {
                searchDao.insertDate(historySearchKey);
                DB_IO.shutdown();//关闭线程
            });
        } else {
            toast("请填写搜索关键词");
        }
    }

    private void initListView(List<HotWordBean> rows) {
        //                if (oldPosition == position) {
        //                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_select));
        //                    te.setTextColor(getApplicationContext().getResources().getColor(R.color.text_white));
        //                } else {
        //                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_un_select));
        //                    te.setTextColor(getApplicationContext().getResources().getColor(R.color.text_main));
        //                }
        //                            oldPosition = position;
        //                            historyOldPosition = -1;
        //                            notifyList();
        //                        flexBoxLp.setFlexGrow(1.0f);
        //                        flexBoxLp.setFlexGrow(itemListBean.getObjectFeatureItemName().length());
        CommonAdapter<HotWordBean> hotWordBeanCommonAdapter = new CommonAdapter<HotWordBean>(getApplicationContext(), R.layout.flexbox_item_text, rows) {
            @Override
            protected void convert(ViewHolder holder, HotWordBean hotWordBean, int position) {

                TextView te = holder.getView(R.id.imageview);
                te.setText(hotWordBean.getName());
//                if (oldPosition == position) {
//                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_select));
//                    te.setTextColor(getApplicationContext().getResources().getColor(R.color.text_white));
//                } else {
//                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_un_select));
//                    te.setTextColor(getApplicationContext().getResources().getColor(R.color.text_main));
//                }
                te.setOnClickListener(v -> {
                            searchWord = hotWordBean.getName();
                            mViewModel.ldSearchWord.setValue(searchWord);
//                            oldPosition = position;
//                            historyOldPosition = -1;
//                            notifyList();
                            searchGoods();
                        }
                );
                ViewGroup.LayoutParams lp = te.getLayoutParams();
                if (lp instanceof FlexboxLayoutManager.LayoutParams) {
                    FlexboxLayoutManager.LayoutParams flexBoxLp = (FlexboxLayoutManager.LayoutParams) lp;
                    int w = hotWordBean.getName().getBytes().length * 32;
                    if (w < 128) {
                        w = 128;
                    }
                    flexBoxLp.width = w;
//                        flexBoxLp.setFlexGrow(1.0f);
//                        flexBoxLp.setFlexGrow(itemListBean.getObjectFeatureItemName().length());
                }

            }
        };
        RecyclerView listHot = mViewDataBinding.listHot;
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getApplicationContext());
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        listHot.setLayoutManager(layoutManager);
        listHot.setAdapter(hotWordBeanCommonAdapter);
    }

//    private void notifyList() {
//        if (historySearchKeyCommonAdapter != null)
//            historySearchKeyCommonAdapter.notifyDataSetChanged();
//        if (hotWordBeanCommonAdapter != null)
//            hotWordBeanCommonAdapter.notifyDataSetChanged();
//    }

    private void initHistoryListView(List<HistorySearchKey> rows) {
        //                if (historyOldPosition == position) {
        //                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_select));
        //                    te.setTextColor(getApplicationContext().getResources().getColor(R.color.text_white));
        //                } else {
        //                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_un_select));
        //                    te.setTextColor(getApplicationContext().getResources().getColor(R.color.text_main));
        //                }
        //                            historyOldPosition = position;
        //                            oldPosition = -1;
        //                            notifyList();
        //                        flexBoxLp.setFlexGrow(1.0f);
        //                        flexBoxLp.setFlexGrow(itemListBean.getObjectFeatureItemName().length());
        CommonAdapter<HistorySearchKey> historySearchKeyCommonAdapter = new CommonAdapter<HistorySearchKey>(getApplicationContext(), R.layout.flexbox_item_text, rows) {
            @Override
            protected void convert(ViewHolder holder, HistorySearchKey historySearchKey, int position) {
                TextView te = holder.getView(R.id.imageview);
                te.setText(historySearchKey.key);
//                if (historyOldPosition == position) {
//                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_select));
//                    te.setTextColor(getApplicationContext().getResources().getColor(R.color.text_white));
//                } else {
//                    te.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.button_them_color_un_select));
//                    te.setTextColor(getApplicationContext().getResources().getColor(R.color.text_main));
//                }
                te.setOnClickListener(v -> {
                            searchWord = historySearchKey.key;
                            mViewModel.ldSearchWord.setValue(searchWord);
//                            historyOldPosition = position;
//                            oldPosition = -1;
//                            notifyList();
                            searchGoods();
                        }
                );
                ViewGroup.LayoutParams lp = te.getLayoutParams();
                if (lp instanceof FlexboxLayoutManager.LayoutParams) {
                    FlexboxLayoutManager.LayoutParams flexBoxLp = (FlexboxLayoutManager.LayoutParams) lp;
                    int w = historySearchKey.key.getBytes().length * 32;
                    if (w < 128) {
                        w = 128;
                    }
                    flexBoxLp.width = w;
//                        flexBoxLp.setFlexGrow(1.0f);
//                        flexBoxLp.setFlexGrow(itemListBean.getObjectFeatureItemName().length());
                }

            }
        };
        RecyclerView listHistory = mViewDataBinding.list;
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getApplicationContext());
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        listHistory.setLayoutManager(layoutManager);
        listHistory.setAdapter(historySearchKeyCommonAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.ldSearchWord.setValue("");
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
