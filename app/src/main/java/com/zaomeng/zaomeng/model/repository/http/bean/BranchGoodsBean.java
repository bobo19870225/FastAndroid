package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-04-28.
 * FastAndroid
 */
public class BranchGoodsBean {


    /**
     * objectID : 2c9051726a3f6378016a48346ea90043
     * objectName : 正大食品（CP）猪肉圆白菜包 510g（6只装 包子馒头花卷 儿童早餐）
     * listImage : http://wj.haoju.me/8776b086e9014c5ca0caaaf19667f543.jpeg
     * showPrice : 8.88
     * stockNumber : 0.0
     * stockOut : 0.0
     * unitDescription : 510g*10袋
     * objectFeatureItemID1 : 2c9051726a3f6378016a483519cb0047
     */

    private String objectID;
    private String objectName;
    private String listImage;
    private double showPrice;
    private int stockNumber;
    private int stockOut;
    private String unitDescription;
    private String objectFeatureItemID1;

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

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public int getStockOut() {
        return stockOut;
    }

    public void setStockOut(int stockOut) {
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
