package com.zaomeng.zaomeng.view;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityRegisterBinding;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.RegisterBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SendSmsCommonBean;
import com.zaomeng.zaomeng.utils.CountDownTimerUtils;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.RegisterViewModel;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 */
public class RegisterActivity extends MVVMActivity<RegisterViewModel, ActivityRegisterBinding> {
    @Inject
    ViewModelFactory viewModelFactory;

    @NonNull
    @Override
    protected RegisterViewModel createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(RegisterViewModel.class);
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

        mViewModel.ldRegister.observe(this, beanResource -> {
            if (beanResource.isSuccess()) {
                Bean<RegisterBean> resource = beanResource.getResource();
                if (resource != null && resource.getHeader().getCode() == 0) {
                    RegisterBean registerBean = resource.getBody().getData();
                    skipTo(CertificationActivity.class, null);
                } else if (resource != null) {
                    toast(resource.getHeader().getMsg());
                }
            } else {
                Throwable error = beanResource.getError();
                if (error != null) {
                    toast(error.toString());
                }
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
        return "注册";
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_register;
    }
}
