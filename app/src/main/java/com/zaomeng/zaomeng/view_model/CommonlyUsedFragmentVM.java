package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.common_used_Goods.CUGoodsPageKeyRepository;
import com.zaomeng.zaomeng.model.repository.http.by_page.common_used_Goods.CUGoodsPageKeyedDataSource;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;
import com.zaomeng.zaomeng.view.CommonlyUsedFragment;

/**
 * Created by Sampson on 2019/4/18.
 * FastAndroid
 * {@link CommonlyUsedFragment}
 */
public class CommonlyUsedFragmentVM extends ListViewModel<CollectInfoBean> {
    private ApiService apiService;
    private CUGoodsPageKeyRepository cuGoodsPageKeyRepository;

    public CommonlyUsedFragmentVM(@NonNull Application application, ApiService apiService, CUGoodsPageKeyRepository cuGoodsPageKeyRepository) {
        super(application);
        this.apiService = apiService;
        this.cuGoodsPageKeyRepository = cuGoodsPageKeyRepository;
    }

    @Override
    public void init(Object data) {

    }

    /**
     * {@link CUGoodsPageKeyedDataSource}
     */
    @Override
    public Listing<CollectInfoBean> getListing(Object data) {
        return cuGoodsPageKeyRepository.post(new String[]{SharedPreerencesUtils.getSessionID(getApplication()),
                "422429993732"}, 10);

    }
}
