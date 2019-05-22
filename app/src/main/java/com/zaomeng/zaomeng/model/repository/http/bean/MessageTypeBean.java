package com.zaomeng.zaomeng.model.repository.http.bean;

import java.util.List;

/**
 * Created by Sampson on 2019-05-22.
 * FastAndroid
 */
public class MessageTypeBean {

    /**
     * id : 111193cf-7ac5-4c3c-808a-372432f4b533
     * name : 订单
     * largeIcon : http://qn.wgclm.com/4ea38289115e43f7a5e35cc7b67be62e.png
     * orderSeq : 1
     * parentID : fa7c2854-8b56-492b-b542-58a4ab2a7357
     * smallIcon : http://qn.wgclm.com/0de5ffc08036415fa36cc7e8baa5f76b.png
     * messageList : [{"name":"订单通知","description":"订单通知","sendDate":"2019-05-22"}]
     */

    private String id;
    private String name;
    private String largeIcon;
    private int orderSeq;
    private String parentID;
    private String smallIcon;
    private List<MessageListBean> messageList;

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

    public List<MessageListBean> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageListBean> messageList) {
        this.messageList = messageList;
    }

    public static class MessageListBean {
        /**
         * name : 订单通知
         * description : 订单通知
         * sendDate : 2019-05-22
         */

        private String name;
        private String description;
        private String sendDate;

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

        public String getSendDate() {
            return sendDate;
        }

        public void setSendDate(String sendDate) {
            this.sendDate = sendDate;
        }
    }
}
