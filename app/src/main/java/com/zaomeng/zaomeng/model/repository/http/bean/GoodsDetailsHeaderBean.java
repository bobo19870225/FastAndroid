package com.zaomeng.zaomeng.model.repository.http.bean;

import java.util.List;

/**
 * Created by Sampson on 2019-04-30.
 * FastAndroid
 */
public class GoodsDetailsHeaderBean {
    private List<String> listBannerURL;
    private String goodsName;
    private double price;
    private String describe;
    private int stockOut;

    public List<String> getListBannerURL() {
        return listBannerURL;
    }

    public void setListBannerURL(List<String> listBannerURL) {
        this.listBannerURL = listBannerURL;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getStockOut() {
        return stockOut;
    }

    public void setStockOut(int stockOut) {
        this.stockOut = stockOut;
    }
}
