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
     * largeIcon : http://wj.haoju.me/a06fe6a206f34c3b9a9a66f72398210c.jpg
     * orderSeq : 1
     * parentID : 076333d6bd284605ab2293fb698db804
     * samllIcon : http://wj.haoju.me/5877141d024641769451a35615e7ff8f.jpg
     * goodsList : [{"objectID":"402892e76a0bd37c016a0be8a94e001e","objectName":"红糖馒头","listImage":"http://wj.haoju.me/d59f50ed84444a6c912242034c8b0895.png","showPrice":8.88,"stockNumber":0,"stockOut":0,"unitDescription":"红色","objectFeatureItemID1":"402892e76a0bd37c016a0be91deb0023"},{"objectID":"dsfsdfdsf","objectName":"奶黄包","listImage":"http://wj.haoju.me/3883e6c9761a49788368bcd7a369ab0c.png","showPrice":25,"stockNumber":1,"stockOut":1,"unitDescription":"1斤","objectFeatureItemID1":"402881856a07a2f6016a07a7c09d0008"},{"objectID":"2c9051726a3f6378016a482352ff0023","objectName":"知味观 小笼包 笋丁猪肉味 250g（包子 早餐 馒头花卷 杭州特产）","listImage":"http://wj.haoju.me/14ef7461cc2c42ebb3f10651b63a7826.jpg","showPrice":8.88,"stockNumber":0,"stockOut":0,"unitDescription":"250g*6包","objectFeatureItemID1":"2c9051726a3f6378016a48243a28002a"},{"objectID":"2c9051726a3f6378016a48346ea90043","objectName":"正大食品（CP）猪肉圆白菜包 510g（6只装 包子馒头花卷 儿童早餐）","listImage":"http://wj.haoju.me/8776b086e9014c5ca0caaaf19667f543.jpeg","showPrice":8.88,"stockNumber":0,"stockOut":0,"unitDescription":"510g*10袋","objectFeatureItemID1":"2c9051726a3f6378016a483519cb0047"},{"objectID":"2c9051726a3f6378016a483b2ce8005c","objectName":"安井 杂粮包 800g（早餐 粗粮养胃 馒头花卷 早餐包子 早茶点心）","listImage":"http://wj.haoju.me/d67e1dfa40c84fb9914756fe7f9dff10.jpg","showPrice":0.01,"stockNumber":0,"stockOut":0,"unitDescription":"800g*10袋","objectFeatureItemID1":"2c9051726a3f6378016a483bddd8005f"}]
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
         * objectID : 2c9051726a3f6378016a482352ff0023
         * objectName : 知味观 小笼包 笋丁猪肉味 250g（包子 早餐 馒头花卷 杭州特产）
         * listImage : http://wj.haoju.me/14ef7461cc2c42ebb3f10651b63a7826.jpg
         * showPrice : 8.88
         * stockNumber : 0.0
         * stockOut : 0.0
         * unitDescription : 250g*6包
         * objectFeatureItemID1 : 2c9051726a3f6378016a48243a28002a
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
}
