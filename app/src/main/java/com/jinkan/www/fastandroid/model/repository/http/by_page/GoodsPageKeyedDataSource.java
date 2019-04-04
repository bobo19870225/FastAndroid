package com.jinkan.www.fastandroid.model.repository.http.by_page;

import com.jinkan.www.fastandroid.model.repository.Listing;
import com.jinkan.www.fastandroid.model.repository.NetWorkState;
import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;
import com.jinkan.www.fastandroid.model.repository.http.ApiService;
import com.jinkan.www.fastandroid.model.repository.http.Message;
import com.jinkan.www.fastandroid.model.repository.http.PageModel;

import androidx.annotation.NonNull;
import retrofit2.Call;

/**
 * Created by Sampson on 2019/2/25.
 * FastAndroid
 */

public class GoodsPageKeyedDataSource extends BasePageKeyedDataSource<Integer, Goods> {


    private String token;


    public GoodsPageKeyedDataSource(Listing<Goods> listing, ApiService apiService, String token) {
        super(listing, apiService);
        this.token = token;
    }


    @NonNull
    @Override
    protected Call<Message<Goods>> setLoadInitialCall(ApiService apiService, LoadInitialParams<Integer> params) {
        return apiService.getGoodsList(0, 10, token);
    }

    @Override
    protected void setLoadInitialCallback(Message<Goods> body, LoadInitialCallback<Integer, Goods> callback) {
        callback.onResult(body.getContentList(), 0, 1);
    }

    @NonNull
    @Override
    protected Call<Message<Goods>> setLoadAfterCall(ApiService apiService, LoadParams<Integer> params) {
        return apiService.getGoodsList(params.key, params.requestedLoadSize, token);
    }

    @Override
    protected boolean setLoadCallback(Message<Goods> body, LoadParams<Integer> params, LoadCallback<Integer, Goods> callback) {
        if (body.getCode() == 0) {
            PageModel pageModel = body.getPageModel();
            if (pageModel.getTotalSize() > params.key) {
                callback.onResult(body.getContentList(), params.key + 1);
            }
        } else {
            listing.networkState.postValue(NetWorkState.error(body.getMsg()));
        }
        return body.getCode() == 0;
    }


}
