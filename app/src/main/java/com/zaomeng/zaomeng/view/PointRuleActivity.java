package com.zaomeng.zaomeng.view;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.ArticleDetailBean;
import com.zaomeng.zaomeng.view.base.BaseDaggerActivity;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-20.
 * FastAndroid
 */
public class PointRuleActivity extends BaseDaggerActivity {
    @Inject
    ApiService apiService;
    @Inject
    HttpHelper httpHelper;
    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "积分规则";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_point_rule;
    }

    @Override
    protected void initView() {
        LoadingDailog loadingDailog = new LoadingDailog.Builder(this).setCancelable(true).
                setCancelOutside(true).
                setShowMessage(true).setMessage("加载中...").create();
        loadingDailog.show();
        apiService.getArticleDetail("402892e96a3414ba016a341563dd0003").observe(this, beanResource -> {
            ArticleDetailBean articleDetailBean = (ArticleDetailBean) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {
                    apiService.getArticleDetail("402892e96a3414ba016a341563dd0003");
                }
            }, this);
//            TextView text = findViewById(R.id.tv_title);
//            text.setText(articleDetailBean.getTitle());
            WebView webView = findViewById(R.id.web_view);
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    if (newProgress == 100) {
                        if (loadingDailog.isShowing()) {
                            loadingDailog.dismiss();
                        }
                    }
                }
            });
            webView.loadData(articleDetailBean.getContent(), "text/html; charset=UTF-8", null);//这种写法可以正确解码
        });

    }
}
