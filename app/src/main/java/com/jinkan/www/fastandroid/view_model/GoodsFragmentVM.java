package com.jinkan.www.fastandroid.view_model;

import android.app.Application;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.bean.FocusPictureListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.PageBean;
import com.jinkan.www.fastandroid.model.repository.http.by_page.GoodsPageKeyRepository;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.Resource;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import static com.jinkan.www.fastandroid.utils.SystemParameter.focusID;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 */
public class GoodsFragmentVM extends ListViewModel<GoodsListRowsBean> {
    private GoodsPageKeyRepository goodsPageKeyRepository;
    private ApiService apiService;

    public GoodsFragmentVM(@NonNull Application application, GoodsPageKeyRepository goodsPageKeyRepository, ApiService apiService) {
        super(application);
        this.goodsPageKeyRepository = goodsPageKeyRepository;
        this.apiService = apiService;
    }

    @Override
    protected Listing<GoodsListRowsBean> getListing(Object data) {
        return goodsPageKeyRepository.post((String[]) data, 10);
    }

    public LiveData<Resource<PageBean<FocusPictureListRowsBean>>> getFocusPictureList() {
        return apiService.getFocusPictureList(null, null, focusID);
    }
}
