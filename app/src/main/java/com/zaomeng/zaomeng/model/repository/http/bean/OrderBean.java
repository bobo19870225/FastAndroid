package com.zaomeng.zaomeng.model.repository.http.bean;

import java.util.List;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class OrderBean {

    /**
     * id : 2c9051726a956662016a9663f4960017
     * name : null
     * memberID : 2c9051726a646c47016a647e046e0006
     * memberName : 18101603953
     * avatarURL : null
     * orderCode : D20190508034100001
     * applyTime : 1557301294000
     * getGoodsTime : null
     * goodsNumbers : 1
     * goodsQTY : 1
     * priceTotal : 7.8
     * priceStandTotal : 7.8
     * sendPrice : 0.0
     * discountRate : 1.0
     * priceAfterDiscount : 0.0
     * payTotal : 0.0
     * payFrom : 1
     * payTime : null
     * paySequence : null
     * sendType : null
     * memberMemo : null
     * sendTime : null
     * sendCode : null
     * status : 1
     * contactName : 18101603953
     * contactPhone : 18101603953
     * address : 上海市，天目中路538弄1号6B
     * goodsList : [{"id":"2c9051726a956662016a9663f4970018","goodsName":"知味观 小笼包 笋丁猪肉味 250g（包子 早餐 馒头花卷 杭州特产）","listImage":"http://wj.haoju.me/14ef7461cc2c42ebb3f10651b63a7826.jpg","memberOrderID":"2c9051726a956662016a9663f4960017","objectFeatureItemID1":"2c9051726a3f6378016a48243a2f002b","objectFeatureItemName1":null,"priceStand":7.8,"discountRate":1,"priceNow":7.8,"priceReturn":7.8,"priceTotal":7.8,"isReturn":1,"status":1,"qty":1}]
     */

    private String id;
    private Object name;
    private String memberID;
    private String memberName;
    private Object avatarURL;
    private String orderCode;
    private long applyTime;
    private Object getGoodsTime;
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
    private Object paySequence;
    private Object sendType;
    private Object memberMemo;
    private Object sendTime;
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

    public Object getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(Object avatarURL) {
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

    public Object getGetGoodsTime() {
        return getGoodsTime;
    }

    public void setGetGoodsTime(Object getGoodsTime) {
        this.getGoodsTime = getGoodsTime;
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
         * id : 2c9051726a956662016a9663f4970018
         * goodsName : 知味观 小笼包 笋丁猪肉味 250g（包子 早餐 馒头花卷 杭州特产）
         * listImage : http://wj.haoju.me/14ef7461cc2c42ebb3f10651b63a7826.jpg
         * memberOrderID : 2c9051726a956662016a9663f4960017
         * objectFeatureItemID1 : 2c9051726a3f6378016a48243a2f002b
         * objectFeatureItemName1 : null
         * priceStand : 7.8
         * discountRate : 1.0
         * priceNow : 7.8
         * priceReturn : 7.8
         * priceTotal : 7.8
         * isReturn : 1
         * status : 1
         * qty : 1
         */

        private String id;
        private String goodsName;
        private String listImage;
        private String memberOrderID;
        private String objectFeatureItemID1;
        private Object objectFeatureItemName1;
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

        public Object getObjectFeatureItemName1() {
            return objectFeatureItemName1;
        }

        public void setObjectFeatureItemName1(Object objectFeatureItemName1) {
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
