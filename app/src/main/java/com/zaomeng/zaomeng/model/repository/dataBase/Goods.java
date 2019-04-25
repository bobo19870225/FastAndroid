package com.zaomeng.zaomeng.model.repository.dataBase;

import java.math.BigDecimal;

import javax.inject.Inject;

/**
 * Created by Sampson on 2018/6/11.
 * StockApp
 */
public class Goods {

    /**
     * id : 1
     * number : JKS10-4-001
     * goodsName : 双桥10平方数字探头
     * unitPrice : 900
     * costPrice : 200
     * productionDate : 2018-05-30
     * manufacturer : 金勘
     * remarks : 测试
     */

    private int id;
    private String number;
    private String goodsName;
    private BigDecimal unitPrice;
    private BigDecimal costPrice;
    private String productionDate;
    private String manufacturer;
    private String remarks;

    @Inject
    public Goods() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
