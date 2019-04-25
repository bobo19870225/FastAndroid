package com.jinkan.www.fastandroid.view.adapter;

import android.view.View;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public interface OnItemClick<T> {
    void onClick(View view, T ItemObject, int position);
}
