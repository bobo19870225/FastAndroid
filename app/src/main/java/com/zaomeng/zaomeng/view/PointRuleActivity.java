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
    private String articleID;

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        String transferData = (String) this.transferData;
        switch (transferData) {
            case "积分规则":
                articleID = "402892e96a3414ba016a341563dd0003";
                break;
            case "赚取积分":
                articleID = "2c9051726af491b5016b0743c388006e";
                break;
            case "积分兑换":
                articleID = "2c9051726af491b5016b0743fe4e006f";
                break;
            case "退换货问题":
                articleID = "2c9051726ad486f5016af2be14890028";
                break;
            case "配送问题":
                articleID = "2c9051726ad486f5016af2bde7890027";
                break;
            case "支付问题":
                articleID = "2c9051726ad486f5016af2bd6f320026";
                break;
            case "常见问题":
                articleID = "2c9051726ad486f5016af2bcbf670025";
                break;

        }
        getArticle();
        return transferData;

    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_point_rule;
    }

    @Override
    protected void initView() {

    }

    private void getArticle() {
        LoadingDailog loadingDailog = new LoadingDailog.Builder(this).setCancelable(true).
                setCancelOutside(true).
                setShowMessage(true).setMessage("加载中...").create();
        loadingDailog.show();
        apiService.getArticleDetail(articleID).observe(this, beanResource -> {
            ArticleDetailBean articleDetailBean = (ArticleDetailBean) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class);
                }

                @Override
                public void reLoad() {
                    apiService.getArticleDetail(articleID);
                }
            }, this);
//            TextView text = findViewById(R.id.tv_title);
//            text.setText(articleDetailBean.getTitle());
            if (articleDetailBean != null) {
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
            } else {
                if (loadingDailog.isShowing()) {
                    loadingDailog.dismiss();
                }
            }
        });
    }
}
