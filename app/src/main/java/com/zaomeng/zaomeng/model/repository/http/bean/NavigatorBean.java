package com.zaomeng.zaomeng.model.repository.http.bean;

import java.util.List;

/**
 * Created by Sampson on 2019/4/21.
 * FastAndroid
 */
public class NavigatorBean {


    /**
     * id : b70f212f31d74dc391bbe06f5dd8ec7f
     * name : 推荐
     * largeIcon :
     * orderSeq : 1
     * parentID : 076333d6bd284605ab2293fb698db804
     * samllIcon :
     * goodsList : [{"objectID":"402892e76a0bd37c016a0be8a94e001e","objectName":"红糖馒头","listImage":"http://wj.haoju.me/d59f50ed84444a6c912242034c8b0895.png","showPrice":5,"stockNumber":0,"stockOut":0},{"objectID":"dsfsdfdsf","objectName":"奶黄包","listImage":"http://wj.haoju.me/3883e6c9761a49788368bcd7a369ab0c.png","showPrice":6,"stockNumber":1,"stockOut":1}]
     */

    private String id;
    private String name;
    private String largeIcon;
    private int orderSeq;
    private String parentID;
    private String samllIcon;
    private List<GoodsListBean> goodsList;

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

    public String getLargeIcon() {
        return largeIcon;
    }

    public void setLargeIcon(String largeIcon) {
        this.largeIcon = largeIcon;
    }

    public int getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(int orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getSamllIcon() {
        return samllIcon;
    }

    public void setSamllIcon(String samllIcon) {
        this.samllIcon = samllIcon;
    }

    public List<GoodsListBean> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsListBean> goodsList) {
        this.goodsList = goodsList;
    }

    public static class GoodsListBean {
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
}
