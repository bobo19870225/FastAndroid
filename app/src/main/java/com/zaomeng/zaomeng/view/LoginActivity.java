package com.zaomeng.zaomeng.view;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.zaomeng.zaomeng.R;
import com.zaomeng.zaomeng.databinding.ActivityLoginBinding;
import com.zaomeng.zaomeng.model.repository.dataBase.UserDao;
import com.zaomeng.zaomeng.model.repository.http.ApiService;
import com.zaomeng.zaomeng.model.repository.http.bean.Bean;
import com.zaomeng.zaomeng.model.repository.http.bean.BodyBean;
import com.zaomeng.zaomeng.model.repository.http.bean.LoginBean;
import com.zaomeng.zaomeng.utils.SharedPreferencesUtils;
import com.zaomeng.zaomeng.view.base.MVVMActivity;
import com.zaomeng.zaomeng.view_model.LoginViewModel;
import com.zaomeng.zaomeng.view_model.ViewModelFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends MVVMActivity<LoginViewModel, ActivityLoginBinding> {
    @Inject
    ApiService apiService;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    UserDao userDao;
    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private EditText mEmailView;
    private View mProgressView;
    private ScrollView mLoginFormView;

    @Override
    protected int setToolBarMenu() {
        return 0;
    }

    @Override
    protected String setToolBarTitle() {
        return null;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.activity_login;
    }


    /*动态权限申请*/
    private void mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, v -> requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS));
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, REQUEST_READ_CONTACTS);

        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mayRequestContacts();
            }
        }
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }


    @NonNull
    @Override
    protected LoginViewModel createdViewModel() {
        return ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
    }

    @Override
    protected void setView() {
        mViewModel.ldShow.observe(this, this::showProgress);
        mViewModel.action.observe(this, s -> {
                    switch (s) {
                        case "register":
                            skipTo(RegisterActivity.class, null);
                            break;
                        case "forgetPassword":
                            skipTo(FindPasswordActivity.class, null);
                            break;
                        case "密码不少于6位":
                        case "电话号码错误":
                            toast(s);
                            break;
                    }

                }
        );
        mEmailView = mViewDataBinding.phone;
        mViewModel.ldPhone.observe(this, s -> {
            if (s.length() == 11) {
                mViewDataBinding.password.requestFocus();
            }
        });
        mayRequestContacts();
        EditText mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                mViewModel.login();
                return true;
            }
            return false;
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.rl_login);
        mViewModel.ldLogin.observe(this, messageResource -> {
            if (messageResource.isSuccess()) {
                Bean<LoginBean> loginBeanBean = messageResource.getResource();
                if (loginBeanBean != null) {
//                    showProgress(false);
                    if (loginBeanBean.getHeader().getCode() == 0) {
                        BodyBean<LoginBean> body = loginBeanBean.getBody();
                        LoginBean loginBean = body.getData();
                        if (loginBean != null) {
                            skipTo(MainActivity.class, null);
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
                            finish();
                        }
                    } else {
                        toast(loginBeanBean.getHeader().getMsg());
                        showProgress(false);
                    }
                }
            } else {
                Throwable error = messageResource.getError();
                if (error != null) {
                    toast(error.toString());
                    showProgress(false);
                }
            }
        });
    }

}

