package com.zaomeng.zaomeng.view;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityFindPasswordBinding;
import com.zaomeng.zaomeng.model.repository.http.HttpHelper;
import com.zaomeng.zaomeng.model.repository.http.InterfaceLogin;
import com.zaomeng.zaomeng.model.repository.http.bean.SendSmsCommonBean;
import com.zaomeng.zaomeng.utils.CountDownTimerUtils;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.FindPasswordVM;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019-05-14.
 * FastAndroid
 */
public class FindPasswordActivity extends MVVMActivity<FindPasswordVM, ActivityFindPasswordBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    HttpHelper httpHelper;
    @NonNull
    @Override
    protected FindPasswordVM createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(FindPasswordVM.class);
    }

    @Override
    protected void setView() {
        mViewModel.ldSendSmsCommonBean.observe(this, beanResource -> {
            if (beanResource.isSuccess()) {
                SendSmsCommonBean commonBean = beanResource.getResource();
                if (commonBean != null && commonBean.getHeader().getCode() == 0) {
                    SendSmsCommonBean.BodyBean body = commonBean.getBody();
                    String vCode = body.getData();
                    mViewModel.isVCodeCorrect(vCode);
                } else if (commonBean != null) {
                    toast(commonBean.getHeader().getMsg());
                }

            } else {
                Throwable error = beanResource.getError();
                if (error != null) {
                    toast(error.toString());
                }
            }
        });
        mViewModel.ldFindPassword.observe(this, beanResource -> {
            String s = (String) httpHelper.AnalyticalData(beanResource, new InterfaceLogin() {
                @Override
                public void skipLoginActivity() {
                    skipTo(LoginActivity.class, null);
                }

                @Override
                public void reLoad() {

                }
            }, this);
            if (s != null) {
                finish();
            }
        });
        mViewModel.action.observe(this, s -> {
            switch (s) {
                case "getVCode":
                    TextView getVerificationCode = mViewDataBinding.getVerificationCode;
                    CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(getVerificationCode, 60000, 1000);
                    countDownTimerUtils.start();
                    break;
                case "phoneError":
                    toast("电话号码不正确");
                    break;
                case "inputVCode":
                    toast("请输入验证码");
                    break;
                case "vCodeError":
                    toast("验证码错误");
                    break;
                case "inputPassword":
                    toast("请输入密码");
                    break;
                case "PasswordError":
                    toast("密码至少为6位");
                    break;

            }

        });
    }

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return "忘记密码";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_find_password;
    }
}
