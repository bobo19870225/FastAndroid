package com.zaomeng.zaomeng.view.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.zaomeng.zaomeng.R;

/**
 * Created by Sampson on 2019/4/3.
 * FastAndroid
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    protected Object transferData = null;

    /**
     * onAttach()：执行该方法时，Fragment与Activity已经完成绑定，
     * 该方法有一个Activity类型的参数，代表绑定的Activity，
     * 这时候你可以执行诸如mActivity = activity的操作。
     *
     * @param context Activity
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    /**
     * onCreate()：初始化Fragment。
     *
     * @param savedInstanceState 可通过参数savedInstanceState获取之前保存的值
     */
    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTransferData();
    }

    private void getTransferData() {
        if (getArguments() != null)
            transferData = getArguments().get("DATA");
    }

    /**
     * onCreateView()：初始化Fragment的布局。
     * 加载布局和findViewById的操作通常在此函数内完成，
     * 但是不建议执行耗时的操作，比如读取数据库数据列表。
     *
     * @return rootView
     */
    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        rootView = setRootView(inflater, container, setLayoutRes());
        return rootView;
    }

    private void setToolBar() {
        Toolbar toolbar = rootView.findViewById(R.id.tool_bar);
        if (toolbar != null) {
            if (setToolBarTitle() != null)
                toolbar.setTitle(setToolBarTitle());
            AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            if (appCompatActivity != null) {
                (appCompatActivity).setSupportActionBar(toolbar);
            }
            toolbar.setNavigationOnClickListener(view -> {
                //TODO 回退
//                onBackPressed();
            });
        }
    }

    protected abstract String setToolBarTitle();

    protected View setRootView(LayoutInflater inflater, ViewGroup container, int setLayoutRes) {
        return inflater.inflate(setLayoutRes, container, false);
    }

    protected abstract @LayoutRes
    int setLayoutRes();

    public void toast(String msg) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_LONG).show();
    }

    /**
     * onActivityCreated()：执行该方法时，
     * 与Fragment绑定的Activity的onCreate方法已经执行完成并返回，
     * 在该方法内可以进行与Activity交互的UI操作，
     * 所以在该方法之前Activity的onCreate方法并未执行完成，
     * 如果提前进行交互操作，会引发空指针异常。
     *
     * @param savedInstanceState 可通过参数savedInstanceState获取之前保存的值
     */
    @Override
    public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setToolBar();
        initData(transferData);
        initUI();
    }

    protected abstract void initUI();

    protected abstract void initData(Object transferData);

    /**
     * onStart()：执行该方法时，Fragment由不可见变为可见状态。
     */
    @Override
    public void onStart() {
        super.onStart();

    }

    /**
     * onResume()：执行该方法时，Fragment处于活动状态，用户可与之交互。
     */
    @Override
    public void onResume() {
        super.onResume();

    }
}
