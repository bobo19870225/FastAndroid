package com.jinkan.www.fastandroid.model.repository.http;


import com.jinkan.www.fastandroid.model.repository.dataBase.Goods;
import com.jinkan.www.fastandroid.model.repository.http.bean.Bean;
import com.jinkan.www.fastandroid.model.repository.http.bean.FocusPictureListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsDetailsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.GoodsListRowsBean;
import com.jinkan.www.fastandroid.model.repository.http.bean.PageBean;
import com.jinkan.www.fastandroid.model.repository.http.live_data_call_adapter.Resource;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sampson on 2018/4/17.
 * 网络请求接口
 */
public interface ApiService {
    @GET("appLogin.json")
    LiveData<Resource<ApiResponse>> appLogin(@Query("loginName") String account,
                                             @Query("password") String password,
                                             @Query("siteID") String siteID,
                                             @Query("loginType") String loginType);

    /**
     * 会员注册
     */
    @GET("phoneRegister.json")
    LiveData<Resource<ApiResponse>> phoneRegister(@Query("phone") String phone,
                                                  @Query("password") String password,
                                                  @Query("vCode") String vCode,
                                                  @Query("siteID") String siteID,
                                                  @Query("recommandID") String recommandID);

    /**
     * 获取商品列表
     */
    @GET("getGoodsShopList.json")
    Call<PageBean<GoodsListRowsBean>> getGoodsShopList(@Query("currentPage") Integer currentPage,
                                                       @Query("pageNumber") Integer pageNumber,
                                                       @Query("goodsCategoryID") String goodsCategoryID,
                                                       @Query("memberID") String memberID);

    /**
     * 获取商品详情
     */
    @GET("getGoodsShopDetail.json")
    LiveData<Bean<GoodsDetailsBean>> getGoodsShopDetail(@Query("goodsShopID") String goodsShopID,
                                                        @Query("memberID") String memberID);


    /**
     * 获取轮播列表
     */
    @GET("getFocusPictureList.json")
    LiveData<Resource<PageBean<FocusPictureListRowsBean>>> getFocusPictureList(@Query("currentPage") Integer currentPage,
                                                                               @Query("pageNumber") Integer pageNumber,
                                                                               @Query("focusID") String goodsCategoryID);

    /**
     * 发送验证码
     */
    @GET("sendSmsCommon.json")
    LiveData<Resource<ApiResponse>> sendSmsCommon(@Query("currentPage") Integer currentPage,
                                                  @Query("pageNumber") Integer pageNumber,
                                                  @Query("focusID") String goodsCategoryID,
                                                  @Query("memberID") String memberID);

    /**
     * 获取对象附件列表
     */
    @GET("getObjectAttachmentList.json")
    Call<Message<Goods>> getObjectAttachmentList(@Query("currentPage") Integer currentPage,
                                                 @Query("pageNumber") Integer pageNumber,
                                                 @Query("objectID") String objectID);

    /**
     * 商品加入购物车
     */
    @GET("addGoodsShopToCart.json")
    Call<Message<Goods>> addGoodsShopToCart(@Query("sessionID") String sessionID,
                                            @Query("goodsShopID") String goodsShopID,
                                            @Query("qty") Integer qty,
                                            @Query("objectFeatureItemID1") String objectFeatureItemID1);

    /**
     * 更改购物车商品数量
     */
    @GET("updateCartGoodsNumber.json")
    Call<Message<Goods>> updateCartGoodsNumber(@Query("sessionID") String sessionID,
                                               @Query("cartGoodsID") String cartGoodsID,
                                               @Query("qty") Integer qty);

    /**
     * 勾选购物车商品
     */
    @GET("selectCartGoods.json")
    Call<Message<Goods>> selectCartGoods(@Query("sessionID") String sessionID,
                                         @Query("cartGoodsID") String cartGoodsID,
                                         @Query("isSelect") Integer isSelect);

    /**
     * 获取购物车商品
     */
    @GET("getCartGoodsList.json")
    Call<Message<Goods>> getCartGoodsList(@Query("sessionID") String sessionID,
                                          @Query("currentPage") Integer currentPage,
                                          @Query("pageNumber") Integer pageNumber);

    /**
     * 删除购物车商品
     */
    @GET("removeCartGoods.json")
    Call<Message<Goods>> removeCartGoods(@Query("sessionID") String sessionID,
                                         @Query("cartGoodsID") String cartGoodsID);

    /**
     * 获取分类列表
     */
    @GET("getCategoryList.json")
    Call<Message<Goods>> getCategoryList(@Query("rootID") String rootID,
                                         @Query("depth") Integer depth);

    /**
     * 购物车生成订单
     */
    @GET("createMemberOrderFromCart.json")
    Call<Message<Goods>> createMemberOrderFromCart(@Query("sessionID") String sessionID,
                                                   @Query("contactName") String contactName,
                                                   @Query("contactPhone") String contactPhone,
                                                   @Query("address") String address);

    /**
     * 店铺商品生成订单
     */
    @GET("createMemberOrder.json")
    Call<Message<Goods>> createMemberOrder(@Query("sessionID") String sessionID,
                                           @Query("goodsShopID") String goodsShopID,
                                           @Query("qty") Integer qty,
                                           @Query("objectFeatureItemID1") String objectFeatureItemID1,
                                           @Query("contactName") String contactName,
                                           @Query("contactPhone") String contactPhone,
                                           @Query("address") String address);



}


