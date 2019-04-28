package com.zaomeng.zaomeng.model.repository.http;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.zaomeng.zaomeng.model.repository.dataBase.Goods;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BranchGoodsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.CollectInfoBean;
import com.zaomeng.zaomeng.model.repository.http.bean.FocusPictureListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsDetailsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsListRowsBean;
import com.zaomeng.zaomeng.model.repository.http.bean.GoodsSuperBean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.model.repository.http.bean.NavigatorBean;
import com.zaomeng.zaomeng.model.repository.http.bean.PageBean;
import com.zaomeng.zaomeng.model.repository.http.bean.RegisterBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SendSmsCommonBean;
import com.zaomeng.zaomeng.model.repository.http.bean.ShopCartBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SpecificationsBean;
import com.zaomeng.zaomeng.model.repository.http.live_data_call_adapter.Resource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sampson on 2018/4/17.
 * 网络请求接口
 */
public interface ApiService {
    @GET("appLogin.json")
    LiveData<Resource<Bean<LoginBean>>> appLogin(@Query("loginName") String account,
                                                 @Query("password") String password,
                                                 @Query("siteID") String siteID,
                                                 @Query("loginType") String loginType);

    /**
     * 会员注册
     */
    @GET("phoneRegister.json")
    LiveData<Resource<Bean<RegisterBean>>> phoneRegister(@Query("phone") String phone,
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
     * 获取商品列表
     */
    @GET("getGoodsShopList.json")
    LiveData<Resource<PageBean<GoodsListRowsBean>>> getGoodsShopListForLiveData(@Query("currentPage") Integer currentPage,
                                                                                @Query("pageNumber") Integer pageNumber,
                                                                                @Query("goodsCategoryID") String goodsCategoryID,
                                                                                @Query("memberID") String memberID);

    /**
     * 获取商品详情
     */
    @GET("getGoodsShopDetail.json")
    LiveData<Resource<Bean<GoodsDetailsBean>>> getGoodsShopDetail(@Query("goodsShopID") String goodsShopID,
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
     * @param sendType 0 注册
     */
    @GET("sendSmsCommon.json")
    LiveData<Resource<SendSmsCommonBean>> sendSmsCommon(@Query("phone") String phone,
                                                        @Query("siteID") String siteID,
                                                        @Query("sendType") Integer sendType);

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
    LiveData<Resource<Bean<String>>> addGoodsShopToCart(@NonNull @Query("sessionID") String sessionID,
                                                        @NonNull @Query("goodsShopID") String goodsShopID,
                                                        @NonNull @Query("qty") Integer qty,
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
    Call<PageBean<ShopCartBean>> getCartGoodsList(@Query("sessionID") String sessionID,
                                                  @Query("currentPage") Integer currentPage,
                                                  @Query("pageNumber") Integer pageNumber);

    /**
     * 获取购物车商品
     */
    @GET("getCartGoodsList.json")
    LiveData<Resource<PageBean<ShopCartBean>>> getCartGoodsListLD(@Query("sessionID") String sessionID,
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

    /**
     * 获取子集导航列表（标准结构）
     * depth=2
     * http://47.110.250.70:8083/zaomeng-api/api/getNodeNavigatorList.json?
     * rootID=076333d6bd284605ab2293fb698db804&
     * depth=2&
     * objectDefineID=422429993732
     */
    @GET("getNodeNavigatorList.json")
    LiveData<Resource<PageBean<NavigatorBean>>> getNodeNavigatorList(@Query("rootID") String rootID, @Query("depth") Integer depth, @Query("objectDefineID") String objectDefineID);


    /**
     * 获取对象特征选项
     */
    @GET("getObjectFeatureItemList.json")
    LiveData<Resource<SpecificationsBean>> getObjectFeatureItemList(@Query("objectID") String objectID);

    /**
     * 获取子集分类列表（标准结构）
     */
    @GET("getNodeCategoryList.json")
    LiveData<Resource<PageBean<GoodsSuperBean>>> getNodeCategoryList(@Query("rootID") String rootID, @Query("depth") Integer depth);

    /**
     * 获取收藏列表
     */
    @GET("getCollectList.json")
    Call<PageBean<CollectInfoBean>> getCollectList(@Query("sessionID") String sessionID,
                                                   @Query("objectDefineID") String objectDefineID,
                                                   @Query("currentPage") Integer currentPage,
                                                   @Query("pageNumber") Integer pageNumber);

    /**
     * 获取子集分类列表（标准结构）
     */
    @GET("addCollect.json")
    LiveData<Resource<Bean<String>>> addCollect(@Query("sessionID") String sessionID,
                                                @Query("objectID") String objectID,
                                                @Query("objectName") String objectName,
                                                @Query("objectDefineID") String objectDefineID);

    /**
     * 获取子集分类列表（标准结构）
     */
    @GET("getNavigatorReleaseList.json")
    Call<PageBean<BranchGoodsBean>> getNavigatorReleaseList(@Query("currentPage") Integer currentPage,
                                                            @Query("pageNumber") Integer pageNumber,
                                                            @Query("memberID") String memberID,
                                                            @Query("objectDefineID") String objectDefineID,
                                                            @Query("navigatorID") String navigatorID);




}


