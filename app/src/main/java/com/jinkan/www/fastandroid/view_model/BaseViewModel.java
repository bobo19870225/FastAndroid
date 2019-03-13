/*
 * Copyright (c) 2018. 代码著作权归卢声波所有。
 */

package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * Created by lushengbo on 2018/1/12.
 * MVVM ViewModel 基类
 * 这种写法存在问题，ViewModel不应该持有Activity
 */

public abstract class BaseViewModel extends AndroidViewModel {
    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * 开始时调用
     */
    public abstract void init();
}
