package com.zaomeng.zaomeng.view.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.MenuRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.zaomeng.zaomeng.R;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Sampson on 2019/3/12.
 * FastAndroid
 * 和{@link BaseActivity}一样的，只不过是使用Dagger注入依赖
 */
public abstract class BaseDaggerActivity extends DaggerAppCompatActivity {
    public Object transferData;
    private @MenuRes
    int toolBarMenu = 0;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtil.setStatusBarLayoutStyle(this, false);
        setRootView();
        getTransferData();
        Toolbar toolbar = findViewById(R.id.tool_bar);
        initView();
        if (toolbar == null) return;
        toolbar.setTitle("");
        TextView title = findViewById(R.id.title);
        title.setText(setToolBarTitle());
        title.setTextSize(20);
        title.setTextColor(Color.parseColor("#333333"));
        toolBarMenu = setToolBarMenu();
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> {
            doClose();
        });
    }

    protected void doClose() {
        onBackPressed();
    }


    protected abstract @MenuRes
    int setToolBarMenu();

    protected abstract String setToolBarTitle();


    protected void setRootView() {
        View inflate = getLayoutInflater().inflate(setLayoutRes(), null, false);
        setContentView(inflate);
//        return inflate;
    }

    @Override
    public final boolean onCreateOptionsMenu(Menu menu) {
        if (toolBarMenu != 0) {
            getMenuInflater().inflate(toolBarMenu, menu);//加载menu文件到布局
        }
        return super.onCreateOptionsMenu(menu);

    }

    private void getTransferData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        if (bundle != null) {
            transferData = bundle.get("DATA");
        } else {
            transferData = null;
        }
    }

    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//        Snackbar.make(mRootView, msg, Snackbar.LENGTH_LONG).show();
    }
    /**
     * 跳转
     *
     * @param data   参数
     * @param mClass 类名
     */
    public void skipTo(Class<?> mClass, Object data) {
//        if (mClass.isAssignableFrom(BaseDaggerActivity.class)) {
            Intent intent = new Intent();
            skipTo(mClass, data, intent);
//        }
    }

    /**
     * 跳转
     *
     * @param data   参数
     * @param mClass 类名
     * @param isTop  是否关闭其它页面
     */
    public void skipTo(Class mClass, Object data, boolean isTop) {
        Intent intent = new Intent();
        intent.setClass(this, mClass);
        if (isTop) {
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        skipTo(mClass, data, intent);
    }

    private void skipTo(Class<?> mClass, Object data, Intent intent) {
        intent.setClass(this, mClass);
        if (data != null) {
            Bundle bundle = new Bundle();
            if (data instanceof String) {
                bundle.putString("DATA", String.valueOf(data));
            } else if (data instanceof Integer) {
                bundle.putInt("DATA", (Integer) data);
            } else if (data instanceof String[]) {
                bundle.putStringArray("DATA", (String[]) data);
            } else if (data instanceof Parcelable) {
                bundle.putParcelable("DATA", (Parcelable) data);
            }
            intent.putExtra("BUNDLE", bundle);
        }
        startActivity(intent);
    }


//    protected abstract @MenuRes
//    int setToolBarMenu();

    protected abstract @LayoutRes
    int setLayoutRes();

    protected abstract void initView();
}
