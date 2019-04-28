package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-04-28.
 * FastAndroid
 */
public class BranchGoodsBean {

    /**
     * objectID : 402892e76a0bd37c016a0be8a94e001e
     * objectName : 红糖馒头
     * listImage : http://wj.haoju.me/d59f50ed84444a6c912242034c8b0895.png
     * showPrice : 5.0
     * stockNumber : 0.0
     * stockOut : 0.0
     */

    private String objectID;
    private String objectName;
    private String listImage;
    private double showPrice;
    private double stockNumber;
    private double stockOut;

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
