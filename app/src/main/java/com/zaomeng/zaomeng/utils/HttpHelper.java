package com.zaomeng.zaomeng.utils;

import android.content.Context;
import android.widget.Toast;

import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageDataBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Sampson on 2019-04-24.
 * FastAndroid
 */
@Singleton
public class HttpHelper<T> {
    private Context context;

    @Inject
    public HttpHelper(Context context) {
        this.context = context;
    }

    public T AnalyticalData(Resource<? extends Bean<T>> data) {
        if (data.isSuccess()) {
            Bean<T> resource = data.getResource();
            if (resource != null && resource.getHeader().getCode() == 0) {
                return resource.getBody().getData();
            } else {
                if (resource != null) {
                    Toast.makeText(context, resource.getHeader().getMsg(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Throwable error = data.getError();
            if (error != null) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return null;
    }

    public PageDataBean<T> AnalyticalPageData(Resource<? extends PageBean<T>> data) {
        if (data.isSuccess()) {
            PageBean<T> pageBean = data.getResource();
            if (pageBean != null && pageBean.getHeader().getCode() == 0) {
                return pageBean.getBody().getData();
            } else {
                if (pageBean != null) {
                    Toast.makeText(context, pageBean.getHeader().getMsg(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Throwable error = data.getError();
            if (error != null) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return null;
    }
}
