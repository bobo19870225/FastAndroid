package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class GoodsDetailsBean {

    /**
     * id : 2c9051726a3f6378016a483b2ce8005c
     * goodsCategoryID : fa7c2854-8b56-492b-b542-58a4ab2a7358
     * name : 安井 杂粮包 800g（早餐 粗粮养胃 馒头花卷 早餐包子 早茶点心）
     * shortName : null
     * showName : 安井 杂粮包 800g（早餐 粗粮养胃 馒头花卷 早餐包子 早茶点心）
     * description : 品牌： 安井
     商品名称：安井冷冻面点 杂粮包
     商品编号：3891852
     商品毛重：0.83kg
     商品产地：江苏无锡；福建厦门
     国产/进口：国产
     分类：馒头
     风味：中式
     * largerImage : http://wj.haoju.me/d67e1dfa40c84fb9914756fe7f9dff10.jpg
     * littleImage : http://wj.haoju.me/d67e1dfa40c84fb9914756fe7f9dff10.jpg
     * standPrice : 0.0
     * realPrice : 22.0
     * stockNumber : 0.0
     * stockOut : 0.0
     * salesUnit : null
     * brandName : 安井
     * showPrice : 0.0
     * isCollect : 0
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
}


