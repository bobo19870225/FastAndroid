package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-05-22.
 * FastAndroid
 */
public class MessageBean {
    /**
     * id : 66666666
     * name : 订单通知
     * description : 订单通知
     * sendDate : 1558493145000
     * sendDateStr : 2019-05-22 10:45:45
     * readTime : null
     * readTimeStr : null
     */

    private String id;
    private String name;
    private String description;
    private long sendDate;
    private String sendDateStr;
    private Object readTime;
    private Object readTimeStr;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSendDate() {
        return sendDate;
    }

    public void setSendDate(long sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendDateStr() {
        return sendDateStr;
    }

    public void setSendDateStr(String sendDateStr) {
        this.sendDateStr = sendDateStr;
    }

    public Object getReadTime() {
        return readTime;
    }

    public void setReadTime(Object readTime) {
        this.readTime = readTime;
    }

    public Object getReadTimeStr() {
        return readTimeStr;
    }

    public void setReadTimeStr(Object readTimeStr) {
        this.readTimeStr = readTimeStr;
    }
}
