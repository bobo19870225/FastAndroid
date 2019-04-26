package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.GoodsPageKeyRepository;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class SortFragmentVM extends ListViewModel<GoodsListRowsBean> {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    private ApiService apiService;
    private GoodsPageKeyRepository goodsPageKeyRepository;

    public SortFragmentVM(@NonNull Application application, ApiService apiService, GoodsPageKeyRepository goodsPageKeyRepository) {
        super(application);
        this.apiService = apiService;
        this.goodsPageKeyRepository = goodsPageKeyRepository;

    }

    @Override
    protected Listing<GoodsListRowsBean> getListing(Object data) {
        return goodsPageKeyRepository.post(new String[]{(String) data, null}, 10);
    }

    public LiveData<Resource<PageBean<GoodsSuperBean>>> getNodeCategoryList() {
        return apiService.getNodeCategoryList("c82678b8ea0149c18fe6ac5ac8590d73", 1);
    }

    public void search() {
        action.setValue("search");
    }
    @Override
    public void init(Object data) {

    }
}

