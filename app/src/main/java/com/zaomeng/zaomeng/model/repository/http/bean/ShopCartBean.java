package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-04-28.
 * FastAndroid
 */
public class ShopCartBean {


    /**
     * id : 2c9051726a646c47016a67f125fe0016
     * cartID : 2c9051726a646c47016a6484de6a0007
     * goodsShopID : 2c9051726a3f6378016a482352ff0023
     * standPrice : 7.8
     * discountRate : 1.0
     * priceNow : 7.8
     * qty : 1
     * priceTotal : 7.8
     * objectFeatureItemID1 : 2c9051726a3f6378016a48243a2f002b
     * objectFeatureItemName1 : 250g*12包
     * isSelected : 1
     * littleImage : http://wj.haoju.me/14ef7461cc2c42ebb3f10651b63a7826.jpg
     */

    private String id;
    private String cartID;
    private String goodsShopID;
    private double standPrice;
    private double discountRate;
    private double priceNow;
    private int qty;
    private double priceTotal;
    private String objectFeatureItemID1;
    private String objectFeatureItemName1;
    private int isSelected;
    private String littleImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getGoodsShopID() {
        return goodsShopID;
    }

    public void setGoodsShopID(String goodsShopID) {
        this.goodsShopID = goodsShopID;
    }

    public double getStandPrice() {
        return standPrice;
    }

    public void setStandPrice(double standPrice) {
        this.standPrice = standPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(double priceNow) {
        this.priceNow = priceNow;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getObjectFeatureItemID1() {
        return objectFeatureItemID1;
    }

    public void setObjectFeatureItemID1(String objectFeatureItemID1) {
        this.objectFeatureItemID1 = objectFeatureItemID1;
    }

    public String getObjectFeatureItemName1() {
        return objectFeatureItemName1;
    }

    public void setObjectFeatureItemName1(String objectFeatureItemName1) {
        this.objectFeatureItemName1 = objectFeatureItemName1;
    }

    public int getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(int isSelected) {
        this.isSelected = isSelected;
    }

    public String getLittleImage() {
        return littleImage;
    }

    public void setLittleImage(String littleImage) {
        this.littleImage = littleImage;
    }
}
