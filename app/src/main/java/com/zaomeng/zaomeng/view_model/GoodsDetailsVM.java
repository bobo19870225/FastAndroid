package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;
import com.zaomeng.zaomeng.utils.SingleLiveEvent;

/**
 * Created by Sampson on 2019/4/17.
 * FastAndroid
 */
public class GoodsDetailsVM extends BaseViewModel {
    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();
    public final MediatorLiveData<Resource<Bean<GoodsDetailsBean>>> ldGoodsDetails = new MediatorLiveData<>();
    public final MutableLiveData<String> ldDescribe = new MutableLiveData<>();
    public final MutableLiveData<String> ldShowPrice = new MutableLiveData<>();
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

    public LiveData<Resource<Bean<CollectBean>>> addCollect(String goodsId, String goodsName) {
        return apiService.addCollect(SharedPreerencesUtils.getSessionID(getApplication()),
                goodsId,
                goodsName,
                "422429993732");
    }

    public void shopCar() {

    }
    public void addToShopCar() {

    }
}
