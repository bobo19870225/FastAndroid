package com.zaomeng.zaomeng.model.repository.http.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sampson on 2019-05-15.
 * FastAndroid
 */
public class WeChatPayBean {

    /**
     * header : {"code":0,"msg":"appApplyMemberOrderPay success!"}
     * body : {"package":"Sign=WXPay","appid":"wx2fc887560c55de68","sign":"6BE4AFC4B41983AF247A7DC0B96744E4","partnerid":"1534247421","prepayid":"wx151038117152786d11bb22a03593819073","noncestr":"1175103810","timestamp":"1557887891"}
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
         * msg : appApplyMemberOrderPay success!
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
         * package : Sign=WXPay
         * appid : wx2fc887560c55de68
         * sign : 6BE4AFC4B41983AF247A7DC0B96744E4
         * partnerid : 1534247421
         * prepayid : wx151038117152786d11bb22a03593819073
         * noncestr : 1175103810
         * timestamp : 1557887891
         */

        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
