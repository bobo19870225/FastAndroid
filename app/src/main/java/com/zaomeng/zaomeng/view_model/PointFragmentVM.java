package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.NetWorkState;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PointBean;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;

import retrofit2.Call;

/**
 * Created by Sampson on 2019-05-07.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.PointFragment}
 */
public class PointFragmentVM extends ListViewModel<Integer, PointBean> {
    private ApiService apiService;
    private String sessionID;
    public final MutableLiveData<String> ldTotalPoint = new MutableLiveData<>();
    public PointFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
        this.apiService = apiService;
    }

    @NonNull
    @Override
    protected Integer setPageSize() {
        return 10;
    }

    @Override
    public Call<PageBean<PointBean>> setLoadInitialCall(PageKeyedDataSource.LoadInitialParams<Integer> params) {
        return apiService.getMyPointList(sessionID, 1, params.requestedLoadSize, (Integer) listRequest);
    }

    @Override
    public void setLoadInitialCallback(PageBean<PointBean> body, PageKeyedDataSource.LoadInitialCallback<Integer, PointBean> callback) {
        ldTotalPoint.postValue(String.valueOf(body.getBody().getPoint()));
        callback.onResult(body.getBody().getData().getRows(), 1, 2);

    }

    @Override
    public Call<PageBean<PointBean>> setLoadAfterCall(PageKeyedDataSource.LoadParams<Integer> params) {
        return apiService.getMyPointList(sessionID, params.key, params.requestedLoadSize, (Integer) listRequest);


    }

    @Override
    public boolean setLoadCallback(PageBean<PointBean> body, PageKeyedDataSource.LoadParams<Integer> params, PageKeyedDataSource.LoadCallback<Integer, PointBean> callback, Listing<PointBean> listing) {
        if (body.getHeader().getCode() == 0) {
//            int currentPage = body.getBody().getData().getCurrentPage();
            int total = body.getBody().getData().getTotalPage();
            if (total > params.key) {
                callback.onResult(body.getBody().getData().getRows(), params.key + 1);
            }
        } else {
            listing.networkState.postValue(NetWorkState.error(body.getHeader().getMsg()));
        }
        return body.getHeader().getCode() == 0;
    }

    @Override
    public void init(Object data) {
        sessionID = SharedPreferencesUtils.getSessionID(getApplication());
    }
}
