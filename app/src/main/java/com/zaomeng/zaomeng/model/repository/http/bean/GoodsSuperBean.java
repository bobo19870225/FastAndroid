package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-04-26.
 * FastAndroid
 */
public class GoodsSuperBean {

    /**
     * id : fa7c2854-8b56-492b-b542-58a4ab2a7358
     * name : 馒头
     * largeIcon :
     * orderSeq : 1
     * parentID : c82678b8ea0149c18fe6ac5ac8590d73
     * smallIcon :
     */

    private String id;
    private String name;
    private String largeIcon;
    private int orderSeq;
    private String parentID;
    private String smallIcon;

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

    public String getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(String smallIcon) {
        this.smallIcon = smallIcon;
    }
}
