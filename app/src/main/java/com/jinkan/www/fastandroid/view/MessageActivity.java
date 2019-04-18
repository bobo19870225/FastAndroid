package com.jinkan.www.fastandroid.view;

import com.jinkan.www.fastandroid.R;
import com.jinkan.www.fastandroid.databinding.ActivityMessageBinding;
import com.jinkan.www.fastandroid.view.adapter.GoodsAdapter;
import com.jinkan.www.fastandroid.view.base.MVVMListActivity;
import com.jinkan.www.fastandroid.view_model.MessageVM;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class MessageActivity extends MVVMListActivity<MessageVM, ActivityMessageBinding, GoodsAdapter> {
    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @NonNull
    @Override
    protected GoodsAdapter setAdapter(Function0 reTry) {
        return new GoodsAdapter(reTry);
    }

    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected MessageVM createdViewModel() {
        return ViewModelProviders.of(this).get(MessageVM.class);
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "消息中心";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_message;
    }
}
