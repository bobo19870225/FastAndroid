package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.MessageTypeBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;

/**
 * Created by Sampson on 2019-05-22.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.MessageTypeActivity}
 */
public class MessageTypeVM extends BaseViewModel {
    private ApiService apiService;

    public MessageTypeVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public LiveData<Resource<PageBean<MessageTypeBean>>> getMessageTypeList() {
        return apiService.getNodeCategoryList("fa7c2854-8b56-492b-b542-58a4ab2a7357",
                "402892e96a5895ef016a5896b76b0002",
                SharedPreferencesUtils.getMemberID(getApplication()));
    }
}
