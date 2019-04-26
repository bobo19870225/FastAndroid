package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.FocusPictureListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

import static com.zaomeng.zaomeng.utils.SystemParameter.focusID;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 */
public class GoodsFragmentVM extends BaseViewModel {
    private ApiService apiService;
    public Function0 function0;

    GoodsFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
//        this.goodsPageKeyRepository = goodsPageKeyRepository;
        this.apiService = apiService;
        function0 = () -> {
            getFocusPictureList();
            return Unit.INSTANCE;
        };
    }


    public LiveData<Resource<PageBean<FocusPictureListRowsBean>>> getFocusPictureList() {
        return apiService.getFocusPictureList(null, null, focusID);
    }

    public LiveData<Resource<PageBean<NavigatorBean>>> getNodeNavigatorList() {
        return apiService.getNodeNavigatorList("076333d6bd284605ab2293fb698db804", 2, "422429993732");
    }

    @Override
    public void init(Object data) {

    }

    public LiveData<Resource<PageBean<GoodsListRowsBean>>> getGoodsList(String goodsCategoryID) {
        return apiService.getGoodsShopListForLiveData(1, 3, goodsCategoryID, "");
    }

    public LiveData<Resource<SpecificationsBean>> getObjectFeatureItemList(String objectID) {
        return apiService.getObjectFeatureItemList(objectID);
    }

}