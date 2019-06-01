package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-06-01.
 * FastAndroid
 */
public class GoodsReturnDetailBean {

    /**
     * id : 2c9051726b077474016b094511830008
     * memo : 您的退款已申请成功，待客服审核中。
     * description : 不好吃
     * returnCode : D20190530110400273
     * goodsShopName : 安井 杂粮包 800g（早餐 粗粮养胃 馒头花卷 早餐包子 早茶点心）
     * littleImage : http://wj.haoju.me/d67e1dfa40c84fb9914756fe7f9dff10.jpg
     * qty : 1
     * objectFeatureItemName1 : 800g*10袋
     * priceTotalReturn : 0.006
     * applyTime : 1559228649000
     * applyTimeStr : 2019-05-30 23:04:09
     * status : 1
     */

    private String id;
    private String memo;
    private String description;
    private String returnCode;
    private String goodsShopName;
    private String littleImage;
    private int qty;
    private String objectFeatureItemName1;
    private double priceTotalReturn;
    private long applyTime;
    private String applyTimeStr;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getGoodsShopName() {
        return goodsShopName;
    }

    public void setGoodsShopName(String goodsShopName) {
        this.goodsShopName = goodsShopName;
    }

    public String getLittleImage() {
        return littleImage;
    }

    public void setLittleImage(String littleImage) {
        this.littleImage = littleImage;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getObjectFeatureItemName1() {
        return objectFeatureItemName1;
    }

    public void setObjectFeatureItemName1(String objectFeatureItemName1) {
        this.objectFeatureItemName1 = objectFeatureItemName1;
    }

    public double getPriceTotalReturn() {
        return priceTotalReturn;
    }

    public void setPriceTotalReturn(double priceTotalReturn) {
        this.priceTotalReturn = priceTotalReturn;
    }

    public long getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(long applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyTimeStr() {
        return applyTimeStr;
    }

    public void setApplyTimeStr(String applyTimeStr) {
        this.applyTimeStr = applyTimeStr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
