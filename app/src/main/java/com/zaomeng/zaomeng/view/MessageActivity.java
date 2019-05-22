package com.zaomeng.zaomeng.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityMessageBinding;
import com.zaomeng.zaomeng.view.adapter.message.MessageAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.MessageVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 */
public class MessageActivity extends MVVMListActivity<MessageVM, ActivityMessageBinding, MessageAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;
    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @NonNull
    @Override
    protected MessageAdapter setAdapter(Function0 reTry) {
        return new MessageAdapter(reTry);
    }

    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected MessageVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(MessageVM.class);
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        String[] strings = (String[]) transferData;
        return strings[0];
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_message;
    }
}
