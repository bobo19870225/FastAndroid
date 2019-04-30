package com.zaomeng.zaomeng.view_model;

import android.app.Application;
import android.text.SpannableString;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsImageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.GoodsDetailsActivity}
 */
public class GoodsDetailsVM extends ListViewModel<Integer, GoodsDetailsImageBean> {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MediatorLiveData<Resource<Bean<GoodsDetailsBean>>> ldGoodsDetails = new MediatorLiveData<>();
    public final MutableLiveData<String> ldDescribe = new MutableLiveData<>();
    public final MutableLiveData<SpannableString> ldShowPrice = new MutableLiveData<>();
    public final MutableLiveData<String> ldShowName = new MutableLiveData<>();
    //    public String goodsName;
//    public String goodsId;
    private ApiService apiService;

    public GoodsDetailsVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }


    @Override
    public void init(Object data) {
        ldGoodsDetails.addSource(apiService.getGoodsShopDetail((String) data, null), ldGoodsDetails::setValue);
    }

    public void back() {
        action.setValue("back");
    }

    public void collect() {
        action.setValue("addCollect");
    }

    public LiveData<Resource<Bean<String>>> addCollect(String goodsId, String goodsName) {
        return apiService.addCollect(SharedPreerencesUtils.getSessionID(getApplication()),
                goodsId,
                goodsName,
                "422429993732");
    }

//    public LiveData<Resource<PageBean<GoodsDetailsImageBean>>> getObjectAttachmentList(String id) {
//        return apiService.getObjectAttachmentList(1, 10, id);
//    }

    public void shopCar() {

    }

    public void addToShopCar() {

    }

    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @Override
    public Call<PageBean<GoodsDetailsImageBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getObjectAttachmentList(1, params.requestedLoadSize, (String) listRequest);
    }

    @Override
    public void setLoadInitialCallback(PageBean<GoodsDetailsImageBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, GoodsDetailsImageBean> callback) {
        callback.onResult(body.getBody().getData().getRows(), 1, 2);
    }

    @Override
    public Call<PageBean<GoodsDetailsImageBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return null;
    }

    @Override
    public boolean setLoadCallback(PageBean<GoodsDetailsImageBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, GoodsDetailsImageBean> callback, Listing<GoodsDetailsImageBean> listing) {
        return false;
    }
}
