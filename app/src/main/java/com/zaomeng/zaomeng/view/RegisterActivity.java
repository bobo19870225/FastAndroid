package com.zaomeng.zaomeng.view;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityRegisterBinding;
import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.model.repository.http.bean.SendSmsCommonBean;
import com.zaomeng.zaomeng.utils.CountDownTimerUtils;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.RegisterViewModel;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

/**
 * Created by Sampson on 2019/4/15.
 * FastAndroid
 */
public class RegisterActivity extends MVVMActivity<RegisterViewModel, ActivityRegisterBinding> {
    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    UserDao userDao;
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
                Bean<LoginBean> resource = beanResource.getResource();
                if (resource != null && resource.getHeader().getCode() == 0) {
                    BodyBean<LoginBean> body = resource.getBody();
                    LoginBean loginBean = body.getData();
                    if (loginBean != null) {
                        skipTo(MainActivity.class, null, true);
                        String sessionID = body.getSessionID();
                        SharedPreferencesUtils.saveSessionID(getApplicationContext(), sessionID);
                        SharedPreferencesUtils.saveMemberID(getApplicationContext(), loginBean.getId());
                        ExecutorService DB_IO = Executors.newFixedThreadPool(2);
                        DB_IO.execute(() -> {
                            userDao.InsertDateUser(loginBean);
                            DB_IO.shutdown();//关闭线程
                        });

                        String ldPhoneValue = mViewModel.ldPhone.getValue();
                        String ldPasswordValue = mViewModel.ldPassword.getValue();
                        if (ldPhoneValue != null && ldPasswordValue != null) {
                            SharedPreferencesUtils.saveLoginInfo(getApplicationContext(), ldPhoneValue, ldPasswordValue);
                        }
                    }
//                    skipTo(CertificationActivity.class, null);
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
