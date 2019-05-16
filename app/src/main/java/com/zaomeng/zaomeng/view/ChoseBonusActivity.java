package com.zaomeng.zaomeng.view;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityChoseBonusBinding;
import com.zaomeng.zaomeng.view.adapter.bonus.BonusAdapter;
import com.zaomeng.zaomeng.view.base.MVVMListActivity;
import com.zaomeng.zaomeng.view_model.ChoseBonusVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

import kotlin.jvm.functions.Function0;

/**
 * Created by Sampson on 2019-05-16.
 * FastAndroid
 */
public class ChoseBonusActivity extends MVVMListActivity<ChoseBonusVM, ActivityChoseBonusBinding, BonusAdapter> {
    @Inject
    ViewModelFactory viewModelFactory;

    @NonNull
    @Override
    protected RecyclerView setRecyclerView() {
        return mViewDataBinding.list;
    }

    @NonNull
    @Override
    protected BonusAdapter setAdapter(Function0 reTry) {
        BonusAdapter bonusAdapter = new BonusAdapter(reTry);
        bonusAdapter.setOnItemClick((view, ItemObject, position) -> {
            Intent intent = new Intent();
            intent.putExtra("DATA", ItemObject);
            setResult(0, intent);
            finish();
        });
        return bonusAdapter;
    }

    @Override
    protected SwipeRefreshLayout setSwipeRefreshLayout() {
        return mViewDataBinding.swipeRefresh;
    }

    @NonNull
    @Override
    protected ChoseBonusVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(ChoseBonusVM.class);
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "选择优惠券";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_chose_bonus;
    }
}
