package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.FocusPictureListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

import static com.zaomeng.zaomeng.utils.SystemParameter.focusID;

/**
 * Created by Sampson on 2019/4/4.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.MainGoodsFragment}
 */
public class MainGoodsFragmentVM extends BaseViewModel {
    private ApiService apiService;
    public Function0 function0;
//    public final SingleLiveEvent<String> action = new SingleLiveEvent<>();

    MainGoodsFragmentVM(@NonNull Application application, ApiService apiService) {
        super(application);
//        this.goodsPageKeyRepository = goodsPageKeyRepository;
        this.apiService = apiService;
        function0 = () -> {
            getNodeNavigatorList();
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

    /**
     * 商品规格
     */
    public LiveData<Resource<SpecificationsBean>> getObjectFeatureItemList(String objectID) {
        return apiService.getObjectFeatureItemList(objectID);
    }

    public LiveData<Resource<Bean<String>>> addGoodsShopToCart(@NonNull String goodsShopID, @NonNull Integer qty, String objectFeatureItemID1) {
        String sessionID = SharedPreferencesUtils.getSessionID(getApplication());
        if (sessionID != null) {
            return apiService.addGoodsShopToCart(sessionID,
                    goodsShopID, qty, objectFeatureItemID1);

        }
        return null;
    }


}
