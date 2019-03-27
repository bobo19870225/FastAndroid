package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.Message;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */
@Singleton
public class GoodsPageKeyedDataSource extends BasePageKeyedDataSource<Integer, Goods, Message<Goods>> {


    @Inject
    Listing<Goods> listing;
    @Inject
    ApiService apiService;

    @Inject
    public GoodsPageKeyedDataSource(Listing<Goods> listing, ApiService apiService) {
        super(listing, apiService);
//        this.listing = listing;
//        this.apiService = apiService;
    }


    private Integer integer = 10;


    @Override
    protected Call<Message<Goods>> setLoadInitialCall(ApiService apiService, LoadInitialParams<Integer> params) {
        return apiService.getGoodsList(0, 10, "");
    }

    @Override
    protected void setLoadInitialCallback(Message<Goods> body, LoadInitialCallback<Integer, Goods> callback) {
        callback.onResult(body.getContentList(), 0, 10);
    }

    @Override
    protected Call<Message<Goods>> setLoadAfterCall(ApiService apiService, LoadParams<Integer> params) {
        return null;
    }

    @Override
    protected void setLoadCallback(Message<Goods> body, LoadParams<Integer> params, LoadCallback<Integer, Goods> callback) {
        integer += 10;
        if (integer == 30) {
            callback.onResult(body.getContentList(), null);
        } else {
            callback.onResult(body.getContentList(), integer);
        }
    }


}
