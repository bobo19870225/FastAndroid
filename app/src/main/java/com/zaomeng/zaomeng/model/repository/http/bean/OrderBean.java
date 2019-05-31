package com.zaomeng.zaomeng.model.repository.http.bean;

import java.util.List;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class OrderBean {


    /**
     * id : 2c9051726acaccb0016acbb16e350011
     * name : null
     * memberID : 2c9051726a646c47016a647e046e0006
     * memberName : 卢声波
     * avatarURL : http://qn.wgclm.com/FiPxfns7K7Fu_s3A5h4Q4xU-3XK_
     * orderCode : D20190519120600188
     * applyTime : 1558195564000
     * applyTimeStr : 2019-05-19 00:06:04
     * getGoodsTime : null
     * getGoodsTimeStr : null
     * goodsNumbers : 2
     * goodsQTY : 2
     * priceTotal : 6.0
     * priceStandTotal : 10.0
     * sendPrice : 0.0
     * discountRate : 0.6
     * priceAfterDiscount : 6.0
     * payTotal : 0.0
     * payFrom : 1
     * payTime : null
     * payTimeStr : null
     * paySequence : null
     * sendType : null
     * memberMemo : null
     * sendTime : null
     * sendTimeStr : null
     * sendCode : null
     * status : 2
     * contactName : 18101603953
     * contactPhone : 18101603953
     * address : 上海市静安区天目中路538弄1号6B
     * goodsList : [{"id":"2c9051726acaccb0016acbb16e360012","goodsName":"红糖馒头","listImage":"http://wj.haoju.me/d59f50ed84444a6c912242034c8b0895.png","memberOrderID":"2c9051726acaccb0016acbb16e350011","objectFeatureItemID1":"402892e76a0bd37c016a0be91e540024","objectFeatureItemName1":null,"priceStand":9.99,"discountRate":0.6,"priceNow":5.994,"priceReturn":5.994,"priceTotal":5.994,"isReturn":1,"status":1,"qty":1},{"id":"2c9051726acaccb0016acbb16e360013","goodsName":"安井 杂粮包 800g（早餐 粗粮养胃 馒头花卷 早餐包子 早茶点心）","listImage":"http://wj.haoju.me/d67e1dfa40c84fb9914756fe7f9dff10.jpg","memberOrderID":"2c9051726acaccb0016acbb16e350011","objectFeatureItemID1":"2c9051726a3f6378016a483bddd8005f","objectFeatureItemName1":null,"priceStand":0.01,"discountRate":0.6,"priceNow":0.006,"priceReturn":0.006,"priceTotal":0.006,"isReturn":1,"status":1,"qty":1}]
     */

    private String id;
    private Object name;
    private String memberID;
    private String memberName;
    private String avatarURL;
    private String orderCode;
    private long applyTime;
    private String applyTimeStr;
    private Object getGoodsTime;
    private Object getGoodsTimeStr;
    private int goodsNumbers;
    private int goodsQTY;
    private double priceTotal;
    private double priceStandTotal;
    private double sendPrice;
    private double discountRate;
    private double priceAfterDiscount;
    private double payTotal;
    private int payFrom;
    private Object payTime;
    private Object payTimeStr;
    private Object paySequence;
    private Object sendType;
    private Object memberMemo;
    private Object sendTime;
    private Object sendTimeStr;
    private Object sendCode;
    private int status;
    private String contactName;
    private String contactPhone;
    private String address;
    private List<GoodsListBean> goodsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public Object getGetGoodsTime() {
        return getGoodsTime;
    }

    public void setGetGoodsTime(Object getGoodsTime) {
        this.getGoodsTime = getGoodsTime;
    }

    public Object getGetGoodsTimeStr() {
        return getGoodsTimeStr;
    }

    public void setGetGoodsTimeStr(Object getGoodsTimeStr) {
        this.getGoodsTimeStr = getGoodsTimeStr;
    }

    public int getGoodsNumbers() {
        return goodsNumbers;
    }

    public void setGoodsNumbers(int goodsNumbers) {
        this.goodsNumbers = goodsNumbers;
    }

    public int getGoodsQTY() {
        return goodsQTY;
    }

    public void setGoodsQTY(int goodsQTY) {
        this.goodsQTY = goodsQTY;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public double getPriceStandTotal() {
        return priceStandTotal;
    }

    public void setPriceStandTotal(double priceStandTotal) {
        this.priceStandTotal = priceStandTotal;
    }

    public double getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(double sendPrice) {
        this.sendPrice = sendPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getPriceAfterDiscount() {
        return priceAfterDiscount;
    }

    public void setPriceAfterDiscount(double priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public double getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(double payTotal) {
        this.payTotal = payTotal;
    }

    public int getPayFrom() {
        return payFrom;
    }

    public void setPayFrom(int payFrom) {
        this.payFrom = payFrom;
    }

    public Object getPayTime() {
        return payTime;
    }

    public void setPayTime(Object payTime) {
        this.payTime = payTime;
    }

    public Object getPayTimeStr() {
        return payTimeStr;
    }

    public void setPayTimeStr(Object payTimeStr) {
        this.payTimeStr = payTimeStr;
    }

    public Object getPaySequence() {
        return paySequence;
    }

    public void setPaySequence(Object paySequence) {
        this.paySequence = paySequence;
    }

    public Object getSendType() {
        return sendType;
    }

    public void setSendType(Object sendType) {
        this.sendType = sendType;
    }

    public Object getMemberMemo() {
        return memberMemo;
    }

    public void setMemberMemo(Object memberMemo) {
        this.memberMemo = memberMemo;
    }

    public Object getSendTime() {
        return sendTime;
    }

    public void setSendTime(Object sendTime) {
        this.sendTime = sendTime;
    }

    public Object getSendTimeStr() {
        return sendTimeStr;
    }

    public void setSendTimeStr(Object sendTimeStr) {
        this.sendTimeStr = sendTimeStr;
    }

    public Object getSendCode() {
        return sendCode;
    }

    public void setSendCode(Object sendCode) {
        this.sendCode = sendCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsListBean {
        /**
         * id : 2c9051726acaccb0016acbb16e360012
         * goodsName : 红糖馒头
         * listImage : http://wj.haoju.me/d59f50ed84444a6c912242034c8b0895.png
         * memberOrderID : 2c9051726acaccb0016acbb16e350011
         * objectFeatureItemID1 : 402892e76a0bd37c016a0be91e540024
         * objectFeatureItemName1 : null
         * priceStand : 9.99
         * discountRate : 0.6
         * priceNow : 5.994
         * priceReturn : 5.994
         * priceTotal : 5.994
         * isReturn : 1
         * status : 1
         * qty : 1
         */

        private String id;
        private String goodsName;
        private String listImage;
        private String memberOrderID;
        private String objectFeatureItemID1;
        private String objectFeatureItemName1;
        private double priceStand;
        private double discountRate;
        private double priceNow;
        private double priceReturn;
        private double priceTotal;
        private int isReturn;
        private int status;
        private int qty;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGoodsName() {
            return goodsName;
        }

        public void setGoodsName(String goodsName) {
            this.goodsName = goodsName;
        }

        public String getListImage() {
            return listImage;
        }

        public void setListImage(String listImage) {
            this.listImage = listImage;
        }

        public String getMemberOrderID() {
            return memberOrderID;
        }

        public void setMemberOrderID(String memberOrderID) {
            this.memberOrderID = memberOrderID;
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

        public double getPriceStand() {
            return priceStand;
        }

        public void setPriceStand(double priceStand) {
            this.priceStand = priceStand;
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

        public double getPriceReturn() {
            return priceReturn;
        }

        public void setPriceReturn(double priceReturn) {
            this.priceReturn = priceReturn;
        }

        public double getPriceTotal() {
            return priceTotal;
        }

        public void setPriceTotal(double priceTotal) {
            this.priceTotal = priceTotal;
        }

        public int getIsReturn() {
            return isReturn;
        }

        public void setIsReturn(int isReturn) {
            this.isReturn = isReturn;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }
    }
}
