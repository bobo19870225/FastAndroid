package com.zaomeng.zaomeng.model.repository.http.bean;

import java.util.List;

/**
 * Created by Sampson on 2019-04-24.
 * FastAndroid
 */
public class SpecificationsBean {

    /**
     * header : {"code":0,"msg":"getObjectFeatureItemList success"}
     * body : {"data":[{"featureDefineName":"颜色","featureDefineID":"402892e76a0bd37c016a0be91d1a0022","itemList":[{"objectFeatureItemName":"红色","objectFeatureItemID":"402892e76a0bd37c016a0be91deb0023","listImage":null,"faceImage":"http://wj.haoju.me/53a186ca877143a28c8e36de2f992290.png"},{"objectFeatureItemName":"白色","objectFeatureItemID":"402892e76a0bd37c016a0be91e540024","listImage":null,"faceImage":"http://wj.haoju.me/c5e204b9ceeb434697b51940c5a98520.png"}]}]}
     */

    private HeaderBean header;
    private BodyBean body;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class HeaderBean {
        /**
         * code : 0
         * msg : getObjectFeatureItemList success
         */

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class BodyBean {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * featureDefineName : 颜色
             * featureDefineID : 402892e76a0bd37c016a0be91d1a0022
             * itemList : [{"objectFeatureItemName":"红色","objectFeatureItemID":"402892e76a0bd37c016a0be91deb0023","listImage":null,"faceImage":"http://wj.haoju.me/53a186ca877143a28c8e36de2f992290.png"},{"objectFeatureItemName":"白色","objectFeatureItemID":"402892e76a0bd37c016a0be91e540024","listImage":null,"faceImage":"http://wj.haoju.me/c5e204b9ceeb434697b51940c5a98520.png"}]
             */

            private String featureDefineName;
            private String featureDefineID;
            private List<ItemListBean> itemList;

            public String getFeatureDefineName() {
                return featureDefineName;
            }

            public void setFeatureDefineName(String featureDefineName) {
                this.featureDefineName = featureDefineName;
            }

            public String getFeatureDefineID() {
                return featureDefineID;
            }

            public void setFeatureDefineID(String featureDefineID) {
                this.featureDefineID = featureDefineID;
            }

            public List<ItemListBean> getItemList() {
                return itemList;
            }

            public void setItemList(List<ItemListBean> itemList) {
                this.itemList = itemList;
            }

            public static class ItemListBean {
                /**
                 * objectFeatureItemName : 红色
                 * objectFeatureItemID : 402892e76a0bd37c016a0be91deb0023
                 * listImage : null
                 * faceImage : http://wj.haoju.me/53a186ca877143a28c8e36de2f992290.png
                 */

                private String objectFeatureItemName;
                private String objectFeatureItemID;
                private Object listImage;
                private String faceImage;

                public String getObjectFeatureItemName() {
                    return objectFeatureItemName;
                }

                public void setObjectFeatureItemName(String objectFeatureItemName) {
                    this.objectFeatureItemName = objectFeatureItemName;
                }

                public String getObjectFeatureItemID() {
                    return objectFeatureItemID;
                }

                public void setObjectFeatureItemID(String objectFeatureItemID) {
                    this.objectFeatureItemID = objectFeatureItemID;
                }

                public Object getListImage() {
                    return listImage;
                }

                public void setListImage(Object listImage) {
                    this.listImage = listImage;
                }

                public String getFaceImage() {
                    return faceImage;
                }

                public void setFaceImage(String faceImage) {
                    this.faceImage = faceImage;
                }
            }
        }
    }
}
