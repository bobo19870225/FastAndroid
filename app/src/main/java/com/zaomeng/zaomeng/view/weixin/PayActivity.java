package com.zaomeng.zaomeng.view.weixin;


import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.view.base.BaseActivity;

import static com.zaomeng.zaomeng.utils.SystemParameter.APP_ID;

public class PayActivity extends BaseActivity {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        api = WXAPIFactory.createWXAPI(this, APP_ID);

        Button appayBtn = findViewById(R.id.appay_btn);
        appayBtn.setOnClickListener(v -> {
//            String url = "https://wxpay.wxutil.com/pub_v2/app/app_pay.php";
//			Button payBtn = findViewById(R.id.appay_btn);
            appayBtn.setEnabled(false);
            Toast.makeText(PayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
//            try {
//                byte[] buf = Util.httpGet(url);
//                if (buf != null && buf.length > 0) {
//                    String content = new String(buf);
//                    Log.e("get server activity_pay params:", content);
//                    JSONObject json = new JSONObject(content);
//                    if (null != json && !json.has("retcode")) {
//                        PayReq req = new PayReq();
//                        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
//                        req.appId = json.getString("appid");
//                        req.partnerId = json.getString("partnerid");
//                        req.prepayId = json.getString("prepayid");
//                        req.nonceStr = json.getString("noncestr");
//                        req.timeStamp = json.getString("timestamp");
//                        req.packageValue = json.getString("package");
//                        req.sign = json.getString("sign");
//                        req.extData = "app data"; // optional
//                        Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
//                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//                        api.sendReq(req);
//                    } else {
//                        Log.d("PAY_GET", "返回错误" + json.getString("retmsg"));
//                        Toast.makeText(PayActivity.this, "返回错误" + json.getString("retmsg"), Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Log.d("PAY_GET", "服务器请求错误");
//                    Toast.makeText(PayActivity.this, "服务器请求错误", Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e) {
//                Log.e("PAY_GET", "异常：" + e.getMessage());
//                Toast.makeText(PayActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
            appayBtn.setEnabled(true);
        });
        Button checkPayBtn = findViewById(R.id.check_pay_btn);
        checkPayBtn.setOnClickListener(v -> {
            boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
            Toast.makeText(PayActivity.this, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {

    }

}
