package com.jinkan.www.fastandroid.model.repository.http;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class ApiResponse {

    /**
     * header : {"code":0,"msg":"appLogin success!"}
     * body : {"data":{"id":"21432141wsfwerq123","applicationID":"8a2f462a66cac9130166ccd9c99304f4","companyID":"8a2f462a66cac9130166ccd7c22b04e9","name":"小铁","shortName":"小铁","loginName":"13162617998","phone":"13162617998","weixinToken":null,"weixinUnionID":null,"recommandID":"21432141wsfwerq123","recommandCode":"00001","recommandChain":"00001","siteID":"8a2f462a6763d1d7016763e2c07f0049","avatarURL":"http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg?w=23&h=23"},"sessionID":"BC85369AD6FA1425B50E84DBEA8FE784"}
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
         * msg : appLogin success!
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
        /**
         * data : {"id":"21432141wsfwerq123","applicationID":"8a2f462a66cac9130166ccd9c99304f4","companyID":"8a2f462a66cac9130166ccd7c22b04e9","name":"小铁","shortName":"小铁","loginName":"13162617998","phone":"13162617998","weixinToken":null,"weixinUnionID":null,"recommandID":"21432141wsfwerq123","recommandCode":"00001","recommandChain":"00001","siteID":"8a2f462a6763d1d7016763e2c07f0049","avatarURL":"http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg?w=23&h=23"}
         * sessionID : BC85369AD6FA1425B50E84DBEA8FE784
         */

        private DataBean data;
        private String sessionID;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getSessionID() {
            return sessionID;
        }

        public void setSessionID(String sessionID) {
            this.sessionID = sessionID;
        }

        public static class DataBean {
            /**
             * id : 21432141wsfwerq123
             * applicationID : 8a2f462a66cac9130166ccd9c99304f4
             * companyID : 8a2f462a66cac9130166ccd7c22b04e9
             * name : 小铁
             * shortName : 小铁
             * loginName : 13162617998
             * phone : 13162617998
             * weixinToken : null
             * weixinUnionID : null
             * recommandID : 21432141wsfwerq123
             * recommandCode : 00001
             * recommandChain : 00001
             * siteID : 8a2f462a6763d1d7016763e2c07f0049
             * avatarURL : http://admin.haoju.me:8082/kpbase//group/M00/85/6A/5FA8-8338-4468-92AB-EED0E66BEACF.jpeg?w=23&h=23
             */

            private String id;
            private String applicationID;
            private String companyID;
            private String name;
            private String shortName;
            private String loginName;
            private String phone;
            private Object weixinToken;
            private Object weixinUnionID;
            private String recommandID;
            private String recommandCode;
            private String recommandChain;
            private String siteID;
            private String avatarURL;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getApplicationID() {
                return applicationID;
            }

            public void setApplicationID(String applicationID) {
                this.applicationID = applicationID;
            }

            public String getCompanyID() {
                return companyID;
            }

            public void setCompanyID(String companyID) {
                this.companyID = companyID;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public Object getWeixinToken() {
                return weixinToken;
            }

            public void setWeixinToken(Object weixinToken) {
                this.weixinToken = weixinToken;
            }

            public Object getWeixinUnionID() {
                return weixinUnionID;
            }

            public void setWeixinUnionID(Object weixinUnionID) {
                this.weixinUnionID = weixinUnionID;
            }

            public String getRecommandID() {
                return recommandID;
            }

            public void setRecommandID(String recommandID) {
                this.recommandID = recommandID;
            }

            public String getRecommandCode() {
                return recommandCode;
            }

            public void setRecommandCode(String recommandCode) {
                this.recommandCode = recommandCode;
            }

            public String getRecommandChain() {
                return recommandChain;
            }

            public void setRecommandChain(String recommandChain) {
                this.recommandChain = recommandChain;
            }

            public String getSiteID() {
                return siteID;
            }

            public void setSiteID(String siteID) {
                this.siteID = siteID;
            }

            public String getAvatarURL() {
                return avatarURL;
            }

            public void setAvatarURL(String avatarURL) {
                this.avatarURL = avatarURL;
            }
        }
    }
}
