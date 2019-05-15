package com.zaomeng.zaomeng.model.repository.http.bean;

/**
 * Created by Sampson on 2019-05-08.
 * FastAndroid
 */
public class AliPayBean {

    /**
     * header : {"code":0,"msg":"appApplyMemberOrderPay success!"}
     * body : {"dataString":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2019042964374457&biz_content=%7B%22body%22%3A%22%E4%BC%9A%E5%91%98%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%222c9051726a97068a016a9769ea5f0001%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BC%9A%E5%91%98%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.activity_pay&notify_url=http%3A%2F%2F47.110.250.70%3A8083%2Fzaomeng-api%2Fapi%2FappApplyMemberOrderPay.json&sign=VFbms5CdlTW8bnFea0xIApq2Trf9Wj8YkFjjNSx3yNux5YiRNpdHXJikIV7x51yILqijVcBa9JquSbN8BOLBaqfQ%2B6Jz6z%2B9EctEuc4p12JCsAa459YSvMNmkVegQQFrxW8PR1jlBPxsCSO5KFyP3%2B%2FkKKInlOxN8fRyyngcBCjBTSEbjCgwn9TxsWScKwzRNtEhvpV9veC8n57aqTs7zFvtGyZ15EEiwmHh9oMOFN%2BiNuSHIEIKqdUuZ7H9IguJyHwo%2F5wHWqe79J6MM09nMYcRWoz0MQ%2BEP%2F%2FwJlDC5H9AlvO2LOXDuedXOP971I1tvSAqMSTiU1oceneINKcqTQ%3D%3D&sign_type=RSA2&timestamp=2019-05-08+20%3A27%3A42&version=1.0"}
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
         * dataString : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2019042964374457&biz_content=%7B%22body%22%3A%22%E4%BC%9A%E5%91%98%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22out_trade_no%22%3A%222c9051726a97068a016a9769ea5f0001%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E4%BC%9A%E5%91%98%E8%B4%AD%E4%B9%B0%E5%95%86%E5%93%81%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.activity_pay&notify_url=http%3A%2F%2F47.110.250.70%3A8083%2Fzaomeng-api%2Fapi%2FappApplyMemberOrderPay.json&sign=VFbms5CdlTW8bnFea0xIApq2Trf9Wj8YkFjjNSx3yNux5YiRNpdHXJikIV7x51yILqijVcBa9JquSbN8BOLBaqfQ%2B6Jz6z%2B9EctEuc4p12JCsAa459YSvMNmkVegQQFrxW8PR1jlBPxsCSO5KFyP3%2B%2FkKKInlOxN8fRyyngcBCjBTSEbjCgwn9TxsWScKwzRNtEhvpV9veC8n57aqTs7zFvtGyZ15EEiwmHh9oMOFN%2BiNuSHIEIKqdUuZ7H9IguJyHwo%2F5wHWqe79J6MM09nMYcRWoz0MQ%2BEP%2F%2FwJlDC5H9AlvO2LOXDuedXOP971I1tvSAqMSTiU1oceneINKcqTQ%3D%3D&sign_type=RSA2&timestamp=2019-05-08+20%3A27%3A42&version=1.0
         */

        private String dataString;

        public String getDataString() {
            return dataString;
        }

        public void setDataString(String dataString) {
            this.dataString = dataString;
        }
    }
}
