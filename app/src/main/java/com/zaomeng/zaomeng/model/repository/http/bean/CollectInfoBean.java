package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-04-27.
 * FastAndroid
 */
public class CollectInfoBean {

    /**
     * collectID : 2c9051726b01cf1d016b0264b8310061
     * objectID : 402892e76a0bd37c016a0be8a94e001e
     * objectName : 红糖馒头
     * listImage : http://wj.haoju.me/d59f50ed84444a6c912242034c8b0895.png
     * showPrice : 8.88
     * stockNumber : 0.0
     * stockOut : 0.0
     * unitDescription : 红色
     * objectFeatureItemID1 : 402892e76a0bd37c016a0be91deb0023
     */

    private String collectID;
    private String objectID;
    private String objectName;
    private String listImage;
    private double showPrice;
    private double stockNumber;
    private double stockOut;
    private String unitDescription;
    private String objectFeatureItemID1;

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

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    public String getObjectFeatureItemID1() {
        return objectFeatureItemID1;
    }

    public void setObjectFeatureItemID1(String objectFeatureItemID1) {
        this.objectFeatureItemID1 = objectFeatureItemID1;
    }
}
