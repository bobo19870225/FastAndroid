package com.jinkan.www.fastandroid.model.repository.http.bean;

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
     */

    private String id;
    private String name;
    private String largeIcon;
    private int orderSeq;
    private String parentID;
    private String samllIcon;

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
}
