package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019/4/19.
 * FastAndroid
 */
public class SendSmsCommonBean {


    /**
     * header : {"code":0,"msg":"sendSmsCommon success!"}
     * body : {"data":"5562"}
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
         * msg : sendSmsCommon success!
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
         * data : 5562
         */

        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
