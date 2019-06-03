package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class GoodsDetailsBean {

    /**
     * id : 2c9051726a3f6378016a482352ff0023
     * goodsCategoryID : fa7c2854-8b56-492b-b542-58a4ab2a7358
     * name : 知味观 小笼包 笋丁猪肉味 250g（包子 早餐 馒头花卷 杭州特产）
     * shortName : null
     * showName : 知味观 小笼包 笋丁猪肉味 250g（包子 早餐 馒头花卷 杭州特产）
     * description : 商品名称：知味观小笼包
     商品编号：7088658
     商品毛重：290.00g
     商品产地：浙江省杭州市
     货号：6957310403004
     风味：中式套餐
     份量：1人份
     净含量：300g以下
     分类：包子
     国产/进口：国产售卖
     方式：单品
     * largerImage : http://wj.haoju.me/14ef7461cc2c42ebb3f10651b63a7826.jpg
     * littleImage : http://wj.haoju.me/14ef7461cc2c42ebb3f10651b63a7826.jpg
     * standPrice : 0.0
     * realPrice : 7.8
     * stockNumber : 0.0
     * stockOut : 0.0
     * salesUnit : null
     * brandName : 知味观
     * showPrice : 0.0
     * isCollect : 1
     * collectID : 2c9051726b17b4db016b1885da3b0004
     */

    private String id;
    private String goodsCategoryID;
    private String name;
    private Object shortName;
    private String showName;
    private String description;
    private String largerImage;
    private String littleImage;
    private double standPrice;
    private double realPrice;
    private double stockNumber;
    private double stockOut;
    private Object salesUnit;
    private String brandName;
    private double showPrice;
    private int isCollect;
    private String collectID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsCategoryID() {
        return goodsCategoryID;
    }

    public void setGoodsCategoryID(String goodsCategoryID) {
        this.goodsCategoryID = goodsCategoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getShortName() {
        return shortName;
    }

    public void setShortName(Object shortName) {
        this.shortName = shortName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLargerImage() {
        return largerImage;
    }

    public void setLargerImage(String largerImage) {
        this.largerImage = largerImage;
    }

    public String getLittleImage() {
        return littleImage;
    }

    public void setLittleImage(String littleImage) {
        this.littleImage = littleImage;
    }

    public double getStandPrice() {
        return standPrice;
    }

    public void setStandPrice(double standPrice) {
        this.standPrice = standPrice;
    }

    public double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(double realPrice) {
        this.realPrice = realPrice;
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

    public Object getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(Object salesUnit) {
        this.salesUnit = salesUnit;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(double showPrice) {
        this.showPrice = showPrice;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }

    public String getCollectID() {
        return collectID;
    }

    public void setCollectID(String collectID) {
        this.collectID = collectID;
    }
}


