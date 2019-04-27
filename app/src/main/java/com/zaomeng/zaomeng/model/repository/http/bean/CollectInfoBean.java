package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-04-27.
 * FastAndroid
 */
public class CollectInfoBean {

    /**
     * collectID : 2c9051726a4ee143016a5da3685a0006
     * objectID : 2c9051726a3f6378016a48346ea90043
     * objectName : 正大食品（CP）猪肉圆白菜包 510g（6只装 包子馒头花卷 儿童早餐）
     * listImage : http://wj.haoju.me/8776b086e9014c5ca0caaaf19667f543.jpeg
     * showPrice : 23.99
     * stockNumber : 0.0
     * stockOut : 0.0
     */

    private String collectID;
    private String objectID;
    private String objectName;
    private String listImage;
    private double showPrice;
    private double stockNumber;
    private double stockOut;

    public String getCollectID() {
        return collectID;
    }

    public void setCollectID(String collectID) {
        this.collectID = collectID;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getListImage() {
        return listImage;
    }

    public void setListImage(String listImage) {
        this.listImage = listImage;
    }

    public double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(double showPrice) {
        this.showPrice = showPrice;
    }

    public double getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(double stockNumber) {
        this.stockNumber = stockNumber;
    }

    public double getStockOut() {
        return stockOut;
    }

    public void setStockOut(double stockOut) {
        this.stockOut = stockOut;
    }
}
