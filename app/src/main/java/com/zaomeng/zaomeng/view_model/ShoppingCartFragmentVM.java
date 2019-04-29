package com.zaomeng.zaomeng.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.zaomeng.zaomeng.model.repository.Listing;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.by_page.shop_cart.ShopCartPageKeyRepository;
import com.zaomeng.zaomeng.utils.SharedPreerencesUtils;

/**
 * Created by Sampson on 2019/4/21.
 * FastAndroid
 * {@link com.zaomeng.zaomeng.view.ShoppingCartFragment}
 */
public class ShoppingCartFragmentVM extends ListViewModel<ShopCartBean> {
    private ShopCartPageKeyRepository shopCartPageKeyRepository;

    public ShoppingCartFragmentVM(@NonNull Application application, ShopCartPageKeyRepository shopCartPageKeyRepository) {
        super(application);
        this.shopCartPageKeyRepository = shopCartPageKeyRepository;
    }

    public final MutableLiveData<String> ldGoodsNumber = new MediatorLiveData<>();
    public final MutableLiveData<String> ldFreight = new MediatorLiveData<>();
    public final MutableLiveData<String> ldTotal = new MediatorLiveData<>();

//    public final MutableLiveData<String> ldGoodsNumber = new MediatorLiveData<>();


    @Override
    public void init(Object data) {

    }

    @Override
    public Listing<ShopCartBean> getListing(Object data) {
        return shopCartPageKeyRepository.post(new String[]{SharedPreerencesUtils.getSessionID(getApplication())}, 10);
    }

    /**
     * 删除商品
     */
    public void delete() {

    }

    /**
     * 全选商品
     */
    public void selectAll() {

    }

    /**
     * 结算
     */
    public void settlement() {

    }


}
