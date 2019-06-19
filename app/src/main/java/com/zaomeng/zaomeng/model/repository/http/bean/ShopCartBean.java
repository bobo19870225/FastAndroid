package com.zaomeng.zaomeng.model.repository.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sampson on 2019-04-28.
 * FastAndroid
 */
public class ShopCartBean implements Parcelable {


    /**
     * id : 2c9051726b6b2b01016b6d51542e0012
     * name : 可佰味黑米窝窝头
     * cartID : 2c9051726a646c47016a6484de6a0007
     * goodsShopID : 2c9051726b2c3bfb016b658f35390022
     * stockNumber : 100
     * stockOut : 100
     * standPrice : 120.0
     * discountRate : 0.0
     * priceNow : 120.0
     * qty : 1
     * priceTotal : 120.0
     * objectFeatureItemID1 : null
     * objectFeatureItemName1 : null
     * isSelected : 1
     * littleImage : http://qn.wgclm.com/499b57a9edcd4f00bc82ffe6a7340bff.jpg
     */

    private String id;
    private String name;
    private String cartID;
    private String goodsShopID;
    private int stockNumber;
    private int stockOut;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.cartID);
        dest.writeString(this.goodsShopID);
        dest.writeInt(this.stockNumber);
        dest.writeInt(this.stockOut);
        dest.writeDouble(this.standPrice);
        dest.writeDouble(this.discountRate);
        dest.writeDouble(this.priceNow);
        dest.writeInt(this.qty);
        dest.writeDouble(this.priceTotal);
        dest.writeString(this.objectFeatureItemID1);
        dest.writeString(this.objectFeatureItemName1);
        dest.writeInt(this.isSelected);
        dest.writeString(this.littleImage);
    }

    public ShopCartBean() {
    }

    protected ShopCartBean(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.cartID = in.readString();
        this.goodsShopID = in.readString();
        this.stockNumber = in.readInt();
        this.stockOut = in.readInt();
        this.standPrice = in.readDouble();
        this.discountRate = in.readDouble();
        this.priceNow = in.readDouble();
        this.qty = in.readInt();
        this.priceTotal = in.readDouble();
        this.objectFeatureItemID1 = in.readString();
        this.objectFeatureItemName1 = in.readString();
        this.isSelected = in.readInt();
        this.littleImage = in.readString();
    }

    public static final Parcelable.Creator<ShopCartBean> CREATOR = new Parcelable.Creator<ShopCartBean>() {
        @Override
        public ShopCartBean createFromParcel(Parcel source) {
            return new ShopCartBean(source);
        }

        @Override
        public ShopCartBean[] newArray(int size) {
            return new ShopCartBean[size];
        }
    };
}
