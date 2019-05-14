package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.HotWordBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

import static com.zaomeng.zaomeng.utils.SystemParameter.siteID;

/**
 * Created by Sampson on 2019-04-25.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.SearchActivity}
 */
public class SearchViewModel extends BaseViewModel {
    private ApiService apiService;
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MutableLiveData<String> ldSearchWord = new MutableLiveData<>();

    public SearchViewModel(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @Override
    public void init(Object data) {

    }

    public void cancel() {
        action.setValue("cancel");
    }

    public LiveData<Resource<PageBean<HotWordBean>>> getHotWordList() {
        return apiService.getHotWordList(1, 100, siteID);
    }
}
